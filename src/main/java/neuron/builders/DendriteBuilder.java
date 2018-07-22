package neuron.builders;

import neuron.branch.Dendrite;
import neuron.signal.SignalType;

public class DendriteBuilder {
    private double[] coordinatesOfBranchBeginning;
    private double orientationInRadians;
    private double length;
    private SignalType signalType;

    public DendriteBuilder setCoordinatesOfBranchBeginning(double[] coordinatesOfBranchBeginning) {
        this.coordinatesOfBranchBeginning = coordinatesOfBranchBeginning;
        return this;
    }

    public DendriteBuilder setOrientationInRadians(double orientationInRadians) {
        this.orientationInRadians = orientationInRadians;
        return this;
    }

    public DendriteBuilder setLength(double length) {
        this.length = length;
        return this;
    }

    public DendriteBuilder setSignalType(SignalType signalType) {
        this.signalType = signalType;
        return this;
    }

    public Dendrite createDendrite() {
        return new Dendrite(coordinatesOfBranchBeginning, orientationInRadians, length, signalType);
    }
}