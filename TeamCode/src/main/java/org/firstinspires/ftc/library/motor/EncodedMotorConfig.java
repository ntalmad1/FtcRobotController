package org.firstinspires.ftc.library.motor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.library.IsaacBot;

/**
 *
 */
public class EncodedMotorConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public String motorName;

    /**
     */
    public DcMotorSimple.Direction initialMotorDirection = DcMotorSimple.Direction.FORWARD;

    /**
     */
    public boolean brakeOn = false;

}
