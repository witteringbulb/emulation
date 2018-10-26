package network;

import defaults.DefaultValues;
import neuron.InternalNeuron;
import neuron.Neuron;
import neuron.branch.Dendrite;
import neuron.builders.DendriteBuilder;
import neuron.signal.SignalType;
import neuron.soma.Soma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class NetworkDesignerStatic {

    private static double averageAxonLengthWithinModule = 3.0;
    private static double widthOfAxonLengthsDistributionWithinModule = 1.0;
    private static double maxLengthOfAxonTerminals = 1.0;
    private static double averageNumberOfAxonTerminals = 20.0;
    private static double widthOfAxonTerminalNumberDistribution = 10.0;
    private static double averageNumberOfDendrites = 40.0;
    private static double widthOfDendriteNumberDistribution = 15.0;
    private static double averageLengthOfDendrites = 1.5;
    private static double widthOfDendriteLengthsDistribution = 0.5;
    private static double ratioExcitatoryToInhibitoryNeurons = 0.7;
    private static SignalType signalType = DefaultValues.DEFAULT_SIGNAL_TYPE;
    private static double somaFiringThreshold = DefaultValues.DEFAULT_SOMA_FIRE_THRESH;
    private static double axonTerminalSynapseWeight = DefaultValues.DEFAULT_SYNAPSE_WEIGHT;

    public static List<Dendrite> makeRandomizedDendrites(Soma soma) {
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

    public static void createAndConnectAxonTerminalsForNetwork(List<Neuron> network) {
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

    private static boolean checkWithinRange(Dendrite dendrite, double[] coordinatesOfEndOfAxon) {
        double xDistance = dendrite.getCoordinatesOfBranchBeginning()[0] - coordinatesOfEndOfAxon[0];
        double yDistance = dendrite.getCoordinatesOfBranchBeginning()[1] - coordinatesOfEndOfAxon[1];
        return (Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2)) < maxLengthOfAxonTerminals);
    }

    private static List<Integer> prepareNeuronIndices(List<InternalNeuron> network) {
        List<Integer> neuronIndices = new ArrayList<Integer>();
        long maxNumberOfAxonTerminals = Math.round(averageNumberOfAxonTerminals + 0.5 * widthOfAxonTerminalNumberDistribution);
        long minNumberOfAxonTerminals = Math.round(averageNumberOfAxonTerminals - 0.5 * widthOfAxonTerminalNumberDistribution);
        int neuronIndex = 0;
        for (InternalNeuron neuron : network) {
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

    public static double getAverageAxonLengthWithinModule() {
        return averageAxonLengthWithinModule;
    }

    public static void setAverageAxonLengthWithinModule(double averageAxonLengthWithinModule) {
        NetworkDesignerStatic.averageAxonLengthWithinModule = averageAxonLengthWithinModule;
    }

    public static double getWidthOfAxonLengthsDistributionWithinModule() {
        return widthOfAxonLengthsDistributionWithinModule;
    }

    public static void setWidthOfAxonLengthsDistributionWithinModule(double widthOfAxonLengthsDistributionWithinModule) {
        NetworkDesignerStatic.widthOfAxonLengthsDistributionWithinModule = widthOfAxonLengthsDistributionWithinModule;
    }

    public static double getMaxLengthOfAxonTerminals() {
        return maxLengthOfAxonTerminals;
    }

    public static void setMaxLengthOfAxonTerminals(double maxLengthOfAxonTerminals) {
        NetworkDesignerStatic.maxLengthOfAxonTerminals = maxLengthOfAxonTerminals;
    }

    public static double getAverageNumberOfAxonTerminals() {
        return averageNumberOfAxonTerminals;
    }

    public static void setAverageNumberOfAxonTerminals(double averageNumberOfAxonTerminals) {
        NetworkDesignerStatic.averageNumberOfAxonTerminals = averageNumberOfAxonTerminals;
    }

    public static double getWidthOfAxonTerminalNumberDistribution() {
        return widthOfAxonTerminalNumberDistribution;
    }

    public static void setWidthOfAxonTerminalNumberDistribution(double widthOfAxonTerminalNumberDistribution) {
        NetworkDesignerStatic.widthOfAxonTerminalNumberDistribution = widthOfAxonTerminalNumberDistribution;
    }

    public static double getAverageNumberOfDendrites() {
        return averageNumberOfDendrites;
    }

    public static void setAverageNumberOfDendrites(double averageNumberOfDendrites) {
        NetworkDesignerStatic.averageNumberOfDendrites = averageNumberOfDendrites;
    }

    public static double getWidthOfDendriteNumberDistribution() {
        return widthOfDendriteNumberDistribution;
    }

    public static void setWidthOfDendriteNumberDistribution(double widthOfDendriteNumberDistribution) {
        NetworkDesignerStatic.widthOfDendriteNumberDistribution = widthOfDendriteNumberDistribution;
    }

    public static double getAverageLengthOfDendrites() {
        return averageLengthOfDendrites;
    }

    public static void setAverageLengthOfDendrites(double averageLengthOfDendrites) {
        NetworkDesignerStatic.averageLengthOfDendrites = averageLengthOfDendrites;
    }

    public static double getWidthOfDendriteLengthsDistribution() {
        return widthOfDendriteLengthsDistribution;
    }

    public static void setWidthOfDendriteLengthsDistribution(double widthOfDendriteLengthsDistribution) {
        NetworkDesignerStatic.widthOfDendriteLengthsDistribution = widthOfDendriteLengthsDistribution;
    }

    public static double getRatioExcitatoryToInhibitoryNeurons() {
        return ratioExcitatoryToInhibitoryNeurons;
    }

    public static void setRatioExcitatoryToInhibitoryNeurons(double ratioExcitatoryToInhibitoryNeurons) {
        NetworkDesignerStatic.ratioExcitatoryToInhibitoryNeurons = ratioExcitatoryToInhibitoryNeurons;
    }

    public static SignalType getSignalType() {
        return signalType;
    }

    public static void setSignalType(SignalType signalType) {
        NetworkDesignerStatic.signalType = signalType;
    }

    public static double getSomaFiringThreshold() {
        return somaFiringThreshold;
    }

    public static void setSomaFiringThreshold(double somaFiringThreshold) {
        NetworkDesignerStatic.somaFiringThreshold = somaFiringThreshold;
    }

    public static double getAxonTerminalSynapseWeight() {
        return axonTerminalSynapseWeight;
    }

    public static void setAxonTerminalSynapseWeight(double axonTerminalSynapseWeight) {
        NetworkDesignerStatic.axonTerminalSynapseWeight = axonTerminalSynapseWeight;
    }
}
