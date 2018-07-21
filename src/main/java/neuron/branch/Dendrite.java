package main.java.neuron.branch;

import main.java.neuron.signal.Signal;

public class Dendrite<T extends Signal> extends Branch {

    public Dendrite(double orientationInRadians,
                     double length,
                     Class<T> signalType,
                     double signalWidth,
                     double signalDisplacement) {
        super(orientationInRadians, length, signalType, signalWidth, signalDisplacement);
    }

}
