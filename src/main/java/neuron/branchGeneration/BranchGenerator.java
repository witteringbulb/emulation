package neuron.branchGeneration;

import neuron.branch.Branch;
import neuron.factories.BranchFactory;
import neuron.signal.SignalType;

import java.util.ArrayList;
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

    public List<Branch> createEvenlySpacedBranchesOfEqualLength(double radius, int numberOfBranches) {
        List<List<Double>> branchEndpointCoordinates = this.generateEvenlySpacedPointsWithRandomOffset(radius, numberOfBranches);
        return this.createBranchesFromListsOfEndpointCoordinates(branchEndpointCoordinates.get(0), branchEndpointCoordinates.get(1));
    }

    public List<List<Double>> generateEvenlySpacedPointsWithRandomOffset(double radius, int numberOfPoints) {
        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        double offset = Math.random()*2*Math.PI;

        for (int i = 0; i < numberOfPoints; i++) {
            xValues.add(coordinatesOfStartOfBranch[0] + radius * Math.sin(offset + i * (2*Math.PI)/numberOfPoints));
            yValues.add(coordinatesOfStartOfBranch[1] + radius * Math.cos(offset + i * (2*Math.PI)/numberOfPoints));
        }

        List<List<Double>> coordinates = new ArrayList<>();
        coordinates.add(xValues);
        coordinates.add(yValues);
        return coordinates;
    }

    public List<Branch> createBranchesFromListsOfEndpointCoordinates(List<Double> xCoordinates, List<Double> yCoordinates) {
        return branchFactory.getBranchesFromArrayssOfEndpointCoordinates(xCoordinates, yCoordinates);
    }

    public Branch generateBranch(double xCoordinateOfEndPoint, double yCoordinateOfEndPoint) {
        return this.branchFactory.getBranch(xCoordinateOfEndPoint, yCoordinateOfEndPoint);
    }

}
