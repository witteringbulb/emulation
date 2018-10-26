package neuron;

import neuron.branch.Axon;
import neuron.branch.Branch;
import neuron.soma.Soma;

import java.util.List;

public class InternalNeuron extends Neuron {

    private Soma soma;

    public InternalNeuron(Soma soma, Axon axon, boolean inhibitory) {
        this.soma = soma;
        soma.setParentNeuron(this);
        this.setAxon(axon);
        this.getAxon().setInhibitory(inhibitory);
    }

    @Override
    public void propagateSignalsOneTimeIncrement() {
        this.getSoma().propagateDendriteSignalsOneTimeIncrementAndFireAxonIfRequired(); //Needs to fire axon if need be
        super.propagateSignalsOneTimeIncrement();
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

}
