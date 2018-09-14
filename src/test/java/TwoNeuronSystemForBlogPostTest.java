import defaults.DefaultValues;
import neuron.Neuron;
import neuron.branch.Axon;
import neuron.branch.AxonTerminal;
import neuron.branch.BranchIdGeneratorStatic;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class TwoNeuronSystemForBlogPostTest {

    private Neuron neuron1;
    private Neuron neuron2;
    private List<Neuron> neuronPop;
    private SignalSaver signalSaver;
    private NetworkSaver networkSaver;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        BranchIdGeneratorStatic.reset();

        String saveDirectory =
                "src" + File.separator + "test" + File.separator +
                        "output_and_sketches" + File.separator + "TwoNeuronSystemForBlogPostTest" + File.separator +
                "two_neurons_blog_sketch" + File.separator + "data" + File.separator;

        SignalType signalType = SignalType.SQUARE_SIGNAL_DEFAULT;

        Soma soma1 = new HeavisideSoma(new double[]{0.0, 0.0}, 1.5);
        Axon axon1 = new Axon(soma1.getSomaLocation(), new double[]{0.0, 3.0}, SignalType.SQUARE_SIGNAL_DEFAULT);

        Soma soma2 = new HeavisideSoma(new double[]{1.0, 3.0}, 0.7);
        Axon axon2 = new Axon(soma2.getSomaLocation(), new double[]{1.0, 0.0}, SignalType.SQUARE_SIGNAL_DEFAULT);

        neuron1 = new Neuron(soma1, axon1);
        neuron2 = new Neuron(soma2, axon2);

        neuron1.getSoma().setDendrites(
                new BranchGenerator(neuron1.getSoma().getSomaLocation(), Dendrite.class, signalType)
                        .createEvenlySpacedBranchesOfEqualLength(2.0, 8));
        neuron2.getSoma().setDendrites(
                new BranchGenerator(neuron2.getSoma().getSomaLocation(), Dendrite.class, signalType)
                        .createEvenlySpacedBranchesOfEqualLength(2.0, 8));

        for (int i = 5; i < 8; i++) {
            Dendrite dendrite = neuron1.getSoma().getDendrites().get(i);
            neuron2.getAxon().addAxonTerminalConnectionToDendrite(dendrite, 1);
        }
        for (int i = 3; i < 8; i++) {
            Dendrite dendrite = neuron2.getSoma().getDendrites().get(i);
            neuron1.getAxon().addAxonTerminalConnectionToDendrite(dendrite, 1);
        }

        neuronPop = new ArrayList<Neuron>();
        neuronPop.add(neuron1);
        neuronPop.add(neuron2);
        networkSaver = new NetworkSaver(neuronPop, saveDirectory);
        signalSaver = new SignalSaver(neuronPop, saveDirectory);
    }

    @Test
    public void testSimulateAndSaveTwoNeuronSystem() {

        networkSaver.saveNeuronConfiguration();

        HashMap<Integer, PopulationFiringData> allFiringData = new HashMap<>();

        //Then we fire signals
        double[] firingAmplitudes = new double[4];
        Arrays.fill(firingAmplitudes, DefaultValues.DEFAULT_SIGNAL_AMPLITUDE);
        allFiringData.put(0, new PopulationFiringData(new int[]{2, 3, 4, 5},
                firingAmplitudes));

        PopulationSimulator simulator = new PopulationSimulator(neuronPop, signalSaver);

        simulator.simulateWithPredeterminedFirings(400, allFiringData);
    }
}