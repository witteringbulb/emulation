package neuron.branch;

import lombok.Builder;
import neuron.signal.Signal;

@Builder
public class Dendrite<T extends Signal> extends Branch {

    public Dendrite(double orientationInRadians,
                     double length,
                     Class<T> signalType,
                     double signalWidth,
                     double signalDisplacement) {
        super(orientationInRadians, length, signalType, signalWidth, signalDisplacement);
    }

}
