package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;

/**
 *
 */
public class ArmConfig {
    /**
     */
    public IsaacBot robot;

    /**
     */
    public EncodedMotorConfig mainBoomConfig;

    /**
     */
    public EncodedMotorConfig viperSlideConfig;

    /**
     * Constructor
     *
     * @param robot
     */
    public ArmConfig(IsaacBot robot) {
        this.robot = robot;

        this.mainBoomConfig = new EncodedMotorConfig(robot);
        this.viperSlideConfig = new EncodedMotorConfig(robot);
    }
}
