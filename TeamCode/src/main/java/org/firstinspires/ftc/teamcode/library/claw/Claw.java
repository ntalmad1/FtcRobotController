package org.firstinspires.ftc.teamcode.library.arm;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.arm.boom.Boom;
import org.firstinspires.ftc.teamcode.library.arm.rotator.Rotator;
import org.firstinspires.ftc.teamcode.library.component.Component;

/**
 *
 */
public class Claw extends Component {

    /**
     */
    private Boom clawBoom;

    /**
     */
    private Rotator clawRotator;

    /**
     */
    private ClawConfig config;

    /**
     * Constructor
     *
     */
    public Claw(ClawConfig config) {
        super(config.robot);
        this.config = config;

        this.clawBoom = new Boom(config.clawBoomConfig);
        this.clawRotator = new Rotator(config.clawRotatorConfig);
    }

    /**
     *
     */
    public void init () {
        super.init();

        this.clawBoom.init();
        this.clawRotator.init();
    }

    /**
     *
     */
    public void run () {
        this.clawBoom.run();
        this.clawRotator.run();
    }
}
