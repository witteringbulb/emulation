package main.java.neuron.Branch;

import java.util.List;

public abstract class Branch {

    private double orientationInRadians;
    private double length;


    public Branch(double orientationInRadians,
                  double length) {
        this.orientationInRadians = orientationInRadians;
        this.length = length;
    }

    public abstract void propagateSignal();

    public double getOrientationInRadians() {
        return this.orientationInRadians;
    }

    public double getLength() {
        return length;
    }

}
