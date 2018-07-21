package main.java.neuron.branch;

import java.util.List;

public class Axon extends Branch {

    private List<AxonTerminal> axonTerminals;

    public Axon(double orientationInRadians, double length) {
        super(orientationInRadians, length);
    }

    public void setAxonTerminals(List<AxonTerminal> axonTerminals) {
        this.axonTerminals = axonTerminals;
    }


}
