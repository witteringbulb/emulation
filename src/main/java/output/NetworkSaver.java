package output;

import neuron.Neuron;

import java.util.List;

public class NetworkSaver {

    private List<Neuron> neurons;
    private String pathToSaveDirectory;

    public NetworkSaver(List<Neuron> neurons, String pathToSaveDirectory) {
        this.neurons = neurons;
        this.pathToSaveDirectory = pathToSaveDirectory;
    }

    public void saveNeuronConfiguration() {
        //TODO: Save info about structure of neurons to a csv file (this format allows us to use Processing's Table library)
        //Basically we just want to save a set of line segments which can then be drawn in Processing
        //Each row should look like: xBeginning, yBeginning, xEnd, yEnd

    }

}
