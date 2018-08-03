package neuron.branch;

public class BranchIdGeneratorStatic {

    private static int nextAvailableUniqueId = 0;

    public static int getNextAvailableUniqueId() {
        return nextAvailableUniqueId++;
    }

}
