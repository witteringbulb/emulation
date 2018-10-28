package neuron;

import defaults.DefaultValues;
import neuron.branch.Axon;
import neuron.branch.Branch;
import neuron.soma.Soma;

import java.util.ArrayList;
import java.util.List;

public class Neuron {

    private Soma soma;

    public Neuron(Soma soma, Axon axon, boolean inhibitory) {
        this.soma = soma;
        soma.setParentNeuron(this);
        this.setAxon(axon);
        this.getAxon().setInhibitory(inhibitory);
    }

    public void propagateSignalsOneTimeIncrement() {
        this.getSoma().propagateDendriteSignalsOneTimeIncrementAndFireAxonIfRequired(); //Needs to fire axon if need be
        this.getAxon().propagateAxonAndAxonTerminalSignalsForwardOneTimeIncrement();
    }

    public Soma getSoma() {
        return soma;
    }

    public List<Branch> getAllBranchesInSingleList() {
        List<Branch> allBranches = getAxonAndAllAxonTerminals();
        for (Branch branch : this.getSoma().getDendrites()) {
            allBranches.add(branch);
        }
        return allBranches;
    }

    public boolean inhibitory = false;

    private final double AXON_FIRE_AMPLITUDE = DefaultValues.DEFAULT_SIGNAL_AMPLITUDE;

    private Axon axon;

    public void fireAxon() {
        this.axon.fire(AXON_FIRE_AMPLITUDE);
    }

    private List<Branch> getAxonAndAllAxonTerminals() {
        List<Branch> axonAndAllAxonTerminals = new ArrayList<Branch>();
        axonAndAllAxonTerminals.add(this.getAxon());
        if (this.getAxon().getAxonTerminals() != null) {
            for (Branch branch : this.getAxon().getAxonTerminals()) {
                axonAndAllAxonTerminals.add(branch);
            }
        }
        return axonAndAllAxonTerminals;
    }

    public Axon getAxon() {
        return axon;
    }
    public void setAxon(Axon axon) {this.axon = axon;}

    public boolean isInhibitory() {
        return this.inhibitory;
    }

}
