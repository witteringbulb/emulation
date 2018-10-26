package neuron;

import defaults.DefaultValues;
import neuron.branch.Axon;
import neuron.branch.Branch;

import java.util.ArrayList;
import java.util.List;

public abstract class Neuron {

    public boolean inhibitory = false;

    private final double AXON_FIRE_AMPLITUDE = DefaultValues.DEFAULT_SIGNAL_AMPLITUDE;

    private Axon axon;

    public void fireAxon() {
        this.axon.fire(AXON_FIRE_AMPLITUDE);
    }

    public void propagateSignalsOneTimeIncrement() {
        this.getAxon().propagateAxonAndAxonTerminalSignalsForwardOneTimeIncrement();
    }

    public abstract List<Branch> getAllBranchesInSingleList();

    public List<Branch> getAxonAndAllAxonTerminals() {
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
