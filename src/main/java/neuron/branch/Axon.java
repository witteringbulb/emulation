package neuron.branch;

import neuron.signal.SignalType;

import java.util.List;

public class Axon extends Branch {

    public Axon(double orientationInRadians,
                double length,
                SignalType signalType) {
        super(orientationInRadians, length, signalType);
        this.axonTerminals = axonTerminals;
    }

    private List<AxonTerminal> axonTerminals;

    public void fire() {
        this.addSignal();
    }

}
