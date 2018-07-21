package main.java.neuron.branch;

import main.java.neuron.factories.SignalFactory;
import main.java.neuron.signal.Signal;

import java.util.List;

public abstract class Branch<T extends Signal> {

    private double orientationInRadians;
    private double length;

    private Class signalType;
    private double signalWidth;
    private double signalDisplacement;

    private List<T> signals;

    public Branch(double orientationInRadians,
                  double length,
                  Class<T> signalType,
                  double signalWidth,
                  double signalDisplacement) {
        this.orientationInRadians = orientationInRadians;
        this.length = length;
        this.signalType = signalType;
        this.signalWidth = signalWidth;
        this.signalDisplacement = signalDisplacement;
    }

    public void propagateSignalsOneTimeIncrement() {
        for (Signal signal : signals) {
            if (signal.hasFullyPassedPosition(length)) {
                signals.remove(signal);
            }
            signal.propagateOneTimeIncrement();
        }
    }

    public void addSignal() {
        signals.add( (T) SignalFactory.getSignal(signalType, this.signalWidth, this.signalDisplacement));
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
