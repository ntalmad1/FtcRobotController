package org.firstinspires.ftc.teamcode.library.dcmotor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.utility.Control;

/**
 *
 */
public class DcMotorComponentConfig {

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
    public boolean brakeOn = false;

    /**
     */
    public Control control = null;

    /**
     * Constructor
     *
     * @param robot
     */
    public DcMotorComponentConfig(IsaacBot robot) {
        this.robot = robot;
    }
}
