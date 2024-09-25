package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.utility.Control;

/**
 *
 */
public class EncodedMotorConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public boolean debug = false;

    /**
     */
    public String motorName;

    /**
     */
    public DcMotorSimple.Direction initialMotorDirection = DcMotorSimple.Direction.FORWARD;

    /**
     */
    public int minTics;

    /**
     */
    public int maxTics;

    /**
     */
    public int increment;

    /**
     */
    public boolean brakeOn = false;

    /**
     */
    public Control control = Control.Gp2_RightStickX;

    /**
     *
     * @param robot
     */
    public EncodedMotorConfig (IsaacBot robot) {
        this.robot = robot;
    }

}
