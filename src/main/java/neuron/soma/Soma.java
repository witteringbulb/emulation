package main.java.neuron.soma;

import javafx.util.Pair;
import main.java.neuron.Neuron;
import main.java.neuron.branch.Dendrite;

import java.util.List;

public abstract class Soma {

    private Neuron parentNeuron;
    public void setParentNeuron(Neuron neuron) {
        this.parentNeuron = neuron;
    }

    private Pair<Double, Double> somaLocation;

    private List<Dendrite> dendrites;

    public Soma(Pair<Double, Double> somaLocation, List<Dendrite> connectedDendrites) {
        this.somaLocation = somaLocation;
        this.dendrites = connectedDendrites;
    }

    public abstract boolean doesAxonFireAtNextTimeIncrement();

    public double sumSignalsFromEndsOfDendritesForThisTimeIncrement() {
        return this.getDendrites().stream()
                .mapToDouble(dendrite -> dendrite.getSignalMagnitudeAtEndOfBranch())
                .sum();
    }

    public void setDendrites(List<Dendrite> dendrites) {
        this.dendrites = dendrites;
    }

    public List<Dendrite> getDendrites() {
        return dendrites;
    }

}
