package org.firstinspires.ftc.teamcode.library.arm.boom;

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
    public String servoName;

    /**
     */
    public boolean isDualServo = false;

    /**
     */
    public String secondaryServoName;

    /**
     */
    public Servo.Direction direction;

    /**
     */
    public Control controllerInputMethod;

    public Control controllerInputMethod2;

    /**
     */
    public boolean invertInput = false;

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
    public double maxIncrement = 0.001;

    /**
     */
    public double degree = 0.0033;
}
