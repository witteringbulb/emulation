package neuron.signal;

import defaults.DefaultValues;
import neuron.branch.Branch;

public abstract class Signal {

    private Branch parentBranch;

    private double[] meanLocation;

    private double distanceFromSignalMeanToSignalOrigin = 0.0;

    private double amplitude;
    private double width;
    private double displacement;

    public Signal(double width, double displacement, double amplitude, Branch parentBranch) {
        this.width = width;
        this.displacement = displacement;
        this.amplitude = amplitude;
        this.parentBranch = parentBranch;
        this.meanLocation = parentBranch.getCoordinatesOfBranchBeginning();
    }

    public void propagateOneTimeIncrement() {
        this.updateDistanceFromSignalMeanToSignalOrigin();
    }

    private void updateDistanceFromSignalMeanToSignalOrigin() {
        this.distanceFromSignalMeanToSignalOrigin += DefaultValues.DEFAULT_SIGNAL_VELOCITY;
        this.meanLocation[0] += DefaultValues.DEFAULT_SIGNAL_VELOCITY * Math.cos(parentBranch.getOrientationInRadians());
        this.meanLocation[1] += DefaultValues.DEFAULT_SIGNAL_VELOCITY * Math.sin(parentBranch.getOrientationInRadians());
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

    public double getDisplacement() { return displacement; }



}
