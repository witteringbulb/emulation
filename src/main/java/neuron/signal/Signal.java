package neuron.signal;

import defaults.DefaultValues;
import neuron.branch.Branch;

public abstract class Signal {

    private final Branch parentBranch;

    private double distanceFromSignalMeanToSignalOrigin = 0.0;

    private double amplitude;
    private double width;
    private double signalSpeed;

    public Signal(double width, double amplitude, Branch parentBranch) {
        this.width = width;
        this.amplitude = amplitude;
        this.signalSpeed = DefaultValues.DEFAULT_SIGNAL_SPEED;
        this.parentBranch = parentBranch;
    }

    public void propagateOneTimeIncrement() {
        this.updateDistanceFromSignalMeanToSignalOrigin();
    }

    private void updateDistanceFromSignalMeanToSignalOrigin() {
        this.distanceFromSignalMeanToSignalOrigin += this.signalSpeed;
    }

    public double getSignalStrengthAtLocation(double position) {
        return this.amplitude * signalDistributionFunction(position);
    }

    public boolean hasFullyPassedPosition(double position) {
        return this.getDistanceFromSignalMeanToSignalOrigin() > position
                && this.getSignalStrengthAtLocation(position) < DefaultValues.DEFAULT_ZERO_SIGNAL_THRESH;
    }

    public abstract double signalDistributionFunction(double position);

    public double getAmplitude() { return amplitude; }

    public double getDistanceFromSignalMeanToSignalOrigin() {
        return distanceFromSignalMeanToSignalOrigin;
    }

    public double getWidth() { return width; }

    public abstract String getTypeAsString();

    public Branch getParentBranch() {
        return this.parentBranch;
    }

}
