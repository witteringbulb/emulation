import neuron.Neuron;
import neuron.branch.Axon;
import neuron.branch.AxonTerminal;
import neuron.branch.Dendrite;
import neuron.branchGeneration.BranchGenerator;
import neuron.builders.AxonTerminalBuilder;
import neuron.builders.DendriteBuilder;
import neuron.signal.SignalType;
import neuron.soma.HeavisideSoma;
import neuron.soma.Soma;
import org.junit.jupiter.api.Test;
import output.NetworkSaver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateSimulateAndSaveSingleNeuronTest {

    private Neuron neuron;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        String saveDirectory = "src"+ File.separator + "test" +File.separator + "CreateSimulateAndSaveSingleNeuronTest"+File.separator;

        SignalType signalType = SignalType.SQUARE_SIGNAL_DEFAULT;

        Soma soma = new HeavisideSoma(new double[]{0.0, 0.0}, 0.5);
        Axon axon = new Axon(soma.getSomaLocation(), new double[]{2.0, 1.5}, SignalType.SQUARE_SIGNAL_DEFAULT);

        neuron = new Neuron(soma, axon);

        neuron.getSoma().setDendrites(
                new BranchGenerator(neuron.getSoma().getSomaLocation(), Dendrite.class, signalType)
                        .createEvenlySpacedBranchesOfEqualLength(1.0, 3));

        neuron.getAxon().setAxonTerminals(
                new BranchGenerator(neuron.getAxon().getCoordinatesOfBranchEnd(), Dendrite.class, signalType)
                    .createEvenlySpacedBranchesOfEqualLength(1.0, 2));

        List<Neuron> neuronPop = new ArrayList<Neuron>();
        neuronPop.add(neuron);
        NetworkSaver networkSaver = new NetworkSaver(neuronPop, saveDirectory);

    }
}