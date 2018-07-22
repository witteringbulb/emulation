package neuron.signal;

import defaults.DefaultValues;

public abstract class Signal {

    private double distanceFromSignalMeanToSignalOrigin = 0;

    private static final double amplitude = DefaultValues.DEFAULT_SIGNAL_AMPLITUDE;
    private double width;
    private double displacement;

    public Signal(double width, double displacement) {
        this.width = width;
        this.displacement = displacement;
    }

    public void propagateOneTimeIncrement() {
        this.updateDistanceFromSignalMeanToSignalOrigin();
    }

    private void updateDistanceFromSignalMeanToSignalOrigin() {
        this.distanceFromSignalMeanToSignalOrigin += DefaultValues.DEFAULT_SIGNAL_VELOCITY;
    }

    /**
     * Calculates the strength of this signal, using the signal's distribution around its mean
     *
     * @param position The position at which you want to calculate the signal strength.
     *                 This will usually be the end of the branch
     */
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
