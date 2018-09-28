package network;

import neuron.Neuron;

import java.util.List;

public class Brain {

    private List<Neuron> neuronsInBrain;

    public Brain(List<Neuron> neuronsInBrain) {
        this.neuronsInBrain = neuronsInBrain;
    }

    public List<Neuron> getAllNeurons() {
        return this.neuronsInBrain;
    }

}
