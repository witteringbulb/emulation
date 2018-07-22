package neuron.factories;

import neuron.signal.Signal;
import neuron.signal.SignalType;
import neuron.signal.SquareSignal;

public class SignalFactory {

    public static Signal getSignal(SignalType signalType, double amplitude) {
        Class signalClass = signalType.getSignalClass();
        double width = signalType.getWidth();
        double displacement = signalType.getDisplacement();

        if (signalClass == SquareSignal.class) {
            return new SquareSignal(width, displacement, amplitude);
        } else {
            throw new IllegalArgumentException("Unrecognised signal type");
        }
    }

}
