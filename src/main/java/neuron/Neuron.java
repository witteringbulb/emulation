package main.java.neuron;

import main.java.neuron.branch.Axon;
import main.java.neuron.branch.AxonTerminal;
import main.java.neuron.soma.Soma;

import java.util.List;

public class Neuron {

    private Soma soma;
    private Axon axon;

    public Neuron(Soma soma, Axon axon, List<AxonTerminal> axonTerminals) {
        this.soma = soma;
        soma.setParentNeuron(this);
        this.axon = axon;
        this.axon.setAxonTerminals(axonTerminals);
    }

    public Soma getSoma() {
        return soma;
    }
    public Axon getAxon() {
        return axon;
    }

    public void fireAxon() {
        this.axon.fire();
    }

}
