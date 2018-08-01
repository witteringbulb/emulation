package simulation;

import neuron.Neuron;
import neuron.branch.Dendrite;
import output.SignalSaver;

import java.util.List;

public class PopulationSimulator {

    private final List<Neuron> neuronPopulation;
    private final SignalSaver signalSaver;

    public PopulationSimulator(List<Neuron> neuronPopulation, SignalSaver signalSaver) {
        this.neuronPopulation = neuronPopulation;
        this.signalSaver = signalSaver;
    }

    //TODO: Methods to simulate the population for one time increment, to simulate it for multiple, to set the initial conditions (which branches are initially firing)

    public void simulateWithAllDendritesFiringRandomly(int numberOfTimeIncrements, double firingProbabilityPerDendritePerTimeIncrement, double dendriteFiringAmplitude) {
        for (int i = 0; i < numberOfTimeIncrements; i++) {
            this.signalSaver.saveSignalsAtThisTimeStep();
            this.simulateForOneTimeIncrementWithAllDendritesFiringRandomly(firingProbabilityPerDendritePerTimeIncrement, dendriteFiringAmplitude);
        }
    }

    private void simulateForOneTimeIncrementWithAllDendritesFiringRandomly(double firingProbabilityPerDendritePerTimeIncrement, double dendriteFiringAmplitude) {
        for (Neuron neuron : neuronPopulation) {
            neuron.propagateSignalsOneTimeIncrement();
            for (Dendrite dendrite : neuron.getSoma().getDendrites()) {
                if (Math.random() < firingProbabilityPerDendritePerTimeIncrement) {
                    dendrite.fire(dendriteFiringAmplitude);
                }
            }
        }
    }

}
