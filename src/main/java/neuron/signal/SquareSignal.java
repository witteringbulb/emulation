package main.java.neuron.signal;

public class SquareSignal extends Signal {

    private double width;
    private double displacement;

    public SquareSignal(double width, double displacement) {
        this.width = width;
        this.displacement = displacement;
    }

    public double signalDistributionFunction(double position) {
        double adjustedPosition = position - this.getDistanceFromSignalMeanToSignalOrigin() - displacement;
        return 0 <  adjustedPosition && adjustedPosition < width ? 1 : 0;
    }

}
