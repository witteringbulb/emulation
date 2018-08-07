package neuron;

import defaults.DefaultValues;
import neuron.branch.Axon;
import neuron.branch.Branch;
import neuron.soma.Soma;

import java.util.ArrayList;
import java.util.List;

public class Neuron {

    private final double AXON_FIRE_AMPLITUDE = DefaultValues.DEFAULT_SIGNAL_AMPLITUDE;

    private Soma soma;
    private Axon axon;

    public Neuron(Soma soma, Axon axon) {
        this.soma = soma;
        soma.setParentNeuron(this);
        this.axon = axon;
    }

    public void fireAxon() {
        this.axon.fireIfAllowed(AXON_FIRE_AMPLITUDE);
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

    public List<Branch> getAllBranchesInSingleList() {
        List<Branch> allBranches = new ArrayList<Branch>();
        allBranches.add(this.getAxon());
        for (Branch branch : this.getAxon().getAxonTerminals()) {
            allBranches.add(branch);
        }
        for (Branch branch : this.getSoma().getDendrites()) {
            allBranches.add(branch);
        }
        return allBranches;
    }

}
