package main.java.neuron;

import javafx.util.Pair;
import main.java.neuron.Branch.Axon;
import main.java.neuron.Branch.Dendrite;

import java.util.List;

public class Neuron {

    private Pair<Integer, Integer> somaLocation;

    private Axon axon;
    private List<Dendrite> dendrites;

}
