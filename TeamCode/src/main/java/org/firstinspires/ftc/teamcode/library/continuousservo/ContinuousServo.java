package org.firstinspires.ftc.teamcode.library.continuousservo;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.component.Component;

public class ContinuousServo extends Component {

    private ContinuousServoConfig config;

    /**
     */
    private CRServo servo;

    /**
     * Constructor
     *
     * @param continuousServoConfig
     */
    public ContinuousServo(ContinuousServoConfig continuousServoConfig) {
        super(continuousServoConfig.robot);

        this.config = continuousServoConfig;
    }

    /**
     *
     */
    @Override
    public void init() {
       super.init();

       servo = robot.hardwareMap.get(CRServo.class, this.config.servoName);

       servo.setDirection(this.config.direction);
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();
    }

    public void setDirection(DcMotorSimple.Direction direction) {
        servo.setDirection(direction);
    }

    /**
     *
     * @param power
     */
    public void setPower(double power) {
        this.servo.setPower(power);
    }

    /**
     *
     * @param power
     */
    public void run(double power) {
        this.setPower(power);
    }

    /**
     *
     */
    public void stop() {
        this.servo.setPower(0);
    }
}
