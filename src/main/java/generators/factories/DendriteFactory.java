package main.java.generators.factories;

import main.java.neuron.branch.Dendrite;

public class DendriteFactory implements BranchFactory<Dendrite> {

    public Dendrite getUnconfiguredBranch() {
        return new Dendrite();
    }

}
