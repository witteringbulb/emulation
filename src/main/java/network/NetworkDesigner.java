package network;

import defaults.DefaultValues;
import neuron.Neuron;
import neuron.branch.Axon;
import neuron.branch.Dendrite;
import neuron.builders.DendriteBuilder;
import neuron.signal.SignalType;
import neuron.soma.HeavisideSoma;
import neuron.soma.Soma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class NetworkDesigner {

    private int numberOfNeurons;
    private double[] averageLocationOfSoma;
    private double maxSomaDistanceFromAvg;
    private double averageAxonLength;
    private double widthOfAxonLengthsDistribution;
    private double maxLengthOfAxonTerminals;
    private double averageNumberOfAxonTerminals;
    private double widthOfAxonTerminalNumberDistribution;
    private double averageNumberOfDendrites;
    private double widthOfDendriteNumberDistribution;
    private double averageLengthOfDendrites;
    private double widthOfDendriteLengthsDistribution;
    private double ratioExcitatoryToInhibitoryNeurons;

    //These ones we just take as defaults for now
    private SignalType signalType = DefaultValues.DEFAULT_SIGNAL_TYPE;
    private double somaFiringThreshold = DefaultValues.DEFAULT_SOMA_FIRE_THRESH;
    private double axonTerminalSynapseWeight = DefaultValues.DEFAULT_SYNAPSE_WEIGHT;

    public NetworkDesigner(int numberOfNeurons, double[] averageLocationOfSoma, double maxSomaDistanceFromAvg, double averageAxonLength,
                           double wifthOfAxonLengthDistribution, double maxLengthOfAxonTerminals, double averageNumberOfAxonTerminals,
                           double widthOfAxonTerminalNumberDistribution, double averageNumberOfDendrites,
                           double widthOfDendriteNumberDistribution, double averageLengthOfDendrites,
                           double widthOfDendriteLengthsDistribution, double ratioExcitatoryToInhibitoryNeurons) {
        this.numberOfNeurons = numberOfNeurons;
        this.averageLocationOfSoma = averageLocationOfSoma;
        this.maxSomaDistanceFromAvg = maxSomaDistanceFromAvg;
        this.averageAxonLength = averageAxonLength;
        this.widthOfAxonLengthsDistribution = wifthOfAxonLengthDistribution;
        this.maxLengthOfAxonTerminals = maxLengthOfAxonTerminals;
        this.averageNumberOfAxonTerminals = averageNumberOfAxonTerminals;
        this.widthOfAxonTerminalNumberDistribution = widthOfAxonTerminalNumberDistribution;
        this.averageNumberOfDendrites = averageNumberOfDendrites;
        this.widthOfDendriteNumberDistribution = widthOfDendriteNumberDistribution;
        this.averageLengthOfDendrites = averageLengthOfDendrites;
        this.widthOfDendriteLengthsDistribution = widthOfDendriteLengthsDistribution;
        this.ratioExcitatoryToInhibitoryNeurons = ratioExcitatoryToInhibitoryNeurons;
    }

    public List<Neuron> designNewDisconnectedNetwork() {
        List<Neuron> network = new ArrayList<Neuron>();


        for (int i = 0; i < numberOfNeurons; i++) {
            double angle = 2 * Math.PI * Math.random();
            double radius = Math.random() * maxSomaDistanceFromAvg;
            double[] somaCoordinates = new double[]{averageLocationOfSoma[0] + radius * Math.cos(angle), averageLocationOfSoma[1] + radius * Math.sin(angle)};
            Soma soma = new HeavisideSoma(somaCoordinates, somaFiringThreshold);

            double axonAngle = 2 * Math.PI * Math.random();
            double axonLength = averageAxonLength + (Math.random() - 0.5)*widthOfAxonLengthsDistribution;
            double[] axonEndCoordinates = new double[]{somaCoordinates[0] + axonLength * Math.cos(axonAngle), somaCoordinates[1] + axonLength * Math.sin(axonAngle)};
            Axon axon = new Axon(somaCoordinates, axonEndCoordinates, signalType);

            network.add(new Neuron(soma, axon, Math.random() > ratioExcitatoryToInhibitoryNeurons));
        }

        for (Neuron neuron : network) {
            List<Dendrite> dendritesToAdd = makeRandomizedDendrites(neuron.getSoma());
            neuron.getSoma().setDendrites(dendritesToAdd);
        }

        return network;

    }

    public void createAndConnectAxonTerminalsForNetwork(List<Neuron> network) {
        List<Integer> indices = prepareNeuronIndices(network);
        List<Dendrite> unconnectedDendrites = network.stream().flatMap(n -> n.getSoma().getDendrites().stream()).collect(Collectors.toList());
        for (int i : indices) {
            Neuron neuron = network.get(i);
            double[] coordinatesOfEndOfAxon = neuron.getAxon().getCoordinatesOfBranchEnd();
            List<Dendrite> nearbyDendrites = unconnectedDendrites.stream()
                                                                 .filter(d -> checkWithinRange(d, coordinatesOfEndOfAxon))
                                                                 .collect(Collectors.toList());
            if (nearbyDendrites.size() > 0) {
                Dendrite dendrite = nearbyDendrites.get(new Random().nextInt(nearbyDendrites.size()));
                neuron.getAxon().addAxonTerminalConnectionToDendrite(dendrite, axonTerminalSynapseWeight);
                unconnectedDendrites.remove(dendrite);
            }
        }
    }

    private boolean checkWithinRange(Dendrite dendrite, double[] coordinatesOfEndOfAxon) {
        double xDistance = dendrite.getCoordinatesOfBranchBeginning()[0] - coordinatesOfEndOfAxon[0];
        double yDistance = dendrite.getCoordinatesOfBranchBeginning()[1] - coordinatesOfEndOfAxon[1];
        return (Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2)) < maxLengthOfAxonTerminals);
    }

    private List<Integer> prepareNeuronIndices(List<Neuron> network) {
        List<Integer> neuronIndices = new ArrayList<Integer>();
        long maxNumberOfAxonTerminals = Math.round(averageNumberOfAxonTerminals + 0.5 * widthOfAxonTerminalNumberDistribution);
        long minNumberOfAxonTerminals = Math.round(averageNumberOfAxonTerminals - 0.5 * widthOfAxonTerminalNumberDistribution);
        int neuronIndex = 0;
        for (Neuron neuron : network) {
            //Roll a 0, should get minimum number of axon terminals. Roll 0.5, should get avg. Roll 1, max
            long numberOfAxonTerminals = Math.round(minNumberOfAxonTerminals + Math.random() * (maxNumberOfAxonTerminals - minNumberOfAxonTerminals));
            for (int i = 0; i < numberOfAxonTerminals; i++) {
                neuronIndices.add(neuronIndex);
            }
            neuronIndex++;
        }
        Collections.shuffle(neuronIndices);
        return neuronIndices;
    }

    private List<Dendrite> makeRandomizedDendrites(Soma soma) {
        List<Dendrite> dendritesToAdd = new ArrayList<>();
        long numberOfDendritesToAdd = Math.round(averageNumberOfDendrites + (Math.random() - 0.5)*widthOfDendriteNumberDistribution);
        double[] somaCoordinates = soma.getSomaLocation();
        for (int i = 0; i < numberOfDendritesToAdd; i++) {
            double dendriteAngle = 2 * Math.PI * Math.random();
            double dendriteLength = averageLengthOfDendrites + (Math.random() - 0.5)*widthOfDendriteLengthsDistribution;
            double[] dendriteStartCoordinates = new double[]{somaCoordinates[0] + dendriteLength * Math.cos(dendriteAngle),
                    somaCoordinates[1] + dendriteLength * Math.sin(dendriteAngle)};
            Dendrite dendrite = new DendriteBuilder().setCoordinatesOfBranchBeginning(dendriteStartCoordinates)
                                                     .setCoordinatesOfBranchEnd(somaCoordinates)
                                                     .setSignalType(signalType)
                                                     .createDendrite();
            dendritesToAdd.add(dendrite);
        }
        return dendritesToAdd;
    }

}
