package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.potentiometer.PotentiometerConfig;
import org.firstinspires.ftc.teamcode.library.potentiometermotor.PotentiometerMotor;
import org.firstinspires.ftc.teamcode.library.potentiometermotor.PotentiometerMotorConfig;

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
    public PotentiometerMotorConfig viperSlideConfig;

    /**
     * Constructor
     *
     * @param robot
     */
    public ArmConfig(IsaacBot robot) {
        this.robot = robot;

        this.mainBoomConfig = new EncodedMotorConfig(robot);
        this.viperSlideConfig = new PotentiometerMotorConfig(robot);
    }
}
