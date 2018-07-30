package neuron.branchGeneration;

import neuron.branch.Branch;
import neuron.factories.BranchFactory;
import neuron.signal.SignalType;

import java.util.List;

public class BranchGenerator<T extends Branch> {

    private final double[] coordinatesOfStartOfBranch;
    private final BranchFactory<T> branchFactory;
    private final Class branchType;

    public BranchGenerator(double[] coordinatesOfStartOfBranch, Class<T> type, SignalType signalType) {
        if (coordinatesOfStartOfBranch.length != 2) {
            throw new IllegalArgumentException("coordinates must be an array of length 2");
        }
        this.coordinatesOfStartOfBranch = coordinatesOfStartOfBranch;
        this.branchFactory = new BranchFactory(type, coordinatesOfStartOfBranch, signalType);
        this.branchType = type;
    }

    public List<Branch> createBranchesFromArraysOfEndpointCoordinates(double[] xCoordinates, double[] yCoordinates) {
        return branchFactory.getBranchesFromArrayssOfEndpointCoordinates(xCoordinates, yCoordinates);
    }

    public Branch generateBranch(double xCoordinateOfEndPoint, double yCoordinateOfEndPoint) {
        return this.branchFactory.getBranch(xCoordinateOfEndPoint, yCoordinateOfEndPoint);
    }

}
