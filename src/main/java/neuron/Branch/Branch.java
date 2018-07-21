package main.java.neuron.Branch;

import main.java.neuron.Signal.Signal;

import java.util.List;

public abstract class Branch<T extends Signal> {

    private double orientationInRadians;
    private double length;

    private List<T> signals;

    public void propagateSignals() {
        for (Signal signal : signals) {
            if (signal.hasFullyPassedPosition(length)) {
                signals.remove(signal);
            }
            signal.propagate();
        }
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
