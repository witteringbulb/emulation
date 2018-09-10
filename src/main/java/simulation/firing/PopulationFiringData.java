package simulation.firing;

import java.util.HashMap;

public class PopulationFiringData {

    public final HashMap<Integer, Double> mapOfDendriteUidsToSignalAmplitudes;

    public PopulationFiringData(int[] dendriteBranchUids, double[] signalAmplitudes) {

        if (dendriteBranchUids.length != signalAmplitudes.length) {
            throw new IllegalArgumentException("arrays should be of the same length");
        }

        this.mapOfDendriteUidsToSignalAmplitudes = new HashMap<Integer, Double>();
        for (int i = 0; i < dendriteBranchUids.length; i++) {
            this.mapOfDendriteUidsToSignalAmplitudes.put(dendriteBranchUids[i], signalAmplitudes[i]);
        }
    }

}
