package org.firstinspires.ftc.teamcode.metalheads.components;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.continuousservo.ContinuousServo;
import org.firstinspires.ftc.teamcode.library.rotator.Rotator;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponent;
import org.firstinspires.ftc.teamcode.metalheads.Constants;

/**
 *
 */
public class Intake extends Component {

    /**
     */
    private IntakeConfig config;

    /**
     */
    public Rotator hServo;

    /**
     */
    public Rotator vServo;

    /**
     */
    // public ContinuousServo roller;
    public ServoComponent pincher;

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

        this.pincher = new ServoComponent(this.config.pincherConfig);
    }


    /**
     *
     */
    @Override
    public void init() {
       super.init();

       this.hServo.init();
       this.vServo.init();
       this.pincher.init();
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        this.hServo.run();
        this.vServo.run();
        this.pincher.run();

        if (this.isDebug()) {
            telemetry.addData("Intake H Servo Pos:", this.hServo.getPosition());
            telemetry.addData("Intake V Servo Pos:", this.vServo.getPosition());
            telemetry.addData("Intake Pincher Pos:", this.pincher.getPosition());
        }
    }

    /**
     *
     * @return
     */
    public Action closePincherAction() {
        return this.pincher.gotoPositionAction(Constants.INTAKE_PINCHER_CLOSE_POS, 1);
    }

    /**
     *
     * @return
     */
    public Action openPincherAction() {
        return this.pincher.gotoPositionAction(Constants.INTAKE_PINCHER_OPEN_POS, 1);
    }
}
