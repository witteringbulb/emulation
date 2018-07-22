package neuron.soma;

import javafx.util.Pair;
import main.java.neuron.Neuron;
import neuron.branch.Dendrite;

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

    public void fireAxonIfRequired() {
        if (doesAxonFireAtNextTimeIncrement()) {
            this.parentNeuron.fireAxon();
        }
    }

    public abstract boolean doesAxonFireAtNextTimeIncrement();

    public double sumSignalsFromEndsOfDendritesForThisTimeIncrement() {
        return this.getDendrites().stream()
                .mapToDouble(dendrite -> dendrite.getSignalMagnitudeAtEndOfBranch())
                .sum();
    }

    public List<Dendrite> getDendrites() {
        return dendrites;
    }

}
