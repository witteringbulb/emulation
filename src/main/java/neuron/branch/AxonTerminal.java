package neuron.branch;

import neuron.signal.SignalType;

public class AxonTerminal extends Branch {

    public AxonTerminal(double orientationInRadians,
                        double length,
                        SignalType signalType) {
        super(orientationInRadians, length, signalType);
    }

}
