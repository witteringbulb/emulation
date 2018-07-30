package neuron.factories;

import neuron.branch.AxonTerminal;
import neuron.branch.Branch;
import neuron.branch.Dendrite;
import neuron.builders.AxonTerminalBuilder;
import neuron.builders.DendriteBuilder;
import neuron.signal.SignalType;

import java.util.ArrayList;
import java.util.List;

public class BranchFactory<T extends Branch> {

    private final Class<T> branchType;
    private final double[] coordinatesOfBranchBeginning;
    private final SignalType signalType;

    public BranchFactory(Class<T> type, double[] coordinatesOfBranchBeginning, SignalType signalType) {
        if (coordinatesOfBranchBeginning.length != 2) {
            throw new IllegalArgumentException("Coordinates should be of length 2");
        }
        this.coordinatesOfBranchBeginning = coordinatesOfBranchBeginning;
        this.branchType = type;
        this.signalType = signalType;
    }

    public List<Branch> getBranchesFromArrayssOfEndpointCoordinates(double[] xCoordinatesOfEndpoints, double[] yCoordinatesOfEndpoints) {
        List<Branch> branches = new ArrayList<Branch>();
        //TODO: Add branches as needed
    }

    public Branch getBranch(double orientationInRadians, double length) {
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
