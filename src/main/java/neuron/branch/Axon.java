package neuron.branch;

import neuron.signal.SignalType;

import java.util.List;

public class Axon extends Branch {

    public Axon(double orientationInRadians,
                double length,
                SignalType signalType,
                List<AxonTerminal> axonTerminals) {
        super(orientationInRadians, length, signalType);
        this.axonTerminals = axonTerminals;
    }

    private List<AxonTerminal> axonTerminals;

}
