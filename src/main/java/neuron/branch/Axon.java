package main.java.neuron.branch;

import main.java.neuron.signal.Signal;

import java.util.List;

public class Axon<T extends Signal> extends Branch {

    private List<AxonTerminal> axonTerminals;
    private List<T> signals;

    public void setAxonTerminals(List<AxonTerminal> axonTerminals) {
        this.axonTerminals = axonTerminals;
    }

    public void fire() {
        this.signals.add();
    }

}
