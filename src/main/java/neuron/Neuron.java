package neuron;

import neuron.branch.Axon;
import neuron.soma.Soma;

public class Neuron {

    private Soma soma;
    private Axon axon;

    public Neuron(Soma soma, Axon axon) {
        this.soma = soma;
        soma.setParentNeuron(this);
        this.axon = axon;
    }

    public void fireAxon() {
        this.axon.fire();
    }

    public void propagateSignalsOneTimeIncrement() {
        this.getSoma().propagateDendriteSignalsOneTimeIncrementAndFireAxonIfRequired(); //Needs to fire axon if need be
        this.getAxon().propagateAxonAndAxonTerminalSignalsForwardOneTimeIncrement();
    }

    public Soma getSoma() {
        return soma;
    }
    public Axon getAxon() {
        return axon;
    }

}
