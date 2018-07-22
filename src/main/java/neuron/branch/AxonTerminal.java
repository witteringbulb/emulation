package neuron.branch;

import neuron.signal.Signal;

public class AxonTerminal<T extends Signal> extends Branch {

    public AxonTerminal(double orientationInRadians,
                    double length,
                    Class<T> signalType,
                    double signalWidth,
                    double signalDisplacement) {
        super(orientationInRadians, length, signalType, signalWidth, signalDisplacement);
    }

}
