package neuron.branch;

import defaults.DefaultValues;
import neuron.signal.SignalType;

import java.util.ArrayList;
import java.util.List;

public class Axon extends Branch {

    private final double AXON_TERMINAL_FIRING_AMP = DefaultValues.DEFAULT_SIGNAL_AMPLITUDE;
    private final double AXON_TERMINAL_FIRE_THRESH = DefaultValues.DEFAULT_AXON_TERMINAL_FIRE_THRESHOLD;

    private List<AxonTerminal> axonTerminals;

    private boolean inhibitory = false;

    private int timeStepsSinceFiring;

    public Axon(double[] coordinatesOfBranchBeginning,
                double[] coordinatesOfBranchEnd,
                SignalType signalType,
                List<AxonTerminal> axonTerminals) {
        super(coordinatesOfBranchBeginning, coordinatesOfBranchEnd, signalType);
        this.axonTerminals = axonTerminals;
        this.timeStepsSinceFiring = 10000;
    }

    public Axon(double[] coordinatesOfBranchBeginning,
                double[] coordinatesOfBranchEnd,
                SignalType signalType) {
        super(coordinatesOfBranchBeginning, coordinatesOfBranchEnd, signalType);
    }

    public void fire(double amplitude) {
        this.addSignal(amplitude);
        this.timeStepsSinceFiring = 0;
    }

    public void addAxonTerminalConnectionToDendrite(Dendrite connectedDendrite, double initialSynapseWeight) {
        if (this.axonTerminals == null) {
            this.axonTerminals = new ArrayList<AxonTerminal>();
        }
        this.axonTerminals.add(new AxonTerminal(this.getCoordinatesOfBranchEnd(), connectedDendrite, this.getSignalType(),
                isInhibitory()*initialSynapseWeight));
    }

    @Override
    public void propagateSignalsOneTimeIncrement() {
        this.timeStepsSinceFiring++;
        super.propagateSignalsOneTimeIncrement();
    }

    public void propagateAxonAndAxonTerminalSignalsForwardOneTimeIncrement() {
        this.propagateSignalsOneTimeIncrement();
        if (this.axonTerminals != null) {
            this.axonTerminals.forEach(axonTerminal -> axonTerminal.propagateSignalsOneTimeIncrement());

            if (this.getSignalMagnitudeAtEndOfBranch() > AXON_TERMINAL_FIRE_THRESH) {
                this.axonTerminals.forEach(axonTerminal -> axonTerminal.fire(AXON_TERMINAL_FIRING_AMP));
            }
        }
    }

    public List<AxonTerminal> getAxonTerminals() {
        return this.axonTerminals;
    }

    public void setAxonTerminals(List<AxonTerminal> axonTerminals) { this.axonTerminals = axonTerminals; }

    public String getBranchType() {return "axon";}

    public int getTimeStepsSinceFiring() { return timeStepsSinceFiring;}

    private int isInhibitory() {
        if (inhibitory) {return -1;}
        return 1;
    }

    public void setInhibitory(boolean inhibitory) {
        this.inhibitory = inhibitory;
    }

}
