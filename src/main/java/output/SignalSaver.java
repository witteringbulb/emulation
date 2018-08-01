package output;

import neuron.Neuron;
import neuron.branch.Branch;
import neuron.signal.Signal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class SignalSaver {

    private List<Neuron> neurons;
    private String pathToSaveDirectory;
    private int timeStep;

    public SignalSaver(List<Neuron> neurons, String pathToSaveDirectory) {
        this.neurons = neurons;
        this.pathToSaveDirectory = pathToSaveDirectory;
        this.timeStep = 0;
    }

    public void saveSignalsAtThisTimeStep() {

        String fileName = this.timeStep+"_allSignals.csv";

        try {

            PrintWriter pw = new PrintWriter(new File(this.pathToSaveDirectory + fileName));

            pw.write("mean_loc_x,mean_loc_y,direction_x,direction_y,amplitude,width,type\n");

            List<Signal> allSignalsInPopulation = neurons.stream()
                    .map(neuron -> neuron.getAllBranchesInSingleList())
                    .flatMap(branches -> branches.stream())
                    .map(branch -> branch.getSignals())
                    .flatMap(signals -> signals.stream())
                    .collect(Collectors.toList());
            for (Signal signal : allSignalsInPopulation) {
                pw.write(signal.getMeanLocation()[0]+","
                        +signal.getMeanLocation()[1]+","
                        +signal.getDirection()[0]+","
                        +signal.getDirection()[1]+","
                        +signal.getAmplitude()+","
                        +signal.getWidth()+","
                        +signal.getTypeAsString()+"\n");
            }
            this.timeStep++;
            pw.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

