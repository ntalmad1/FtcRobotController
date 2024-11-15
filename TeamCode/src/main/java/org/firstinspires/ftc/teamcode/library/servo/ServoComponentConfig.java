package org.firstinspires.ftc.teamcode.library.servo;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.utility.Control;

/**
 *
 */
public class ServoComponentConfig {

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
    public double  secondaryServoOffset = 0;

    /**
     */
    public Servo.Direction direction;

    /**
     */
    public Control controllerInputMethod = Control.NONE;

    /**
     */
    public Control controllerInputMethod2 = Control.NONE;

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
    public boolean lazyInit = false;

    /**
     */
    public double maxIncrement = 0.001;

    /**
     */
    public double degree = 0.0033;

    /**
     */
    public double gearRatio = 1;

    /**
     */
    public boolean debug = false;

    /**
     * Constructor
     *
     * @param robot
     */
    public ServoComponentConfig(IsaacBot robot) {
        this.robot = robot;
    }
}
