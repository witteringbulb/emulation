package neuron.branch;

import defaults.DefaultValues;
import neuron.signal.SignalType;

public class AxonTerminal extends Branch {

    public AxonTerminal(double[] coordinatesOfBranchBeginning,
                        double[] coordinatesOfBranchEnd,
                        SignalType signalType) {
        super(coordinatesOfBranchBeginning, coordinatesOfBranchEnd, signalType);
    }

    public void fire(double amplitude) {
        this.addSignal(amplitude);
    }

    public String getBranchType() {return "axonTerminal";}

}
