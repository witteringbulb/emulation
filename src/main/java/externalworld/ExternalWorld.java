package externalworld;

import network.Brain;

public abstract class ExternalWorld {

    private Brain brain;

    public ExternalWorld(Brain brain) {
        this.setBrain(brain);
    }

    public abstract void coordinateSensoryNeuronFiringThisTimeStep();

    public Brain getBrain() {
        return this.brain;
    }

    public void setBrain(Brain brain) {
        this.brain = brain;
    }
}
