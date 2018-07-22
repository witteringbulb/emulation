package neuron.branch;

import java.util.ArrayList;
import java.util.List;

import neuron.factories.SignalFactory;
import neuron.signal.Signal;
import neuron.signal.SignalType;

public abstract class Branch<T extends Signal> {

    private double orientationInRadians;
    private double length;

    private SignalType signalType;

    private List<T> signals = new ArrayList<T>();

    public Branch(double orientationInRadians,
                  double length,
                  SignalType signalType) {
        this.orientationInRadians = orientationInRadians;
        this.length = length;
        this.signalType = signalType;
    }

    public void fire() {
        this.addSignal();
    }

    public void addSignal() {
        signals.add( (T) SignalFactory.getSignal(signalType));
    }

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
