package main.java.neuron;

import main.java.neuron.branch.Axon;
import main.java.neuron.soma.Soma;

public class Neuron {

    private Soma soma;
    private Axon axon;

    public Neuron(Soma soma, Axon axon) {
        this.soma = soma;
        soma.setParentNeuron(this);
        this.axon = axon;
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
