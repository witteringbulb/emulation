package neuron.branch;

import defaults.DefaultValues;
import neuron.signal.SignalType;

public class AxonTerminal extends Branch {

    public AxonTerminal(double orientationInRadians,
                        double length,
                        SignalType signalType) {
        super(orientationInRadians, length, signalType);
    }

    public void fire() {
        this.addSignal(DefaultValues.DEFAULT_SIGNAL_AMPLITUDE);
    }

}
