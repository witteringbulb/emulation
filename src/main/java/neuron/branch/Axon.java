package main.java.neuron.branch;

import main.java.neuron.signal.Signal;

import java.util.List;

public class Axon<T extends Signal> extends Branch {

    public Axon(double orientationInRadians,
                    double length,
                    Class<T> signalType,
                    double signalWidth,
                    double signalDisplacement,
                    List<AxonTerminal> axonTerminals) {
        super(orientationInRadians, length, signalType, signalWidth, signalDisplacement);
        this.axonTerminals = axonTerminals;
    }

    private List<AxonTerminal> axonTerminals;

    public void fire() {
        this.addSignal();
    }

}
