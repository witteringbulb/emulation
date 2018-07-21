package main.java.generators.factories;

import main.java.neuron.branch.AxonTerminal;

public class AxonTerminalFactory implements BranchFactory<AxonTerminal> {

    public AxonTerminal getUnconfiguredBranch() {
        return new AxonTerminal();
    }

}
