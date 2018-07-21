package main.java.neuron.factories;

import main.java.neuron.branch.AxonTerminal;
import main.java.neuron.branch.Branch;
import main.java.neuron.branch.Dendrite;

public class BranchFactory {

    public static Branch getBranch(Class branchType,
                            double orientationInRadians,
                            double length,
                            Class signalType,
                            double signalWidth,
                            double signalDisplacement) {
        if (branchType == Dendrite.class) {
            return new Dendrite(orientationInRadians, length, signalType, signalWidth, signalDisplacement);
        } else if (branchType == AxonTerminal.class) {
            return new AxonTerminal(orientationInRadians, length, signalType, signalWidth, signalDisplacement);
        } else {
            throw new IllegalArgumentException("branch Type not recognised");
        }
    }

}
