package main.java.neuron;

import javafx.util.Pair;
import main.java.neuron.branch.Axon;
import main.java.neuron.branch.Dendrite;

import java.util.List;

public class Neuron {

    private Pair<Double, Double> somaLocation;

    private Axon axon;
    private List<Dendrite> dendrites;

    public Neuron(Pair<Double, Double> somaLocation) {
        this.somaLocation = somaLocation;
    }

    public Axon getAxon() {
        return axon;
    }

    public void setAxon(Axon axon) {
        this.axon = axon;
    }

    public List<Dendrite> getDendrites() {
        return dendrites;
    }

    public void setDendrites(List<Dendrite> dendrites) {
        this.dendrites = dendrites;
    }

}
