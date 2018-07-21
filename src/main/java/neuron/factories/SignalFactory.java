package main.java.neuron.factories;

import main.java.neuron.signal.Signal;
import main.java.neuron.signal.SquareSignal;

public class SignalFactory {

    public static Signal getSignal(Class signalType, double width, double displacement) {
        if (signalType == SquareSignal.class) {
            return new SquareSignal(width, displacement);
        } else {
            throw new IllegalArgumentException("Unrecognised signal type");
        }
    }

}
