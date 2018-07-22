package neuron.signal;

public class SquareSignal extends Signal {

    public SquareSignal(double width, double displacement, double amplitude) {
        super(width, displacement, amplitude);
    }

    public double signalDistributionFunction(double position) {
        double adjustedPosition = position - this.getDistanceFromSignalMeanToSignalOrigin() - this.getDisplacement();
        return 0 <  adjustedPosition && adjustedPosition < this.getWidth() ? 1 : 0;
    }

}
