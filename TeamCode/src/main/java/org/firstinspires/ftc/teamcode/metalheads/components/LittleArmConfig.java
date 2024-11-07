package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.potentiometermotor.PotentiometerMotorConfig;
import org.firstinspires.ftc.teamcode.library.rotator.Rotator;
import org.firstinspires.ftc.teamcode.library.rotator.RotatorConfig;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponent;
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
    public RotatorConfig doubleServosConfig;

    /**
     */
    public RotatorConfig middleServoConfig;

    /**
     */
    public RotatorConfig clawRotatorConfig;

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

        this.doubleServosConfig = new RotatorConfig(robot);
        this.middleServoConfig = new RotatorConfig(robot);
        this.clawRotatorConfig = new RotatorConfig(robot);
        this.clawPincherConfig = new ServoComponentConfig(robot);
    }
}
