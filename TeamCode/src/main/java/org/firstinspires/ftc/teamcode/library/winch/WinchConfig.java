package org.firstinspires.ftc.teamcode.library.winch;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.motor.EncodedMotorConfig;

/**
 *
 */
public class WinchConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public EncodedMotorConfig encodedMotorConfig;

    /**
     */
    public boolean debug = false;

    /**
     *
     * @param robot
     */
    public WinchConfig (IsaacBot robot) {
        this.robot = robot;
    }
}
