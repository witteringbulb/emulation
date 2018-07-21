package main.java.neuron.branch;

import main.java.neuron.signal.Signal;

import java.util.List;

public class Axon<T extends Signal> extends Branch {

    public Axon(double orientationInRadians,
                    double length,
                    Class<T> signalType,
                    double signalWidth,
                    double signalDisplacement) {
        super(orientationInRadians, length, signalType, signalWidth, signalDisplacement);
    }

    private List<AxonTerminal> axonTerminals;

    public void setAxonTerminals(List<AxonTerminal> axonTerminals) {
        this.axonTerminals = axonTerminals;
    }

    public void fire() {
        this.addSignal();
    }

}
