package neuron.signal;

import neuron.branch.Branch;

public class SquareSignal extends Signal {

    public SquareSignal(double width, double amplitude, Branch parentBranch) {
        super(width, amplitude, parentBranch);
    }

    public double signalDistributionFunction(double position) {
        double adjustedPosition = position - this.getDistanceFromSignalMeanToSignalOrigin();
        return -this.getWidth()/2 < adjustedPosition && adjustedPosition < this.getWidth()/2 ? 1 : 0;
    }

    public String getTypeAsString() {
        return "SquareSignal";
    }

}
