package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.rotator.Rotator;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponent;

/**
 *
 */
public class Claw extends Component {

    /**
     */
    private Rotator clawRotator;

    /**
     */
    private ClawConfig config;

    /**
     */
    private ServoComponent pincher;

    /**
     * Constructor
     *
     * @param clawConfig
     */
    public Claw(ClawConfig clawConfig) {
        super(clawConfig.robot);
        this.config = clawConfig;

        this.clawRotator = new Rotator(this.config.clawRotatorConfig);
        this.pincher = new ServoComponent(this.config.pincherConfig);
    }

    /**
     *
     */
    @Override
    public void init() {
        super.init();

        this.clawRotator.init();
        this.pincher.init();
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        this.clawRotator.run();
        this.pincher.run();
    }

}
