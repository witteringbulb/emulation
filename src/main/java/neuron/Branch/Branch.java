package main.java.neuron.Branch;

import java.util.List;

public abstract class Branch<T extends Branch> {

    private double orientationInRadians;
    private double lengthToSynapse;
    private double lengthToSoma;

    private List<T> branches;

    public Branch(double orientationInRadians,
                  double lengthToSynapse,
                  double lengthToSoma,
                  List<T> branches) {
        this.orientationInRadians = orientationInRadians;
        this.lengthToSynapse = lengthToSynapse;
        this.lengthToSoma = lengthToSoma;
        this.branches = branches;
    }

    public double getOrientationInRadians() {
        return this.orientationInRadians;
    }

    public List<T> getBranches() {
        return this.branches;
    }

    public double getLengthToSynapse() {
        return lengthToSynapse;
    }

    public double getLengthToSoma() {
        return lengthToSoma;
    }

}
