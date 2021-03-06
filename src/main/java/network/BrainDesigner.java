package network;

import neuron.Neuron;
import neuron.branch.Axon;
import neuron.branch.Dendrite;
import neuron.soma.HeavisideSoma;
import neuron.soma.Soma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BrainDesigner {

    private HashMap<String, NeuralModule> neuralModules;
    private List<Neuron> internalNeuronPopulation;
    private List<Neuron> inputNeurons;

    public BrainDesigner() {
        this.neuralModules = new HashMap<String, NeuralModule>();
    }

    public void addModule(String moduleName, NeuralModule module) {
        this.neuralModules.put(moduleName, module);
    }

    public void establishUnidirectionalModuleConnections(String nameOfModuleToConnect,
                                                         String nameOfModuleToConnectWith,
                                                         int numberOfInterModuleNeurons) {
        if (this.neuralModules.get(nameOfModuleToConnect) == null) {
            throw new IllegalArgumentException("No module with that id");
        }
        if (this.neuralModules.get(nameOfModuleToConnectWith) == null) {
            throw new IllegalArgumentException("No module with that id");
        }
        this.neuralModules.get(nameOfModuleToConnect).addConnectedModule(
                this.neuralModules.get(nameOfModuleToConnectWith), numberOfInterModuleNeurons);

    }

    public Brain createBrainNetworkFromModules() {
       this.createDisconnectedBrainNetwork();
       this.createAndConnectAxonTerminalsForBrainNetwork();
       return new Brain(this.internalNeuronPopulation, this.inputNeurons);
    }

    public void createUnconnectedInputNeurons(NeuralModule moduleToConnect, int numberOfInputNeurons) {

    }

    public void createDisconnectedBrainNetwork() {
        this.internalNeuronPopulation = new ArrayList<Neuron>();
        //For each module, we create the internal network and create neurons with axons reaching out to the locations of other modules
        for (NeuralModule neuralModule : this.neuralModules.values()) {
            List<Neuron> moduleInternalNetwork = neuralModule.designDisconnectedModuleInternalNetwork();
            this.internalNeuronPopulation.addAll(moduleInternalNetwork);

            List<Neuron> connectionsToExternalModules = createInterModuleConnections(neuralModule);
            this.internalNeuronPopulation.addAll(connectionsToExternalModules);

            this.internalNeuronPopulation.addAll(moduleInternalNetwork);
            this.internalNeuronPopulation.addAll(connectionsToExternalModules);
        }
    }

    public List<Neuron> createInterModuleConnections(NeuralModule neuralModule) {
        List<Neuron> interModuleConnections = new ArrayList<Neuron>();
        HashMap<NeuralModule, Integer> connectedModules = neuralModule.getMapOfConnectedModulesToNumberOfConnections();
        if (connectedModules == null) {
            return new ArrayList<Neuron>();
        }
        for (NeuralModule moduleToConnectWith : connectedModules.keySet()) {
            interModuleConnections.addAll(createNeuronsBetweenModules(neuralModule, moduleToConnectWith, connectedModules.get(neuralModule)));
        }
        return interModuleConnections;
    }

    private List<Neuron> createNeuronsBetweenModules(NeuralModule neuralModule, NeuralModule moduleToConnectWith, int numberOfNeurons) {
        List<Neuron> neuronConnectionsBetweenModules = new ArrayList<Neuron>();
        for (int i = 0; i < numberOfNeurons; i++) {
            double[] somaCoordinates = chooseRandomPointInCircle(neuralModule.getCentre(), neuralModule.getRadius());
            Soma soma = new HeavisideSoma(somaCoordinates, NetworkDesignerStatic.getSomaFiringThreshold());

            double[] coordinatesOfAxonEnd = chooseRandomPointInCircle(moduleToConnectWith.getCentre(), moduleToConnectWith.getRadius());
            Axon axon = new Axon(somaCoordinates, coordinatesOfAxonEnd, NetworkDesignerStatic.getSignalType());

            neuronConnectionsBetweenModules.add(
                    new Neuron(soma, axon, Math.random() > NetworkDesignerStatic.getRatioExcitatoryToInhibitoryNeurons()));
        }
        for (Neuron neuron : neuronConnectionsBetweenModules) {
            List<Dendrite> dendritesToAdd = NetworkDesignerStatic.makeRandomizedDendrites(neuron.getSoma());
            neuron.getSoma().setDendrites(dendritesToAdd);
        }
        return neuronConnectionsBetweenModules;
    }

    private double[] chooseRandomPointInCircle(double[] centre, double radius) {
        double distanceFromCentre = radius*Math.random();
        double angle = Math.random()*2*Math.PI;
        return new double[]{centre[0]+distanceFromCentre*Math.cos(angle), centre[1]+distanceFromCentre*Math.sin(angle)};
    }

    private void createAndConnectAxonTerminalsForBrainNetwork() {
        NetworkDesignerStatic.createAndConnectAxonTerminalsForNetwork(this.internalNeuronPopulation);
    }


}
