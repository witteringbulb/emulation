package neuron.signal;

import neuron.branch.Branch;

public class SquareSignal extends Signal {

    public SquareSignal(double width, double amplitude, Branch parentBranch) {
        super(width, amplitude, parentBranch);
    }

    public double signalDistributionFunction(double position) {
        double adjustedPosition = position - this.getDistanceFromSignalMeanToSignalOrigin();
        return 0 <  adjustedPosition && adjustedPosition < this.getWidth() ? 1 : 0;
    }

}
