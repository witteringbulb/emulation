package network.builders;

import network.NetworkDesigner;

public class NetworkDesignerBuilder {
    private int numberOfNeurons = 30;
    private double[] averageLocationOfSoma = new double[]{0.0, 0.0};
    private double maxSomaDistanceFromAvg = 5.0;
    private double averageAxonLength = 3.0;
    private double wifthOfAxonLengthDistribution = 1.0;
    private double maxLengthOfAxonTerminals = 1.0;
    private double averageNumberOfAxonTerminals = 20;
    private double widthOfAxonTerminalNumberDistribution = 10;
    private double averageNumberOfDendrites = 40;
    private double widthOfDendriteNumberDistribution = 15;
    private double averageLengthOfDendrites = 1.5;
    private double widthOfDendriteLengthsDistribution = 0.5;
    private double ratioExcitatoryToInhibitoryNeurons = 0.85;

    public NetworkDesignerBuilder setNumberOfNeurons(int numberOfNeurons) {
        this.numberOfNeurons = numberOfNeurons;
        return this;
    }

    public NetworkDesignerBuilder setAverageLocationOfSoma(double[] averageLocationOfSoma) {
        if (averageLocationOfSoma.length != 2) {
            throw new IllegalArgumentException("Soma location avg must be of length 2");
        }
        this.averageLocationOfSoma = averageLocationOfSoma;
        return this;
    }

    public NetworkDesignerBuilder setMaxSomaDistanceFromAvg(double maxSomaDistanceFromAvg) {
        this.maxSomaDistanceFromAvg = maxSomaDistanceFromAvg;
        return this;
    }

    public NetworkDesignerBuilder setAverageAxonLength(double averageAxonLength) {
        this.averageAxonLength = averageAxonLength;
        return this;
    }

    public NetworkDesignerBuilder setWifthOfAxonLengthDistribution(double wifthOfAxonLengthDistribution) {
        this.wifthOfAxonLengthDistribution = wifthOfAxonLengthDistribution;
        return this;
    }

    public NetworkDesignerBuilder setMaxLengthOfAxonTerminals(double maxLengthOfAxonTerminals) {
        this.maxLengthOfAxonTerminals = maxLengthOfAxonTerminals;
        return this;
    }

    public NetworkDesignerBuilder setAverageNumberOfAxonTerminals(double averageNumberOfAxonTerminals) {
        this.averageNumberOfAxonTerminals = averageNumberOfAxonTerminals;
        return this;
    }

    public NetworkDesignerBuilder setWidthOfAxonTerminalNumberDistribution(double widthOfAxonTerminalNumberDistribution) {
        this.widthOfAxonTerminalNumberDistribution = widthOfAxonTerminalNumberDistribution;
        return this;
    }

    public NetworkDesignerBuilder setAverageNumberOfDendrites(double averageNumberOfDendrites) {
        this.averageNumberOfDendrites = averageNumberOfDendrites;
        return this;
    }

    public NetworkDesignerBuilder setWidthOfDendriteNumberDistribution(double widthOfDendriteNumberDistribution) {
        this.widthOfDendriteNumberDistribution = widthOfDendriteNumberDistribution;
        return this;
    }

    public NetworkDesignerBuilder setAverageLengthOfDendrites(double averageLengthOfDendrites) {
        this.averageLengthOfDendrites = averageLengthOfDendrites;
        return this;
    }

    public NetworkDesignerBuilder setWidthOfDendriteLengthsDistribution(double widthOfDendriteLengthsDistribution) {
        this.widthOfDendriteLengthsDistribution = widthOfDendriteLengthsDistribution;
        return this;
    }

    public NetworkDesignerBuilder setRatioExcitatoryToInhibitoryNeurons(double ratioExcitatoryToInhibitoryNeurons) {
        if (ratioExcitatoryToInhibitoryNeurons < 0 || ratioExcitatoryToInhibitoryNeurons > 1) {
            throw new IllegalArgumentException("ratio of excitatory to inhibitory must lie between 0 and 1");
        }
        this.ratioExcitatoryToInhibitoryNeurons = ratioExcitatoryToInhibitoryNeurons;
        return this;
    }

    public NetworkDesigner createNetworkDesigner() {
        return new NetworkDesigner(numberOfNeurons, averageLocationOfSoma, maxSomaDistanceFromAvg, averageAxonLength, wifthOfAxonLengthDistribution, maxLengthOfAxonTerminals, averageNumberOfAxonTerminals, widthOfAxonTerminalNumberDistribution, averageNumberOfDendrites, widthOfDendriteNumberDistribution, averageLengthOfDendrites, widthOfDendriteLengthsDistribution, ratioExcitatoryToInhibitoryNeurons);
    }
}