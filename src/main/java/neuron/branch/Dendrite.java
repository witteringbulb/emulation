package neuron.branch;

import neuron.signal.SignalType;

public class Dendrite extends Branch {

    public Dendrite(double orientationInRadians,
                     double length,
                     SignalType signalType) {
        super(orientationInRadians, length, signalType);
    }

    public void fire(double amplitude) {
        this.addSignal(amplitude);
    }

}
