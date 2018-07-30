package output;

import neuron.Neuron;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class NetworkSaver {

    private List<Neuron> neurons;
    private String pathToSaveDirectory;

    public NetworkSaver(List<Neuron> neurons, String pathToSaveDirectory) {
        this.neurons = neurons;
        this.pathToSaveDirectory = pathToSaveDirectory;
    }

    public void saveNeuronConfiguration() {

        List<double[]> allBranchBeginningCoordinates = this.neurons.stream()
                .map(neuron -> neuron.getAllBranchesInSingleList())
                .flatMap(branches -> branches.stream())
                .map(branch -> branch.getBranchLocationInfo())
                .collect(Collectors.toList());

        String fileName = "branches.csv";

        try {

            PrintWriter pw = new PrintWriter(new File(this.pathToSaveDirectory + fileName));

            allBranchBeginningCoordinates.forEach(branchCoordinates -> pw.write(
                            branchCoordinates[0]+","
                            + branchCoordinates[1]+","
                            + branchCoordinates[2]+","
                            + branchCoordinates[3]+"\n"
            ));

            pw.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
