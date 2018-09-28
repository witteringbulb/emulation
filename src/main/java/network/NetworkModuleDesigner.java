package network;

import defaults.DefaultValues;
import neuron.Neuron;
import neuron.branch.Axon;
import neuron.branch.Dendrite;
import neuron.signal.SignalType;
import neuron.soma.HeavisideSoma;
import neuron.soma.Soma;

import java.util.ArrayList;
import java.util.List;

public class NetworkModuleDesigner {

    private int numberOfNeurons;
    private double[] averageLocationOfSoma;
    private double maxSomaDistanceFromAvg;
    private double averageAxonLength = NetworkDesignerStatic.getAverageAxonLengthWithinModule();
    private double widthOfAxonLengthsDistribution = NetworkDesignerStatic.getWidthOfAxonLengthsDistributionWithinModule();
    private double maxLengthOfAxonTerminals = NetworkDesignerStatic.getMaxLengthOfAxonTerminals();
    private double averageNumberOfAxonTerminals = NetworkDesignerStatic.getAverageNumberOfAxonTerminals();
    private double widthOfAxonTerminalNumberDistribution = NetworkDesignerStatic.getWidthOfAxonTerminalNumberDistribution();
    private double averageNumberOfDendrites = NetworkDesignerStatic.getAverageNumberOfDendrites();
    private double widthOfDendriteNumberDistribution = NetworkDesignerStatic.getWidthOfDendriteNumberDistribution();
    private double averageLengthOfDendrites = NetworkDesignerStatic.getAverageLengthOfDendrites();
    private double widthOfDendriteLengthsDistribution = NetworkDesignerStatic.getWidthOfDendriteLengthsDistribution();
    private double ratioExcitatoryToInhibitoryNeurons = NetworkDesignerStatic.getRatioExcitatoryToInhibitoryNeurons();

    //These ones we just take as defaults for now
    private SignalType signalType = DefaultValues.DEFAULT_SIGNAL_TYPE;
    private double somaFiringThreshold = DefaultValues.DEFAULT_SOMA_FIRE_THRESH;
    private double axonTerminalSynapseWeight = DefaultValues.DEFAULT_SYNAPSE_WEIGHT;

    public NetworkModuleDesigner(int numberOfNeurons, double[] averageLocationOfSoma, double maxSomaDistanceFromAvg) {
        this.numberOfNeurons = numberOfNeurons;
        this.averageLocationOfSoma = averageLocationOfSoma;
        this.maxSomaDistanceFromAvg = maxSomaDistanceFromAvg;
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
            List<Dendrite> dendritesToAdd = NetworkDesignerStatic.makeRandomizedDendrites(neuron.getSoma());
            neuron.getSoma().setDendrites(dendritesToAdd);
        }

        return network;

    }

}
