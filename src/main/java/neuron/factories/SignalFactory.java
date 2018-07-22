package neuron.factories;

import neuron.signal.Signal;
import neuron.signal.SignalType;
import neuron.signal.SquareSignal;

public class SignalFactory {

    public static Signal getSignal(SignalType signalType) {
        Class signalClass = signalType.getClass();
        double width = signalType.getWidth();
        double displacement = signalType.getDisplacement();

        if (signalClass == SquareSignal.class) {
            return new SquareSignal(width, displacement);
        } else {
            throw new IllegalArgumentException("Unrecognised signal type");
        }
    }

}
