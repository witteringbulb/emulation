package neuron.soma;

import javafx.util.Pair;
import neuron.Neuron;
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

    public void propagateDendriteSignalsOneTimeIncrementAndFireAxonIfRequired() {
        dendrites.forEach(dendrite -> dendrite.propagateSignalsOneTimeIncrement());
        fireAxonIfRequired();
    }

    public void fireAxonIfRequired() {
        if (doesAxonFireAtCurrentTimeIncrement()) {
            this.parentNeuron.fireAxon();
        }
    }

    public abstract boolean doesAxonFireAtCurrentTimeIncrement();

    public double sumSignalsFromEndsOfDendritesForThisTimeIncrement() {
        return this.getDendrites().stream()
                .mapToDouble(dendrite -> dendrite.getSignalMagnitudeAtEndOfBranch())
                .sum();
    }

    public List<Dendrite> getDendrites() {
        return dendrites;
    }

}
