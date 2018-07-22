package neuron.factories;

import neuron.branch.AxonTerminal;
import neuron.branch.Branch;
import neuron.branch.Dendrite;
import neuron.builders.AxonTerminalBuilder;
import neuron.builders.DendriteBuilder;

public class BranchFactory {

    public static Branch getBranch(Class branchType,
                                   double orientationInRadians,
                                   double length,
                                   Class signalType,
                                   double signalWidth,
                                   double signalDisplacement) {
        if (branchType == Dendrite.class) {
            return new DendriteBuilder().setOrientationInRadians(orientationInRadians)
                                        .setLength(orientationInRadians)
                                        .setSignalType(signalType)
                                        .setSignalWidth(signalWidth)
                                        .setSignalDisplacement(signalDisplacement)
                                        .createDendrite();
        } else if (branchType == AxonTerminal.class) {
            return new AxonTerminalBuilder().setOrientationInRadians(orientationInRadians)
                                            .setLength(orientationInRadians)
                                            .setSignalType(signalType)
                                            .setSignalWidth(signalWidth)
                                            .setSignalDisplacement(signalDisplacement)
                                            .createAxonTerminal();
        } else {
            throw new IllegalArgumentException("branch Type not recognised");
        }
    }

}
