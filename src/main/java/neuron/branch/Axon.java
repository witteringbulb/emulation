package neuron.branch;

import defaults.DefaultValues;
import neuron.signal.SignalType;

import java.util.ArrayList;
import java.util.List;

public class Axon extends Branch {

    private List<AxonTerminal> axonTerminals;

    public Axon(double[] coordinatesOfBranchBeginning,
                double[] coordinatesOfBranchEnd,
                SignalType signalType,
                List<AxonTerminal> axonTerminals) {
        super(coordinatesOfBranchBeginning, coordinatesOfBranchEnd, signalType);
        this.axonTerminals = axonTerminals;
    }

    public Axon(double[] coordinatesOfBranchBeginning,
                double[] coordinatesOfBranchEnd,
                SignalType signalType) {
        super(coordinatesOfBranchBeginning, coordinatesOfBranchEnd, signalType);
    }

    public void fire() {
        this.addSignal(DefaultValues.DEFAULT_SIGNAL_AMPLITUDE);
    }

    public void buildAxonTerminalsAndConnectThemToNearbyDendrites(int numberToBuild) {
        List<AxonTerminal> axonTerminals = new ArrayList<AxonTerminal>();
        //TODO: Implement
        this.axonTerminals = axonTerminals;
    }

    public void propagateAxonAndAxonTerminalSignalsForwardOneTimeIncrement() {
        this.propagateSignalsOneTimeIncrement();
        this.axonTerminals.forEach(axonTerminal -> axonTerminal.propagateSignalsOneTimeIncrement());

        if (this.getSignalMagnitudeAtEndOfBranch() > DefaultValues.DEFAULT_AXON_TERMINAL_FIRE_THRESHOLD) {
            this.axonTerminals.forEach(axonTerminal -> axonTerminal.fire());
        }
    }

    public List<AxonTerminal> getAxonTerminals() {
        return this.axonTerminals;
    }

    public void setAxonTerminals(List<AxonTerminal> axonTerminals) { this.axonTerminals = axonTerminals; }

}
