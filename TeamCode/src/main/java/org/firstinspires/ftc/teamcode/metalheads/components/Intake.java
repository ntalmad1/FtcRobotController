package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.continuousservo.ContinuousServo;
import org.firstinspires.ftc.teamcode.library.rotator.Rotator;

/**
 *
 */
public class Intake extends Component {

    /**
     */
    private IntakeConfig config;

    /**
     */
    private Rotator hServo;

    /**
     */
    private Rotator vServo;

    /**
     */
    private ContinuousServo roller;

    /**
     * Constructor
     *
     * @param intakeConfig
     */
    public Intake(IntakeConfig intakeConfig) {
        super(intakeConfig.robot);

        this.config = intakeConfig;

        this.hServo = new Rotator(this.config.hServoConfig);
        this.vServo = new Rotator(this.config.vServoConfig);

        this.roller = new ContinuousServo(this.config.rollerConfig);
    }


    /**
     *
     */
    @Override
    public void init() {
       super.init();

       this.hServo.init();
       this.vServo.init();
       this.roller.init();
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        this.hServo.run();
        this.vServo.run();
        this.roller.run();
    }
}
