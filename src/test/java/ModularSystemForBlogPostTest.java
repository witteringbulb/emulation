import defaults.DefaultValues;
import network.*;
import neuron.branch.BranchIdGeneratorStatic;
import org.junit.jupiter.api.Test;
import output.NetworkSaver;
import output.SignalSaver;
import simulation.PopulationSimulator;

import java.io.File;

class ModularSystemForBlogPostTest {

    private Brain brain;
    private SignalSaver signalSaver;
    private NetworkSaver networkSaver;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        BranchIdGeneratorStatic.reset();

        String saveDirectory =
                "src" + File.separator + "test" + File.separator +
                        "output_and_sketches" + File.separator + "ModularSystemForBlogPostTest" + File.separator +
                "modular_brain_blog_sketch" + File.separator + "data" + File.separator;

        BrainDesigner brainDesigner = new BrainDesigner();
        brainDesigner.addModule("1", new NeuralModule(150, new double[]{8,8}, 8));
        brainDesigner.addModule("2", new NeuralModule(150, new double[]{-8,-8}, 8));
        brainDesigner.establishUnidirectionalModuleConnections("1", "2", 60);
        brain = brainDesigner.createBrainNetworkFromModules();

        networkSaver = new NetworkSaver(brain.getAllNeurons(), saveDirectory);
        signalSaver = new SignalSaver(brain.getAllNeurons(), saveDirectory);
    }

    @Test
    public void testSimulateAndSaveModularNeuronSystem() {

        networkSaver.saveNeuronConfiguration();

        PopulationSimulator simulator = new PopulationSimulator(brain.getAllNeurons(), signalSaver);

        simulator.simulateWithAllDendritesFiringRandomly(800, 0.002, DefaultValues.DEFAULT_SIGNAL_AMPLITUDE);
    }
}