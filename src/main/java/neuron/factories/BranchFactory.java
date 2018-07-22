package neuron.factories;

import neuron.branch.AxonTerminal;
import neuron.branch.Branch;
import neuron.branch.Dendrite;

public class BranchFactory {

    public static Branch getBranch(Class branchType,
                                   double orientationInRadians,
                                   double length,
                                   Class signalType,
                                   double signalWidth,
                                   double signalDisplacement) {
        if (branchType == Dendrite.class) {
            return dendriteBuilder.build()(orientationInRadians, length, signalType, signalWidth, signalDisplacement);
        } else if (branchType == AxonTerminal.class) {
            return new AxonTerminal(orientationInRadians, length, signalType, signalWidth, signalDisplacement);
        } else {
            throw new IllegalArgumentException("branch Type not recognised");
        }
    }

}
