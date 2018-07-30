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
        if (xCoordinatesOfEndpoints.length != yCoordinatesOfEndpoints.length) {
            throw new IllegalArgumentException("arrays must be of equal length");
        }
        List<Branch> branches = new ArrayList<Branch>();
        for (int index = 0; index < xCoordinatesOfEndpoints.length; index++) {
            branches.add(getBranch(xCoordinatesOfEndpoints[index], yCoordinatesOfEndpoints[index]));
        }
        return branches;
    }

    public Branch getBranch(double xCoordinateOfBranchEnd, double yCoordinateOfBranchEnd) {
        if (branchType == Dendrite.class) {
            return new DendriteBuilder().setSignalType(signalType)
                                        .setCoordinatesOfBranchBeginning(coordinatesOfBranchBeginning)
                                        .setCoordinatesOfBranchEnd(new double[]{xCoordinateOfBranchEnd, yCoordinateOfBranchEnd})
                                        .createDendrite();
        } else if (branchType == AxonTerminal.class) {
            return new AxonTerminalBuilder().setSignalType(signalType)
                                            .setCoordinatesOfBranchBeginning(coordinatesOfBranchBeginning)
                                            .setCoordinatesOfBranchEnd(new double[]{xCoordinateOfBranchEnd, yCoordinateOfBranchEnd})
                                            .createAxonTerminal();
        } else {
            throw new IllegalArgumentException("branch Type not recognised");
        }
    }

}
