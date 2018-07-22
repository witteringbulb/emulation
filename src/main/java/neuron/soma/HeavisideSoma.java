package neuron.soma;

import javafx.util.Pair;
import neuron.branch.Dendrite;

import java.util.List;

public class HeavisideSoma extends Soma {

    private double activationThreshold;

    public HeavisideSoma(double[] somaLocation,
                         List<Dendrite> connectedDendrites,
                         double activationThreshold) {
        super(somaLocation, connectedDendrites);
        this.activationThreshold = activationThreshold;
    }

    public boolean doesAxonFireAtCurrentTimeIncrement() {
        return this.sumSignalsFromEndsOfDendritesForThisTimeIncrement() >= activationThreshold;
    }

}
