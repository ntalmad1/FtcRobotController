package org.firstinspires.ftc.teamcode.library.winch;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.motor.EncodedMotorConfig;

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
