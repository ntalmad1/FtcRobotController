package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.potentiometermotor.PotentiometerMotorConfig;

/**
 *
 */
public class BigArmConfig {
    /**
     */
    public IsaacBot robot;

    /**
     */
    public String viperSlidesTouchSensorName;

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
    public BigArmConfig(IsaacBot robot) {
        this.robot = robot;

        this.mainBoomConfig = new EncodedMotorConfig(robot);
        this.viperSlideConfig = new EncodedMotorConfig(robot);
    }
}
