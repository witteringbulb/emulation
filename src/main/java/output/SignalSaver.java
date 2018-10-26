package output;

import neuron.InternalNeuron;
import neuron.signal.Signal;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class SignalSaver {

    private List<InternalNeuron> neurons;
    private String pathToSaveDirectory;
    private int timeStep;

    public SignalSaver(List<InternalNeuron> neurons, String pathToSaveDirectory) {
        this.neurons = neurons;
        this.pathToSaveDirectory = pathToSaveDirectory;
        this.timeStep = 0;
    }

    public void saveSignalsAtThisTimeStep() {

        String fileName = this.timeStep+"_allSignals.csv";

        try {

            PrintWriter pw = new PrintWriter(new File(this.pathToSaveDirectory + fileName));

            pw.write("branch_uid,ratio_distance_along_branch,amplitude\n");

            List<Signal> allSignalsInPopulation = neurons.stream()
                    .map(neuron -> neuron.getAllBranchesInSingleList())
                    .flatMap(branches -> branches.stream())
                    .map(branch -> branch.getSignals())
                    .flatMap(signals -> signals.stream())
                    .collect(Collectors.toList());
            for (Signal signal : allSignalsInPopulation) {
                pw.write(signal.getParentBranch().getBranchUniqueId()+","
                        +signal.getDistanceAlongBranchAsRatio()+","
                        +signal.getAmplitude()+"\n");
            }
            this.timeStep++;
            pw.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

