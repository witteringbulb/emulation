package neuron;

import neuron.branch.Axon;
import neuron.branch.Branch;
import neuron.soma.Soma;

import java.util.List;

public class ExternalInputNeuron extends Neuron {

    public ExternalInputNeuron(Axon axon, boolean inhibitory) {
        this.setAxon(axon);
        this.getAxon().setInhibitory(inhibitory);
    }

    public List<Branch> getAllBranchesInSingleList() {
        return this.getAxonAndAllAxonTerminals();
    }

}
