package main.java.generators.factories;

import main.java.neuron.branch.Branch;

public interface BranchFactory<T extends Branch> {

    public T getUnconfiguredBranch();

}
