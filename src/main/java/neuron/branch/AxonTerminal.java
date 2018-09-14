package neuron.branch;

import defaults.DefaultValues;
import neuron.signal.Signal;
import neuron.signal.SignalType;

import java.util.Iterator;

public class AxonTerminal extends Branch {

    private final Dendrite connectedDendrite;

    private double synapticWeight;

    private int timeStepsToSynapseFire = -1;

    private final double SYNAPSE_FIRE_THRESH = DefaultValues.DEFAULT_SYNAPSE_FIRE_THRESH;
    private final int SYNAPSE_FIRE_TIME_DELAY = DefaultValues.DEFAULT_SYNAPSE_FIRE_TIME_DELAY;
    private final double POSTSYNAPTIC_AMPLITUDE_BASE = DefaultValues.DEFAULT_POSTSYNAPTIC_SIGNAL_AMPLITUDE_BASE;

    public AxonTerminal(double[] coordinatesOfBranchBeginning,
                        Dendrite connectedDendrite,
                        SignalType signalType,
                        double synapticWeight) {
        super(coordinatesOfBranchBeginning, connectedDendrite.getCoordinatesOfBranchBeginning(), signalType);
        this.connectedDendrite = connectedDendrite;
        this.synapticWeight = synapticWeight;
    }

    public AxonTerminal(double[] coordinatesOfBranchBeginning,
                        double[] coordinatesOfBranchEnd,
                        SignalType signalType) {
        super(coordinatesOfBranchBeginning, coordinatesOfBranchEnd, signalType);
        this.connectedDendrite = null;
    }

    @Override
    public void propagateSignalsOneTimeIncrement() {

        this.fireSynapseIfNeeded();

        Iterator<Signal> signalsIterator = this.getSignals().iterator();
        while (signalsIterator.hasNext()) {
            Signal signal = signalsIterator.next();
            signal.propagateOneTimeIncrement();
            if (signal.hasFullyPassedPosition(this.getLength())) {
                signalsIterator.remove();
            }
        }
    }

    private void fireSynapseIfNeeded() {
        this.timeStepsToSynapseFire -= 1;
        if (this.getSignalMagnitudeAtEndOfBranch() > SYNAPSE_FIRE_THRESH && this.timeStepsToSynapseFire < 0) {
            timeStepsToSynapseFire = SYNAPSE_FIRE_TIME_DELAY;
        }
        if (timeStepsToSynapseFire == 0) {
            this.fireConnectedDendrite();
        }
    }

    public void fire(double amplitude) {
        this.addSignal(amplitude);
    }

    public void fireConnectedDendrite() {
        if (this.connectedDendrite != null) {
            this.connectedDendrite.fire(POSTSYNAPTIC_AMPLITUDE_BASE * this.synapticWeight);
        }
    }

    public String getBranchType() {return "axonTerminal";}

}
