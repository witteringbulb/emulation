package neuron.signal;

import defaults.DefaultValues;

public enum SignalType {

    SQUARE_SIGNAL_DEFAULT (SquareSignal.class, DefaultValues.DEFAULT_SIGNAL_WIDTH, DefaultValues.DEFAULT_SIGNAL_DISPLACEMENT);

    private final Class signalClass;
    private final double width;
    private final double displacement;
    SignalType(Class signalClass, double width, double displacement) {
        this.signalClass = signalClass;
        this.width = width;
        this.displacement = displacement;
    }

    public Class getSignalClass() { return signalClass; }
    public double getWidth() { return width; }
    public double getDisplacement() { return displacement; }

}