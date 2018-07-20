package main.java.neuron.Branch;

import java.util.List;

public class InputBranch extends Branch {

    public InputBranch(double orientationInRadians,
                       double lengthToSynapse,
                       double lengthToSoma,
                       List<InputBranch> branches) {
        super(orientationInRadians, lengthToSynapse, lengthToSoma, branches);
    }

}
