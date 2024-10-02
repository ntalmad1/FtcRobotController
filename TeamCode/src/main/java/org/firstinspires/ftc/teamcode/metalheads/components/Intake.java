package org.firstinspires.ftc.teamcode.metalheads.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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

        this.addGp2_Right_Bumper_DownHandler(event -> {
            this.roller.setDirection(DcMotorSimple.Direction.FORWARD);
            this.roller.setPower(1);
        });

        this.addGp2_Right_Bumper_UpHandler(event -> {
            this.roller.stop();

        });

        this.addGp2_Left_Bumper_DownHandler(event -> {
            this.roller.setDirection(DcMotorSimple.Direction.REVERSE);
            this.roller.setPower(1);
        });

        this.addGp2_Left_Bumper_UpHandler(event -> {
            this.roller.stop();
        });
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
