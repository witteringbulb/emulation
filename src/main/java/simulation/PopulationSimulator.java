package simulation;

import neuron.Neuron;
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

}
