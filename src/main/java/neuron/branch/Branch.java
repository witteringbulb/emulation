package neuron.branch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import neuron.factories.SignalFactory;
import neuron.signal.Signal;
import neuron.signal.SignalType;

public abstract class Branch {

    private double[] coordinatesOfBranchBeginning;
    private double[] coordinatesOfBranchEnd;
    private SignalType signalType;

    private double length;
    private double orientationInRadians;

    private List<Signal> signals = new ArrayList<Signal>();

    public Branch(double[] coordinatesOfBranchBeginning,
                  double[] coordinatesOfBranchEnd,
                  SignalType signalType) {
        if (coordinatesOfBranchBeginning.length !=2 || coordinatesOfBranchEnd.length != 2) {
            throw new IllegalArgumentException("Coordinates arrays must both be of length 2");
        }
        this.coordinatesOfBranchBeginning = coordinatesOfBranchBeginning;
        this.coordinatesOfBranchEnd = coordinatesOfBranchEnd;
        this.signalType = signalType;
    }

    public void addSignal(double amplitude) {
        signals.add( SignalFactory.getSignal(signalType, amplitude));
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

    public double getOrientationInRadians() {
        //TODO: TEST THIS
        if (this.orientationInRadians == 0.0d) {
            this.orientationInRadians = Math.PI/2 + Math.atan(
                    this.coordinatesOfBranchEnd[0]-this.coordinatesOfBranchBeginning[0]
                            /(this.coordinatesOfBranchEnd[1]-this.coordinatesOfBranchBeginning[1]));
            if (this.coordinatesOfBranchBeginning[0] > this.coordinatesOfBranchEnd[0]) {
                this.orientationInRadians += Math.PI;
            }
        }
        return this.orientationInRadians;
    }

    public List<Signal> getSignals() {return signals;}

    public double[] getBranchLocationInfo() {
        return new double[]{this.getCoordinatesOfBranchBeginning()[0],
                this.getCoordinatesOfBranchBeginning()[1],
                this.getCoordinatesOfBranchEnd()[0],
                this.getCoordinatesOfBranchEnd()[1]};
    }

}
