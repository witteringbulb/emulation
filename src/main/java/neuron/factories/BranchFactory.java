package neuron.factories;

import neuron.branch.AxonTerminal;
import neuron.branch.Branch;
import neuron.branch.Dendrite;
import neuron.builders.AxonTerminalBuilder;
import neuron.builders.DendriteBuilder;
import neuron.signal.SignalType;

public class BranchFactory {

    public static Branch getBranch(Class branchType,
                                   double[] coordinatesOfBranchBeginning,
                                   double orientationInRadians,
                                   double length,
                                   SignalType signalType) {
        if (coordinatesOfBranchBeginning.length != 2) {
            throw new IllegalArgumentException("Coordinates should be of length 2");
        }
        if (branchType == Dendrite.class) {
            return new DendriteBuilder().setOrientationInRadians(orientationInRadians)
                                        .setLength(length)
                                        .setSignalType(signalType)
                                        .setCoordinatesOfBranchBeginning(coordinatesOfBranchBeginning)
                                        .createDendrite();
        } else if (branchType == AxonTerminal.class) {
            return new AxonTerminalBuilder().setOrientationInRadians(orientationInRadians)
                                            .setLength(length)
                                            .setSignalType(signalType)
                                            .setCoordinatesOfBranchBeginning(coordinatesOfBranchBeginning)
                                            .createAxonTerminal();
        } else {
            throw new IllegalArgumentException("branch Type not recognised");
        }
    }

}
