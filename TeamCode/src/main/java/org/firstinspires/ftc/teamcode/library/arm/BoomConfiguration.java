package org.firstinspires.ftc.teamcode.library.arm;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.Control;
import org.firstinspires.ftc.teamcode.library.IsaacBot;

/**
 *
 */
public class BoomConfiguration {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public String deviceName;

    /**
     */
    public Servo.Direction direction;

    /**
     */
    public Control controllerInputMethod;

    /**
     */
    public double minPosition = 0;

    /**
     */
    public double maxPosition = 1;

    /**
     */
    public double zeroDegreePosition;

    /**
     */
    public double homePosition;

    /**
     */
    public double maxIncrement;

    /**
     */
    public double degree = 0.0033;
}
