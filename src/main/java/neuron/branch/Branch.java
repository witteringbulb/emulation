package main.java.neuron.branch;

import main.java.neuron.signal.Signal;

import java.util.List;

public abstract class Branch<T extends Signal> {

    private double orientationInRadians;
    private double length;

    private List<T> signals;

    public void propagateSignalsOneTimeIncrement() {
        for (Signal signal : signals) {
            if (signal.hasFullyPassedPosition(length)) {
                signals.remove(signal);
            }
            signal.propagateOneTimeIncrement();
        }
    }

    public double getSignalMagnitudeAtEndOfBranch() {
        return signals.stream().mapToDouble(signal -> signal.getSignalStrengthAtLocation(length)).sum();
    }

    public double getOrientationInRadians() {
        return this.orientationInRadians;
    }

    public void setOrientationInRadians(double orientationInRadians) { this.orientationInRadians = orientationInRadians; }

    public double getLength() {
        return length;
    }

    public void setLength(double length) { this.length = length; }

}
