package neuron.branchGeneration;

import neuron.branch.Branch;
import neuron.factories.BranchFactory;

import java.util.List;

public class BranchGenerator<T extends Branch> {

    private final double[] coordinatesOfStartOfBranch;
    private final BranchFactory<T> branchFactory;
    private final Class branchType;

    public BranchGenerator(double[] coordinatesOfStartOfBranch, Class<T> type) {
        if (coordinatesOfStartOfBranch.length != 2) {
            throw new IllegalArgumentException("coordinates must be a n array of length 2");
        }
        this.coordinatesOfStartOfBranch = coordinatesOfStartOfBranch;
        this.branchFactory = new BranchFactory(type, coordinatesOfStartOfBranch);
        this.branchType = type;
    }

    public List<T> createBranchesFromArraysOfEndpointCoordinates(double[] xCoordinates, double[] yCoordinates) {
        //TODO: Remember to change BranchFactory and Branch builder to take xy coordinates of start and end, not length and orientation in radians
        //TODO: Call factory method to create list
    }

    public T generateBranch(double xCoordinateOfEndPoint, double yCoordinateOfEndPoint) {
        return this.branchFactory.getBranch(xCoordinateOfEndPoint, yCoordinateOfEndPoint);
    }

}
