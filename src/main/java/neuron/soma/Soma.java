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

    private double[] somaLocation ;

    private List<Dendrite> dendrites;

    public Soma(double[] somaLocation) {
        if (somaLocation.length != 2) {
            throw new IllegalArgumentException(
                    "somaLocation coordinates must be supplied as an array of length 2");
        }
        this.somaLocation = somaLocation;
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

    public void setDendrites(List<Dendrite> dendrites) { this.dendrites = dendrites; }

    public double[] getSomaLocation() {
        return somaLocation;
    }

}
