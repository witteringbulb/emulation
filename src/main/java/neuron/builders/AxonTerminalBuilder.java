package neuron.builders;

import neuron.branch.AxonTerminal;
import neuron.signal.Signal;

public class AxonTerminalBuilder<T extends Signal> {
    private double orientationInRadians;
    private double length;
    private Class<T> signalType;
    private double signalWidth;
    private double signalDisplacement;

    public AxonTerminalBuilder setOrientationInRadians(double orientationInRadians) {
        this.orientationInRadians = orientationInRadians;
        return this;
    }

    public AxonTerminalBuilder setLength(double length) {
        this.length = length;
        return this;
    }

    public AxonTerminalBuilder setSignalType(Class<T> signalType) {
        this.signalType = signalType;
        return this;
    }

    public AxonTerminalBuilder setSignalWidth(double signalWidth) {
        this.signalWidth = signalWidth;
        return this;
    }

    public AxonTerminalBuilder setSignalDisplacement(double signalDisplacement) {
        this.signalDisplacement = signalDisplacement;
        return this;
    }

    public AxonTerminal createAxonTerminal() {
        return new AxonTerminal(orientationInRadians, length, signalType, signalWidth, signalDisplacement);
    }
}