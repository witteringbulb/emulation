package main.java.generators;

import javafx.util.Pair;
import main.java.neuron.Branch.Axon;
import main.java.neuron.Branch.AxonTerminal;
import main.java.neuron.Branch.Dendrite;
import main.java.neuron.Neuron;

import java.util.List;

public class NeuronGenerator {

    public static Neuron generateNeuron(Pair<Double, Double> somaLocation,
                                        int axonOrientationInRadians,
                                        int axonLength,
                                        List<Dendrite> dendrites,
                                        List<AxonTerminal> axonTerminals) {
        Neuron neuron = new Neuron(somaLocation);
        neuron.setDendrites(dendrites);
        neuron.setAxon(new Axon(axonOrientationInRadians, axonLength));
        neuron.getAxon().setAxonTerminals(axonTerminals);
        return neuron;
    }

}
