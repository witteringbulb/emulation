package neuron.builders;

import neuron.branch.AxonTerminal;
import neuron.signal.SignalType;

public class AxonTerminalBuilder {
    private double[] coordinatesOfBranchBeginning;
    private double[] coordinatesOfBranchEnd;
    private SignalType signalType;

    public AxonTerminalBuilder setCoordinatesOfBranchBeginning(double[] coordinatesOfBranchBeginning) {
        this.coordinatesOfBranchBeginning = coordinatesOfBranchBeginning;
        return this;
    }

    public AxonTerminalBuilder setCoordinatesOfBranchEnd(double[] coordinatesOfBranchEnd) {
        this.coordinatesOfBranchEnd = coordinatesOfBranchEnd;
        return this;
    }

    public AxonTerminalBuilder setSignalType(SignalType signalType) {
        this.signalType = signalType;
        return this;
    }

    public AxonTerminal createAxonTerminal() {
        return new AxonTerminal(coordinatesOfBranchBeginning, coordinatesOfBranchEnd, signalType);
    }
}