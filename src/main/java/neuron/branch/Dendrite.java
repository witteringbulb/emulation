package neuron.branch;

import neuron.signal.SignalType;

public class Dendrite extends Branch {

    public Dendrite(double[] coordinatesOfBranchBeginning,
                    double[] coordinatesOfBranchEnd,
                    SignalType signalType) {
        super(coordinatesOfBranchBeginning, coordinatesOfBranchEnd, signalType);
    }

    public void fire(double amplitude) {
        this.addSignal(amplitude);
    }

    public String getBranchType() {return "dendrite";}

}
