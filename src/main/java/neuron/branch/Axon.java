package main.java.neuron.branch;

import java.util.List;

public class Axon extends Branch {

    private List<AxonTerminal> axonTerminals;

    public void setAxonTerminals(List<AxonTerminal> axonTerminals) {
        this.axonTerminals = axonTerminals;
    }


}
