package neuron.signal;

import defaults.DefaultValues;
import neuron.branch.Branch;

public abstract class Signal {

    private final Branch parentBranch;

    private double[] meanLocation;
    private final double[] signalXYDirectionUnitVector;

    private double distanceFromSignalMeanToSignalOrigin = 0.0;

    private double amplitude;
    private double width;

    public Signal(double width, double amplitude, Branch parentBranch) {
        this.width = width;
        this.amplitude = amplitude;
        this.parentBranch = parentBranch;
        this.meanLocation = parentBranch.getCoordinatesOfBranchBeginning();

        this.signalXYDirectionUnitVector = new double[]{Math.sin(parentBranch.getOrientationInRadians()),
                Math.cos(parentBranch.getOrientationInRadians())};
    }

    public void propagateOneTimeIncrement() {
        this.updateDistanceFromSignalMeanToSignalOrigin();
    }

    private void updateDistanceFromSignalMeanToSignalOrigin() {
        this.distanceFromSignalMeanToSignalOrigin += DefaultValues.DEFAULT_SIGNAL_SPEED;
        this.meanLocation[0] += DefaultValues.DEFAULT_SIGNAL_SPEED * this.signalXYDirectionUnitVector[0];
        this.meanLocation[1] += DefaultValues.DEFAULT_SIGNAL_SPEED * this.signalXYDirectionUnitVector[1];
    }

    public double getSignalStrengthAtLocation(double position) {
        return this.amplitude * signalDistributionFunction(position);
    }

    public boolean hasFullyPassedPosition(double position) {
        return this.getDistanceFromSignalMeanToSignalOrigin() > position
                && this.getSignalStrengthAtLocation(position) < DefaultValues.DEFAULT_ZERO_SIGNAL_THRESH;
    }

    public abstract double signalDistributionFunction(double position);

    public double[] getMeanLocation() { return meanLocation; }

    public double getAmplitude() { return amplitude; }

    public double getDistanceFromSignalMeanToSignalOrigin() {
        return distanceFromSignalMeanToSignalOrigin;
    }

    public double getWidth() { return width; }

    public double[] getDirection() { return this.signalXYDirectionUnitVector; }

    public abstract String getTypeAsString();

}
