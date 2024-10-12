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
    public ServoComponent pincher;

    /**
     */
    private Rotator clawRotator;

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

//        this.addGp1_A_PressHandler(event -> {
//
//            if (pincher.getPosition() > 0.5) {
//                pincher.setPosition(config.pincherConfig.minPosition);
//            }
//            else {
//                pincher.setPosition(config.pincherConfig.maxPosition);
//            }
//
//        });
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
