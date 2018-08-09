package neuron.branch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import defaults.DefaultValues;
import neuron.factories.SignalFactory;
import neuron.signal.Signal;
import neuron.signal.SignalType;

public abstract class Branch {

    private final int uniqueId;

    private final double[] coordinatesOfBranchBeginning;
    private double[] coordinatesOfBranchEnd;
    private final SignalType signalType;

    private final SignalFactory signalFactory;

    private double length;

    private List<Signal> signals = new ArrayList<Signal>();

    public Branch(double[] coordinatesOfBranchBeginning,
                  double[] coordinatesOfBranchEnd,
                  SignalType signalType) {
        if (coordinatesOfBranchBeginning.length !=2 || coordinatesOfBranchEnd.length != 2) {
            throw new IllegalArgumentException("Coordinates arrays must both be of length 2");
        }

        this.uniqueId = BranchIdGeneratorStatic.getNextAvailableUniqueId();

        this.coordinatesOfBranchBeginning = coordinatesOfBranchBeginning;
        this.coordinatesOfBranchEnd = coordinatesOfBranchEnd;
        this.signalType = signalType;
        this.signalFactory = new SignalFactory(this.signalType);
    }

    public void addSignal(double amplitude) {
        signals.add( signalFactory.getSignal(amplitude, this));
    }

    public void propagateSignalsOneTimeIncrement() {
        Iterator<Signal> signalsIterator = signals.iterator();
        while (signalsIterator.hasNext()) {
            Signal signal = signalsIterator.next();
            signal.propagateOneTimeIncrement();
            if (signal.hasFullyPassedPosition(this.getLength())) {
                signalsIterator.remove();
            }
        }
    }

    public double getSignalMagnitudeAtEndOfBranch() {
        return signals.stream().mapToDouble(signal -> signal.getSignalStrengthAtLocation(this.getLength())).sum();
    }

    public double[] getCoordinatesOfBranchBeginning() {
        return this.coordinatesOfBranchBeginning;
    }
    public double[] getCoordinatesOfBranchEnd() {
        return this.coordinatesOfBranchEnd;
    }

    public double getLength() {
        if (this.length == 0.0d) {
            this.length = Math.sqrt(
                    Math.pow(this.coordinatesOfBranchEnd[0] - this.coordinatesOfBranchBeginning[0], 2.0)
                            + Math.pow(this.coordinatesOfBranchEnd[1] - this.coordinatesOfBranchBeginning[1], 2.0));
        }
        return this.length;
    }

    public abstract void fire(double amplitude);

    public int getBranchUniqueId() {
        return  this.uniqueId;
    }

    public abstract String getBranchType();

    public List<Signal> getSignals() { return signals; }

}
