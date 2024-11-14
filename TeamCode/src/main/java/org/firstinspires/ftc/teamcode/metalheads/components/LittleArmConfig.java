package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponentConfig;

/**
 *
 */
public class LittleArmConfig {
    /**
     */
    public IsaacBot robot;

    /**
     */
    public ServoComponentConfig doubleServosConfig;

    /**
     */
    public ServoComponentConfig middleServoConfig;

    /**
     */
    public ServoComponentConfig clawRotatorConfig;

    /**
     */
    public ServoComponentConfig clawPincherConfig;

    /**
     * Constructor
     *
     * @param robot
     */
    public LittleArmConfig(IsaacBot robot) {
        this.robot = robot;

        this.doubleServosConfig = new ServoComponentConfig(robot);
        this.middleServoConfig = new ServoComponentConfig(robot);
        this.clawRotatorConfig = new ServoComponentConfig(robot);
        this.clawPincherConfig = new ServoComponentConfig(robot);
    }
}
