package neuron.builders;

import neuron.branch.AxonTerminal;
import neuron.signal.SignalType;

public class AxonTerminalBuilder {
    private double[] coordinatesOfBranchBeginning;
    private double orientationInRadians;
    private double length;
    private SignalType signalType;

    public AxonTerminalBuilder setCoordinatesOfBranchBeginning(double[] coordinatesOfBranchBeginning) {
        this.coordinatesOfBranchBeginning = coordinatesOfBranchBeginning;
        return this;
    }

    public AxonTerminalBuilder setOrientationInRadians(double orientationInRadians) {
        this.orientationInRadians = orientationInRadians;
        return this;
    }

    public AxonTerminalBuilder setLength(double length) {
        this.length = length;
        return this;
    }

    public AxonTerminalBuilder setSignalType(SignalType signalType) {
        this.signalType = signalType;
        return this;
    }

    public AxonTerminal createAxonTerminal() {
        return new AxonTerminal(coordinatesOfBranchBeginning, orientationInRadians, length, signalType);
    }
}