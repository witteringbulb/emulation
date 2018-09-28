import defaults.DefaultValues;
import network.NetworkDesignerStatic;
import network.NetworkModuleDesigner;
import neuron.Neuron;
import neuron.branch.BranchIdGeneratorStatic;
import org.junit.jupiter.api.Test;
import output.NetworkSaver;
import output.SignalSaver;
import simulation.PopulationSimulator;

import java.io.File;
import java.util.List;

class MultiNeuronSystemForBlogPostTest {

    private List<Neuron> neuronPop;
    private SignalSaver signalSaver;
    private NetworkSaver networkSaver;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        BranchIdGeneratorStatic.reset();

        String saveDirectory =
                "src" + File.separator + "test" + File.separator +
                        "output_and_sketches" + File.separator + "MultiNeuronSystemForBlogPostTest" + File.separator +
                "multi_neuron_blog_sketch" + File.separator + "data" + File.separator;

        NetworkModuleDesigner networkModuleDesigner = new NetworkModuleDesigner(50, new double[]{0,0}, 5.0);

        neuronPop = networkModuleDesigner.designNewDisconnectedNetwork();
        NetworkDesignerStatic.createAndConnectAxonTerminalsForNetwork(neuronPop);

        networkSaver = new NetworkSaver(neuronPop, saveDirectory);
        signalSaver = new SignalSaver(neuronPop, saveDirectory);
    }

    @Test
    public void testSimulateAndSaveMultiNeuronSystem() {

        networkSaver.saveNeuronConfiguration();

        PopulationSimulator simulator = new PopulationSimulator(neuronPop, signalSaver);

        simulator.simulateWithAllDendritesFiringRandomly(400, 0.001, DefaultValues.DEFAULT_SIGNAL_AMPLITUDE);
    }
}