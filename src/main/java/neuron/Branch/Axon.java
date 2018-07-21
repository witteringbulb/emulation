package main.java.neuron.Branch;

import main.java.neuron.Branch.AxonTerminal;
import main.java.neuron.Branch.Branch;

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
