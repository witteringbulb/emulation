package test.java.neuron;

import javafx.util.Pair;
import main.java.neuron.Neuron;
import main.java.neuron.branch.Axon;
import main.java.neuron.branch.AxonTerminal;
import main.java.neuron.branch.Dendrite;
import main.java.neuron.signal.SquareSignal;
import main.java.neuron.soma.HeavisideSoma;
import main.java.neuron.soma.Soma;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NeuronTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        Dendrite dendrite = new Dendrite(0.5, 1.5, SquareSignal.class, 0.2, 0.1);
        List<Dendrite> dendrites = new ArrayList<Dendrite>();
        dendrites.add(dendrite);
        Soma soma = new HeavisideSoma(new Pair<Double, Double>(1.0, 0.5), dendrites, 0.5);

        AxonTerminal axonTerminal = new AxonTerminal(0.0, 2.0, SquareSignal.class, 0.1, 0.3);
        List<AxonTerminal> axonTerminals = new ArrayList<AxonTerminal>();
        axonTerminals.add(axonTerminal);
        Axon axon = new Axon(0.9, 3.1, SquareSignal.class, 0.3, 0.1, axonTerminals);

        Neuron neuron = new Neuron(soma, axon);

        //TODO: Finish test

    }
}