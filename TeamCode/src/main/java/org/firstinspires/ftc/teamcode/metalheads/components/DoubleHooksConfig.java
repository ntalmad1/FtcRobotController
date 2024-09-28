package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponentConfig;

/**
 *
 */
public class DoubleHooksConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public EncodedMotorConfig linearActuatorConfig;

    /**
     */
    public ServoComponentConfig doubleServosConfig;

    /**
     * Constructor
     *
     * @param robot
     */
    public DoubleHooksConfig(IsaacBot robot) {
        this.robot = robot;

        this.linearActuatorConfig = new EncodedMotorConfig(robot);

        this.doubleServosConfig = new ServoComponentConfig(robot);
    }
}
