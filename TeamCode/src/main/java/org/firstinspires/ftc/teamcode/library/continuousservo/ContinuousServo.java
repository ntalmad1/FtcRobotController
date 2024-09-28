package org.firstinspires.ftc.teamcode.library.continuousservo;

import com.qualcomm.robotcore.hardware.CRServo;
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

        servo = robot.hardwareMap.get(CRServo.class, this.config.servoName);
    }

    /**
     *
     */
    @Override
    public void init() {
       super.init();

       servo.setDirection(this.config.direction);
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();
    }

    /**
     *
     * @param power
     */
    public void setPower(double power) {
        this.servo.setPower(power);
    }
}
