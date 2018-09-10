package simulation;

import neuron.Neuron;
import neuron.branch.Branch;
import neuron.branch.Dendrite;
import output.SignalSaver;
import simulation.firing.PopulationFiringData;

import java.util.HashMap;
import java.util.List;

public class PopulationSimulator {

    private final List<Neuron> neuronPopulation;
    private final SignalSaver signalSaver;

    private HashMap<Integer, Branch> mapOfBranchUidToBranch;

    public PopulationSimulator(List<Neuron> neuronPopulation, SignalSaver signalSaver) {
        this.neuronPopulation = neuronPopulation;
        this.signalSaver = signalSaver;
    }

    public void simulateWithPredeterminedFirings(int numberOfTimeIncrements, HashMap<Integer, PopulationFiringData> allFiringData) {
        for (int i = 0; i < numberOfTimeIncrements; i++) {
            this.signalSaver.saveSignalsAtThisTimeStep();
            PopulationFiringData populationFiringDataThisTimestep = allFiringData.get(i);
            this.simulateForOneTimeIncrementWithPredeterminedFirings(populationFiringDataThisTimestep);
        }
    }

    private void simulateForOneTimeIncrementWithPredeterminedFirings(PopulationFiringData populationFiringDataThisTimestep) {
        for (Neuron neuron : neuronPopulation) {
            neuron.propagateSignalsOneTimeIncrement();
        }
        if (populationFiringDataThisTimestep != null) {
            for (int branchUid : populationFiringDataThisTimestep.mapOfDendriteUidsToSignalAmplitudes.keySet()) {
                getMapOfBranchUidToBranch().get(branchUid).fire(populationFiringDataThisTimestep.mapOfDendriteUidsToSignalAmplitudes.get(branchUid));
            }
        }
    }

    public void simulateWithAllDendritesFiringRandomly(int numberOfTimeIncrements, double firingProbabilityPerDendritePerTimeIncrement, double dendriteFiringAmplitude) {
        for (int i = 0; i < numberOfTimeIncrements; i++) {
            this.signalSaver.saveSignalsAtThisTimeStep();
            this.simulateForOneTimeIncrementWithAllDendritesFiringRandomly(firingProbabilityPerDendritePerTimeIncrement, dendriteFiringAmplitude);
        }
    }

    private void simulateForOneTimeIncrementWithAllDendritesFiringRandomly(double firingProbabilityPerDendritePerTimeIncrement, double dendriteFiringAmplitude) {
        for (Neuron neuron : neuronPopulation) {
            neuron.propagateSignalsOneTimeIncrement();
            for (Dendrite dendrite : neuron.getSoma().getDendrites()) {
                if (Math.random() < firingProbabilityPerDendritePerTimeIncrement) {
                    dendrite.fire(dendriteFiringAmplitude);
                }
            }
        }
    }

    public HashMap<Integer, Branch> getMapOfBranchUidToBranch() {
        if (mapOfBranchUidToBranch == null) {
            mapOfBranchUidToBranch = new HashMap<Integer, Branch>();
            for (Neuron neuron : neuronPopulation) {
                for (Branch branch : neuron.getAllBranchesInSingleList()) {
                    mapOfBranchUidToBranch.put(branch.getBranchUniqueId(), branch);
                }
            }
        }
        return mapOfBranchUidToBranch;
    }

}
