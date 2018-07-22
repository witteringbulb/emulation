import javafx.util.Pair;
import neuron.Neuron;
import neuron.branch.Axon;
import neuron.branch.AxonTerminal;
import neuron.branch.Dendrite;
import neuron.signal.SignalType;
import neuron.soma.HeavisideSoma;
import neuron.soma.Soma;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NeuronTest {

    Neuron neuron;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        Dendrite dendrite1 = new Dendrite(0.5, 1.5, SignalType.SQUARE_SIGNAL_DEFAULT);
        Dendrite dendrite2 = new Dendrite(1.3, 1.0, SignalType.SQUARE_SIGNAL_DEFAULT);
        List<Dendrite> dendrites = new ArrayList<Dendrite>();
        dendrites.add(dendrite1);
        dendrites.add(dendrite2);
        Soma soma = new HeavisideSoma(new Pair<Double, Double>(1.0, 0.5), dendrites, 0.5);

        AxonTerminal axonTerminal1 = new AxonTerminal(0.2, 1.1, SignalType.SQUARE_SIGNAL_DEFAULT);
        AxonTerminal axonTerminal2 = new AxonTerminal(0.6, 2.1, SignalType.SQUARE_SIGNAL_DEFAULT);
        List<AxonTerminal> axonTerminals = new ArrayList<AxonTerminal>();
        axonTerminals.add(axonTerminal1);
        axonTerminals.add(axonTerminal2);
        Axon axon = new Axon(0.9, 5.3, SignalType.SQUARE_SIGNAL_DEFAULT, axonTerminals);

        neuron = new Neuron(soma, axon);

    }

    @Test
    public void checkNeuronValuesAreSetCorrectly() {
        assertEquals(0.5, neuron.getSoma().getDendrites().get(0).getOrientationInRadians());
        assertEquals(1.0, neuron.getSoma().getDendrites().get(1).getLength());

        //TODO: Add more
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