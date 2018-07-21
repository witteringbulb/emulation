package main.java.generators.Factories;

import main.java.neuron.Branch.Branch;

public interface BranchFactory<T extends Branch> {

    public T getUnconfiguredBranch();

}
