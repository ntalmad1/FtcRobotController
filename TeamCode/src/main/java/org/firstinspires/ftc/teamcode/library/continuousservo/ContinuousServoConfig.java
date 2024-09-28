package org.firstinspires.ftc.teamcode.library.continuousservo;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.library.IsaacBot;

/**
 *
 */
public class ContinuousServoConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public String servoName;

    /**
     */
    public DcMotorSimple.Direction direction = DcMotorSimple.Direction.FORWARD;

    /**
     * Constructor
     *
     * @param robot
     */
    public ContinuousServoConfig(IsaacBot robot) {
        this.robot = robot;
    }

}
