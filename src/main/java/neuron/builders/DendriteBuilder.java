package neuron.builders;

import neuron.branch.Dendrite;
import neuron.signal.Signal;

public class DendriteBuilder<T extends Signal> {
    private double orientationInRadians;
    private double length;
    private Class<T> signalType;
    private double signalWidth;
    private double signalDisplacement;

    public DendriteBuilder setOrientationInRadians(double orientationInRadians) {
        this.orientationInRadians = orientationInRadians;
        return this;
    }

    public DendriteBuilder setLength(double length) {
        this.length = length;
        return this;
    }

    public DendriteBuilder setSignalType(Class<T> signalType) {
        this.signalType = signalType;
        return this;
    }

    public DendriteBuilder setSignalWidth(double signalWidth) {
        this.signalWidth = signalWidth;
        return this;
    }

    public DendriteBuilder setSignalDisplacement(double signalDisplacement) {
        this.signalDisplacement = signalDisplacement;
        return this;
    }

    public Dendrite createDendrite() {
        return new Dendrite(orientationInRadians, length, signalType, signalWidth, signalDisplacement);
    }
}