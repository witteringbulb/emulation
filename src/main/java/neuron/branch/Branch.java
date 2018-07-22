package neuron.branch;

import java.util.ArrayList;
import java.util.Iterator;
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

    public void addSignal(double amplitude) {
        signals.add( (T) SignalFactory.getSignal(signalType, amplitude));
    }

    public void propagateSignalsOneTimeIncrement() {
        Iterator<T> signalsIterator = signals.iterator();
        while (signalsIterator.hasNext()) {
            Signal signal = signalsIterator.next();
            signal.propagateOneTimeIncrement();
            if (signal.hasFullyPassedPosition(length)) {
                signalsIterator.remove();
            }
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
