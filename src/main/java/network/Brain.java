package network;

import neuron.Neuron;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Brain {

    private List<Neuron> neuronsInBrain;
    private List<Neuron> inputNeurons;

    public Brain(List<Neuron> neuronsInBrain, List<Neuron> inputNeurons) {
        this.neuronsInBrain = neuronsInBrain;
        this.inputNeurons = inputNeurons;
    }

    public List<Neuron> getAllNeurons() {
        return Stream.concat(this.neuronsInBrain.stream(), this.inputNeurons.stream()).collect(Collectors.toList());
    }
    public List<Neuron> getInputNeurons() {return this.inputNeurons;}

}
