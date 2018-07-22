package neuron.signal;

import defaults.DefaultValues;

public abstract class Signal {

    private double distanceFromSignalMeanToSignalOrigin = 0.0;

    private double amplitude;
    private double width;
    private double displacement;

    public Signal(double width, double displacement, double amplitude) {
        this.width = width;
        this.displacement = displacement;
        this.amplitude = amplitude;
    }

    public void propagateOneTimeIncrement() {
        this.updateDistanceFromSignalMeanToSignalOrigin();
    }

    private void updateDistanceFromSignalMeanToSignalOrigin() {
        this.distanceFromSignalMeanToSignalOrigin += DefaultValues.DEFAULT_SIGNAL_VELOCITY;
    }

    public double getSignalStrengthAtLocation(double position) {
        return this.amplitude * signalDistributionFunction(position);
    }

    public boolean hasFullyPassedPosition(double position) {
        return this.getDistanceFromSignalMeanToSignalOrigin() > position
                && this.getSignalStrengthAtLocation(position) < DefaultValues.DEFAULT_ZERO_SIGNAL_THRESH;
    }

    public abstract double signalDistributionFunction(double position);

    public double getDistanceFromSignalMeanToSignalOrigin() {
        return distanceFromSignalMeanToSignalOrigin;
    }

    public double getWidth() { return width; }

    public double getDisplacement() { return displacement; }

}
