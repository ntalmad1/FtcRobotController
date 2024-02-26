package org.firstinspires.ftc.library.pixelcatcher;

import org.firstinspires.ftc.library.utility.Control;
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
    public PixelCatcher.ArmPosition rightArmInitPos = PixelCatcher.ArmPosition.CLOSED;

    /**
     */
    public Control rightArmToggle = Control.Gp1_RightTrigger_Down;

    public String winchServoName;

    public Control winchToggle1 = Control.Gp1_RightBumper_Down;

    public Control winchToggle2 = Control.Gp1_LeftBumper_Down;

    public double winchUpPosition;

    public double winchDownPosition;

    public double winchServoInitPos;

    public PixelCatcher.WinchPosition winchInitPosition = PixelCatcher.WinchPosition.UP;
}
