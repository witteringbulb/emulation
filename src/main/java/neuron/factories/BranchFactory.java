package neuron.factories;

import neuron.branch.AxonTerminal;
import neuron.branch.Branch;
import neuron.branch.Dendrite;
import neuron.builders.AxonTerminalBuilder;
import neuron.builders.DendriteBuilder;
import neuron.signal.SignalType;

public class BranchFactory {

    public static Branch getBranch(Class branchType,
                                   double orientationInRadians,
                                   double length,
                                   SignalType signalType) {
        if (branchType == Dendrite.class) {
            return new DendriteBuilder().setOrientationInRadians(orientationInRadians)
                                        .setLength(length)
                                        .setSignalType(signalType)
                                        .createDendrite();
        } else if (branchType == AxonTerminal.class) {
            return new AxonTerminalBuilder().setOrientationInRadians(orientationInRadians)
                                            .setLength(length)
                                            .setSignalType(signalType)
                                            .createAxonTerminal();
        } else {
            throw new IllegalArgumentException("branch Type not recognised");
        }
    }

}
