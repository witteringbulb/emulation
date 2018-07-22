package neuron.factories;

import neuron.signal.Signal;
import neuron.signal.SquareSignal;

public class SignalFactory {

    public static Signal getSignal(Class signalType, double width, double displacement) {
        if (signalType == SquareSignal.class) {
            return new SquareSignal(width, displacement);
        } else {
            throw new IllegalArgumentException("Unrecognised signal type");
        }
    }

}
