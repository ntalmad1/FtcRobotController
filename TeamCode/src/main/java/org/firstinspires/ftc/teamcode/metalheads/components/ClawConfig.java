package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.rotator.RotatorConfig;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponentConfig;

/**
 *
 */
public class ClawConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public RotatorConfig clawRotatorConfig;

    /**
     */
    public ServoComponentConfig pincherConfig;

    /***
     * Constructor
     *
     * @param robot
     */
    public ClawConfig(IsaacBot robot) {
        this.robot = robot;

        this.clawRotatorConfig = new RotatorConfig(robot);

        this.pincherConfig = new ServoComponentConfig(robot);
    }

}
