package neuron.branch;

import defaults.DefaultValues;
import neuron.signal.SignalType;

import java.util.List;

public class Axon extends Branch {

    private List<AxonTerminal> axonTerminals;

    public Axon(double orientationInRadians,
                double length,
                SignalType signalType,
                List<AxonTerminal> axonTerminals) {
        super(orientationInRadians, length, signalType);
        this.axonTerminals = axonTerminals;
    }

    public void propagateAxonAndAxonTerminalSignalsForwardOneTimeIncrement() {
        this.propagateSignalsOneTimeIncrement();
        this.axonTerminals.forEach(axonTerminal -> axonTerminal.propagateSignalsOneTimeIncrement());

        if (this.getSignalMagnitudeAtEndOfBranch() > DefaultValues.DEFAULT_AXON_TERMINAL_FIRE_THRESHOLD) {
            this.axonTerminals.forEach(axonTerminal -> axonTerminal.fire());
        }
    }

}
