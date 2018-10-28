import neuron.Neuron;
import neuron.branch.Axon;
import neuron.branch.AxonTerminal;
import neuron.builders.AxonTerminalBuilder;
import neuron.branch.Dendrite;
import neuron.builders.DendriteBuilder;
import neuron.signal.SignalType;
import neuron.soma.HeavisideSoma;
import neuron.soma.Soma;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NeuronTest {

    private final double deviationThreshold = 0.001;

    private Neuron neuron;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        Dendrite dendrite1 = new DendriteBuilder().setCoordinatesOfBranchBeginning(new double[]{1.0, 0.5}).setCoordinatesOfBranchEnd(new double[]{0.5, 1.0}).setSignalType(SignalType.SQUARE_SIGNAL_DEFAULT).createDendrite();
        Dendrite dendrite2 = new DendriteBuilder().setCoordinatesOfBranchBeginning(new double[]{1.0, 0.5}).setCoordinatesOfBranchEnd(new double[]{0.1, 0.3}).setSignalType(SignalType.SQUARE_SIGNAL_DEFAULT).createDendrite();
        List<Dendrite> dendrites = new ArrayList<>();
        dendrites.add(dendrite1);
        dendrites.add(dendrite2);
        Soma soma = new HeavisideSoma(new double[]{1.0, 0.5}, 0.5);
        soma.setDendrites(dendrites);

        AxonTerminal axonTerminal1 = new AxonTerminalBuilder().setCoordinatesOfBranchBeginning(new double[]{2.0, 1.5}).setCoordinatesOfBranchEnd(new double[]{2.5, 1.7}).setSignalType(SignalType.SQUARE_SIGNAL_DEFAULT).createAxonTerminal();
        AxonTerminal axonTerminal2 = new AxonTerminalBuilder().setCoordinatesOfBranchBeginning(new double[]{2.0, 1.5}).setCoordinatesOfBranchEnd(new double[]{2.0, 2.1}).setSignalType(SignalType.SQUARE_SIGNAL_DEFAULT).createAxonTerminal();
        List<AxonTerminal> axonTerminals = new ArrayList<>();
        axonTerminals.add(axonTerminal1);
        axonTerminals.add(axonTerminal2);
        Axon axon = new Axon(soma.getSomaLocation(), new double[]{1.0, 4.5}, SignalType.SQUARE_SIGNAL_DEFAULT);
        axon.setAxonTerminals(axonTerminals);

        neuron = new Neuron(soma, axon, false);

    }

    @Test
    public void checkNeuronValuesAreSetCorrectly() {
        assertEquals(1.0, neuron.getSoma().getDendrites().get(0).getCoordinatesOfBranchEnd()[1]);
        assertEquals(0.1, neuron.getSoma().getDendrites().get(1).getCoordinatesOfBranchEnd()[0]);

        assertEquals(neuron.getSoma().getSomaLocation()[0], 1.0);
    }

    @Test
    public void testThatNoSignalAtDendritesMeansNoSignalAtAxonAndNoSignalAtAxonTerminals() {

        for (int t = 0; t < 1000; t++) {
            this.neuron.propagateSignalsOneTimeIncrement();
            assertEquals(neuron.getAxon().getSignalMagnitudeAtEndOfBranch(), 0.0);
            for (AxonTerminal axonTerminal : neuron.getAxon().getAxonTerminals()) {
                assertEquals(axonTerminal.getSignalMagnitudeAtEndOfBranch(), 0.0);
            }
        }

    }

    @Test
    public void testThatBelowThresholdSignalAtDendritesMeansNoSignalAtAxonAndNoSignalAtAxonTerminals() {

        neuron.getSoma().getDendrites().get(0).fire(0.1);
        for (int t = 0; t < 1000; t++) {
            this.neuron.propagateSignalsOneTimeIncrement();
            assertEquals(neuron.getAxon().getSignalMagnitudeAtEndOfBranch(), 0.0);
            for (AxonTerminal axonTerminal : neuron.getAxon().getAxonTerminals()) {
                assertEquals(axonTerminal.getSignalMagnitudeAtEndOfBranch(), 0.0);
            }
        }

    }

    @Test
    public void testThatAboveThresholdSignalAtDendritesMeansSignalAtAxonAndSignalAtAxonTerminals() {

        neuron.getSoma().getDendrites().get(0).fire(5.0);
        double cumulativeAxonSignal = 0.0;
        double cumulativeAxonPotentialSignal = 0.0;
        for (int t = 0; t < 1000; t++) {
            this.neuron.propagateSignalsOneTimeIncrement();
            cumulativeAxonSignal+=neuron.getAxon().getSignalMagnitudeAtEndOfBranch();
            for (AxonTerminal axonTerminal : neuron.getAxon().getAxonTerminals()) {
                cumulativeAxonPotentialSignal+=axonTerminal.getSignalMagnitudeAtEndOfBranch();
            }
        }
        assertTrue(cumulativeAxonSignal > 0);
        assertTrue(cumulativeAxonPotentialSignal > 0);
    }
}