package neuron.branch;

import neuron.signal.Signal;

public class Dendrite<T extends Signal> extends Branch {

    public Dendrite(double orientationInRadians,
                     double length,
                     Class<T> signalType,
                     double signalWidth,
                     double signalDisplacement) {
        super(orientationInRadians, length, signalType, signalWidth, signalDisplacement);
    }

}
