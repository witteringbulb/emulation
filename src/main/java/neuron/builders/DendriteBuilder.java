package neuron.builders;

import neuron.branch.Dendrite;
import neuron.signal.SignalType;

public class DendriteBuilder {
    private double[] coordinatesOfBranchBeginning;
    private double[] coordinatesOfBranchEnd;
    private SignalType signalType;

    public DendriteBuilder setCoordinatesOfBranchBeginning(double[] coordinatesOfBranchBeginning) {
        this.coordinatesOfBranchBeginning = coordinatesOfBranchBeginning;
        return this;
    }

    public DendriteBuilder setCoordinatesOfBranchEnd(double[] coordinatesOfBranchEnd) {
        this.coordinatesOfBranchEnd = coordinatesOfBranchEnd;
        return this;
    }

    public DendriteBuilder setSignalType(SignalType signalType) {
        this.signalType = signalType;
        return this;
    }

    public Dendrite createDendrite() {
        return new Dendrite(coordinatesOfBranchBeginning, coordinatesOfBranchEnd, signalType);
    }
}