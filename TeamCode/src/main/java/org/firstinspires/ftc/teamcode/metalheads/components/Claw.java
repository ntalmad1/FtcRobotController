package org.firstinspires.ftc.teamcode.metalheads.components;

import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.rotator.Rotator;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponent;

/**
 *
 */
public class Claw extends Component {

    /**
     */
    public ServoComponent pincher;

    /**
     */
    public Rotator clawRotator;

    /**
     */
    private ClawConfig config;

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
     * @return
     */
    public Action closeClawAction() {
        return this.pincher.gotoPositionAction(config.pincherConfig.maxPosition, 1);
    }

    /**
     *
     * @return
     */
    public Action openClawAction() {
        return this.pincher.gotoPositionAction(config.pincherConfig.minPosition, 1);
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

        if (this.isDebug()) {
            telemetry.addData("Pincher Pos:", this.pincher.getPosition());
            telemetry.addData("Claw Rotator Pos:", this.clawRotator.getPosition());
        }
    }

}
