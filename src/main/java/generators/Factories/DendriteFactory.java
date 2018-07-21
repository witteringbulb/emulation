package main.java.generators.Factories;

import main.java.neuron.Branch.Dendrite;

public class DendriteFactory implements BranchFactory<Dendrite> {

    public Dendrite getUnconfiguredBranch() {
        return new Dendrite();
    }

}
