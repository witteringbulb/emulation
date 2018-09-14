package defaults;

public class DefaultValues {

    //Signals
    public static final double DEFAULT_SIGNAL_AMPLITUDE = 0.4;
    public static final double DEFAULT_SIGNAL_WIDTH = 0.1;
    public static final double DEFAULT_SIGNAL_SPEED = 0.05;
    public static final double DEFAULT_ZERO_SIGNAL_THRESH = 0.1;

    //Axon
    public static final double DEFAULT_AXON_TERMINAL_FIRE_THRESHOLD = 0.3;
    public static final double IMMEDIATE_POST_FIRE_PEN_ABS = 2;
    public static final double POST_FIRE_PENALTY_DECAY_ABS = 0.2;

    //Synapse
    public static final double DEFAULT_SYNAPSE_FIRE_THRESH = 0.1;
    public static final int DEFAULT_SYNAPSE_FIRE_TIME_DELAY = 5;
    public static final double DEFAULT_POSTSYNAPTIC_SIGNAL_AMPLITUDE_BASE = 0.4;

}
