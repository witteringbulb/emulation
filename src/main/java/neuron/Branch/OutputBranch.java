package main.java.neuron.Branch;

import java.util.List;

public class OutputBranch extends Branch {

    public OutputBranch(double orientationInRadians,
                        double lengthToSynapse,
                        double lengthToSoma,
                        List<OutputBranch> branches) {
        super(orientationInRadians, lengthToSynapse, lengthToSoma, branches);
    }
}
