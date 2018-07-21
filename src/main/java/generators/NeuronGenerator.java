package main.java.generators;

import javafx.util.Pair;
import main.java.generators.Factories.AxonTerminalFactory;
import main.java.generators.Factories.BranchFactory;
import main.java.generators.Factories.DendriteFactory;
import main.java.neuron.Branch.Axon;
import main.java.neuron.Branch.AxonTerminal;
import main.java.neuron.Branch.Branch;
import main.java.neuron.Branch.Dendrite;
import main.java.neuron.Neuron;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeuronGenerator{

    private static final double BRANCH_ROTATION_OFFSET = 0.0;

    public static Neuron generateNeuronWithUniformBranches(Pair<Double, Double> somaLocation,
                                                             int axonOrientationInRadians,
                                                             int axonLength,
                                                             int numberOfDendrites,
                                                             double lengthOfDendrites,
                                                             int numberOfAxonTerminals,
                                                             double lengthOfAxonTerminals) {
        List<Dendrite> dendrites = generateEvenlyRotationallySpacedBranchesWithFixedLengths(numberOfDendrites,
                                                                                            lengthOfDendrites,
                                                                                            Dendrite.class);
        List<AxonTerminal> axonTerminals = generateEvenlyRotationallySpacedBranchesWithFixedLengths(numberOfAxonTerminals,
                                                                                                    lengthOfAxonTerminals,
                                                                                                    AxonTerminal.class);
        return generateNeuron(somaLocation, axonOrientationInRadians, axonLength, dendrites, axonTerminals);
    }

    public static Neuron generateNeuron(Pair<Double, Double> somaLocation,
                                        int axonOrientationInRadians,
                                        int axonLength,
                                        List<Dendrite> dendrites,
                                        List<AxonTerminal> axonTerminals) {
        Neuron neuron = new Neuron(somaLocation);
        neuron.setDendrites(dendrites);
        neuron.setAxon(new Axon(axonOrientationInRadians, axonLength));
        neuron.getAxon().setAxonTerminals(axonTerminals);
        return neuron;
    }

    private static <T extends Branch> List<T> generateEvenlyRotationallySpacedBranchesWithFixedLengths(int numberOfBranches,
                                                                                                double length,
                                                                                                Class<T> branchType) {
        List<Double> branchLengths = Collections.nCopies(numberOfBranches, length);
        return generateEvenlyRotationallySpacedBranches(branchLengths, branchType);
    }

    private static <T extends Branch> List<T> generateEvenlyRotationallySpacedBranches(List<Double> branchLengths,
                                                                                Class<T> branchType) {
        BranchFactory branchFactory = getBranchFactory(branchType);
        List<T> branches = new ArrayList<T>();
        double branchRotation = BRANCH_ROTATION_OFFSET;
        for (double branchLength : branchLengths) {
            T branchToAdd = (T) branchFactory.getUnconfiguredBranch();
            branchToAdd.setLength(branchLength);
            branchToAdd.setOrientationInRadians(branchRotation);
            branches.add(branchToAdd);
            branchRotation += 2*Math.PI/branchLengths.size();
        }
        return branches;
    }

    private static BranchFactory getBranchFactory(Class branchType) {
        if (branchType == Dendrite.class) {
            return new DendriteFactory();
        } else if (branchType == AxonTerminal.class) {
            return new AxonTerminalFactory();
        } else {
            throw new IllegalArgumentException("Branch Type not recognised");
        }
    }

}
