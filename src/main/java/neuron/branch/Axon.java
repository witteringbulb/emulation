package neuron.branch;

import defaults.DefaultValues;
import neuron.signal.SignalType;

import java.util.ArrayList;
import java.util.List;

public class Axon extends Branch {

    private final double AXON_TERMINAL_FIRING_AMP = DefaultValues.DEFAULT_SIGNAL_AMPLITUDE;
    private final double AXON_TERMINAL_FIRE_THRESH = DefaultValues.DEFAULT_AXON_TERMINAL_FIRE_THRESHOLD;

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

    public void fire(double amplitude) {
        this.addSignal(amplitude);
    }

    public void buildAxonTerminalsAndConnectThemToNearbyDendrites(int numberToBuild) {
        List<AxonTerminal> axonTerminals = new ArrayList<AxonTerminal>();
        //TODO: Implement
        this.axonTerminals = axonTerminals;
    }

    public void propagateAxonAndAxonTerminalSignalsForwardOneTimeIncrement() {
        this.propagateSignalsOneTimeIncrement();
        this.axonTerminals.forEach(axonTerminal -> axonTerminal.propagateSignalsOneTimeIncrement());

        if (this.getSignalMagnitudeAtEndOfBranch() > AXON_TERMINAL_FIRE_THRESH) {
            this.axonTerminals.forEach(axonTerminal -> axonTerminal.fireIfAllowed(AXON_TERMINAL_FIRING_AMP));
        }
    }

    public List<AxonTerminal> getAxonTerminals() {
        return this.axonTerminals;
    }

    public void setAxonTerminals(List<AxonTerminal> axonTerminals) { this.axonTerminals = axonTerminals; }

    public String getBranchType() {return "axon";}

}
