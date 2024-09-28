package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.continuousservo.ContinuousServoConfig;
import org.firstinspires.ftc.teamcode.library.rotator.RotatorConfig;

/**
 *
 */
public class IntakeConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public RotatorConfig hServoConfig;

    /**
     */
    public ContinuousServoConfig rollerConfig;

    /**
     */
    public RotatorConfig vServoConfig;

    /**
     * Constructor
     *
     * @param robot
     */
    public IntakeConfig(IsaacBot robot) {
        this.robot = robot;

        this.hServoConfig = new RotatorConfig(robot);
        this.vServoConfig = new RotatorConfig(robot);

        this.rollerConfig = new ContinuousServoConfig(robot);
    }

}
