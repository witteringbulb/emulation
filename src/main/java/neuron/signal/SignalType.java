package neuron.signal;

import defaults.DefaultValues;

public enum SignalType {

    SQUARE_SIGNAL_DEFAULT (SquareSignal.class, DefaultValues.DEFAULT_SIGNAL_WIDTH);

    private final Class signalClass;
    private final double width;
    SignalType(Class signalClass, double width) {
        this.signalClass = signalClass;
        this.width = width;
    }

    public Class getSignalClass() { return signalClass; }
    public double getWidth() { return width; }

}