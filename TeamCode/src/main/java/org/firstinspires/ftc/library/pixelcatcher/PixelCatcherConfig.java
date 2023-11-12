package org.firstinspires.ftc.library.pixelcatcher;

import org.firstinspires.ftc.library.Control;
import org.firstinspires.ftc.library.IsaacBot;

/**
 *
 */
public class PixelCatcherConfig {

    /**
     *
     */
    public IsaacBot robot;

    /**
     *
     */
    public String leftArmServoName;

    /**
     *
     */
    public double leftArmServoOpenedPos;

    /**
     *
     */
    public double leftArmServoClosedPos;

    /**
     *
     */
    public double leftArmServoInitPos;

    /**
     *
     */
    public PixelCatcher.ArmPosition leftArmInitPos = PixelCatcher.ArmPosition.CLOSED;

    /**
     */
    public Control leftArmToggle = Control.Gp1_LeftTrigger_Down;

    /**
     *
     */
    public String rightArmServoName;

    /**
     *
     */
    public double rightArmServoOpenedPos;

    /**
     *
     */
    public double rightArmServoClosedPos;

    /**
     *
     */
    public double rightArmServoInitPos;

    /**
     *
     */
    public PixelCatcher.ArmPosition rightArmIntiPos = PixelCatcher.ArmPosition.CLOSED;

    /**
     */
    public Control rightArmToggle = Control.Gp1_RightTrigger_Down;

    /**
     */
    public String leftPixelSensorName;

    /**
     */
    public String rightPixelSensorName;
}
