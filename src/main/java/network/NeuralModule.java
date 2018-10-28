package network;

import neuron.Neuron;

import java.util.HashMap;
import java.util.List;

public class NeuralModule {

    private HashMap<NeuralModule, Integer> connectedModules;

    private double[] centre;
    private double radius;
    private NetworkModuleDesigner networkModuleDesigner;

    public NeuralModule(int numberOfNeurons, double[] centre, double radius) {
        this.centre = centre;
        this.radius = radius;
        this.networkModuleDesigner = new NetworkModuleDesigner(numberOfNeurons, centre, radius);
    }

    public void addConnectedModule(NeuralModule module, int numberOfInterModuleNeurons) {
        if (numberOfInterModuleNeurons <= 0) {
            throw new IllegalArgumentException("Can't connect a negative number of neurons");
        }
        if (this.connectedModules == null) {
            this.connectedModules = new HashMap<NeuralModule, Integer>();
        }
        this.connectedModules.put(module, numberOfInterModuleNeurons);
    }

    public List<Neuron> designDisconnectedModuleInternalNetwork(){
        return this.networkModuleDesigner.designNewDisconnectedNetwork();
    }

    public HashMap<NeuralModule, Integer> getMapOfConnectedModulesToNumberOfConnections() {
        return this.connectedModules;
    }

    public double[] getCentre() {
        return centre;
    }

    public double getRadius() {
        return radius;
    }

}
