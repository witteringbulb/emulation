import defaults.DefaultValues;
import network.NetworkDesigner;
import network.builders.NetworkDesignerBuilder;
import neuron.Neuron;
import neuron.branch.Axon;
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

        NetworkDesigner networkDesigner = new NetworkDesignerBuilder().setNumberOfNeurons(20).createNetworkDesigner();

        neuronPop = networkDesigner.designNewNetwork();

        networkSaver = new NetworkSaver(neuronPop, saveDirectory);
        signalSaver = new SignalSaver(neuronPop, saveDirectory);
    }

    @Test
    public void testSimulateAndSaveTwoNeuronSystem() {

        networkSaver.saveNeuronConfiguration();

        PopulationSimulator simulator = new PopulationSimulator(neuronPop, signalSaver);

        simulator.simulateWithAllDendritesFiringRandomly(400, 0.01, DefaultValues.DEFAULT_SIGNAL_AMPLITUDE);
    }
}