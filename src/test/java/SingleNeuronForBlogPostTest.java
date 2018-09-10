import defaults.DefaultValues;
import neuron.Neuron;
import neuron.branch.Axon;
import neuron.branch.AxonTerminal;
import neuron.branch.Dendrite;
import neuron.branchGeneration.BranchGenerator;
import neuron.signal.SignalType;
import neuron.soma.HeavisideSoma;
import neuron.soma.Soma;
import org.junit.jupiter.api.Test;
import output.NetworkSaver;
import output.SignalSaver;
import simulation.PopulationSimulator;
import simulation.firing.PopulationFiringData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class SingleNeuronForBlogPostTest {

    private Neuron neuron;
    private List<Neuron> neuronPop;
    private SignalSaver signalSaver;
    private NetworkSaver networkSaver;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        String saveDirectory =
                "src" + File.separator + "test" + File.separator +
                        "output_and_sketches" + File.separator + "SingleNeuronForBlogPostTest" + File.separator +
                "single_neuron_blog_sketch" + File.separator + "data" + File.separator;

        SignalType signalType = SignalType.SQUARE_SIGNAL_DEFAULT;

        Soma soma = new HeavisideSoma(new double[]{0.0, 0.0}, 0.5);
        Axon axon = new Axon(soma.getSomaLocation(), new double[]{0.0, 3.0}, SignalType.SQUARE_SIGNAL_DEFAULT);

        neuron = new Neuron(soma, axon);

        neuron.getSoma().setDendrites(
                new BranchGenerator(neuron.getSoma().getSomaLocation(), Dendrite.class, signalType)
                        .createEvenlySpacedBranchesOfEqualLength(2.0, 5));

        neuron.getAxon().setAxonTerminals(
                new BranchGenerator(neuron.getAxon().getCoordinatesOfBranchEnd(), AxonTerminal.class, signalType)
                        .createEvenlySpacedBranchesOfEqualLength(1.0, 4));

        neuronPop = new ArrayList<Neuron>();
        neuronPop.add(neuron);
        networkSaver = new NetworkSaver(neuronPop, saveDirectory);
        signalSaver = new SignalSaver(neuronPop, saveDirectory);
    }

    @Test
    public void testSimulateAndSaveSingleNeuron() {

        networkSaver.saveNeuronConfiguration();

        HashMap<Integer, PopulationFiringData> allFiringData = new HashMap<>();

        //Then we fire two signals
        allFiringData.put(0, new PopulationFiringData(new int[]{1, 2},
                new double[]{DefaultValues.DEFAULT_SIGNAL_AMPLITUDE, DefaultValues.DEFAULT_SIGNAL_AMPLITUDE}));
        //Then we fire two signals close behind (too close to refire axon)
        allFiringData.put(8, new PopulationFiringData(new int[]{2, 3},
                new double[]{DefaultValues.DEFAULT_SIGNAL_AMPLITUDE, DefaultValues.DEFAULT_SIGNAL_AMPLITUDE}));
        //fire a single signal
        allFiringData.put(22, new PopulationFiringData(new int[]{1}, new double[]{DefaultValues.DEFAULT_SIGNAL_AMPLITUDE}));
        allFiringData.put(52, new PopulationFiringData(new int[]{3}, new double[]{DefaultValues.DEFAULT_SIGNAL_AMPLITUDE}));
        allFiringData.put(76, new PopulationFiringData(new int[]{5}, new double[]{-DefaultValues.DEFAULT_SIGNAL_AMPLITUDE}));
        //Then we fire an excitatory and inhibitory signal
        allFiringData.put(100, new PopulationFiringData(new int[]{1, 2, 5},
                new double[]{DefaultValues.DEFAULT_SIGNAL_AMPLITUDE, DefaultValues.DEFAULT_SIGNAL_AMPLITUDE, -DefaultValues.DEFAULT_SIGNAL_AMPLITUDE}));


        PopulationSimulator simulator = new PopulationSimulator(neuronPop, signalSaver);

        simulator.simulateWithPredeterminedFirings(165, allFiringData);
    }
}