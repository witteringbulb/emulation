package neuron.factories;

import neuron.branch.Branch;
import neuron.signal.Signal;
import neuron.signal.SignalType;
import neuron.signal.SquareSignal;

public class SignalFactory {

    private final SignalType signalType;

    public SignalFactory(SignalType signalType) {
        this.signalType = signalType;
    }

    public Signal getSignal(double amplitude, Branch parentBranch) {
        Class signalClass = signalType.getSignalClass();
        double width = signalType.getWidth();

        if (signalClass == SquareSignal.class) {
            return new SquareSignal(width, amplitude, parentBranch);
        } else {
            throw new IllegalArgumentException("Unrecognised signal type");
        }
    }

}
