package externalworld;

import network.Brain;
import neuron.ExternalInputNeuron;

public class AllNeuronsFireRandomlyExternalWorld extends ExternalWorld {

    private double chanceToFirePerNeuronPerTimestep;
    private double firingAmplitude;

    public AllNeuronsFireRandomlyExternalWorld(Brain brain, double chanceToFirePerNeuronPerTimestep, double firingAmplitude) {
        super(brain);
        this.setChanceToFirePerNeuronPerTimestep(chanceToFirePerNeuronPerTimestep);
        this.firingAmplitude = firingAmplitude;
    }

    @Override
    public void coordinateSensoryNeuronFiringThisTimeStep() {
        for (ExternalInputNeuron neuron : this.getBrain().getInputNeurons()) {
            fireNeuronIfRequired(neuron);
        }
    }

    private void fireNeuronIfRequired(ExternalInputNeuron neuron) {
        if (Math.random() < chanceToFirePerNeuronPerTimestep) {
            neuron.getAxon().fire(firingAmplitude);
        }
    }

    public void setChanceToFirePerNeuronPerTimestep(double chanceToFirePerNeuronPerTimestep) {
        this.chanceToFirePerNeuronPerTimestep = chanceToFirePerNeuronPerTimestep;
    }

}
