package output;

import neuron.Neuron;
import neuron.branch.Branch;

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

        List<Branch> allBranches = this.neurons.stream()
                .map(neuron -> neuron.getAllBranchesInSingleList())
                .flatMap(branches -> branches.stream())
                .collect(Collectors.toList());

        String fileName = "branches.csv";

        try {

            File file = new File(this.pathToSaveDirectory + fileName);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();

            PrintWriter pw = new PrintWriter(file);

            pw.write("x1,y1,x2,y2,branch_uid\n");

            allBranches.forEach(branch -> pw.write(
                            branch.getCoordinatesOfBranchBeginning()[0]+","
                            + branch.getCoordinatesOfBranchBeginning()[1]+","
                            + branch.getCoordinatesOfBranchEnd()[0]+","
                            + branch.getCoordinatesOfBranchEnd()[1]+","
                            + branch.getBranchUniqueId() + "\n"
            ));

            pw.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
