package main.java.generators.Factories;

import main.java.neuron.Branch.AxonTerminal;

public class AxonTerminalFactory implements BranchFactory<AxonTerminal> {

    public AxonTerminal getUnconfiguredBranch() {
        return new AxonTerminal();
    }

}
