package org.firstinspires.ftc.teamcode.metalheads.base;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.metalheads.components.DoubleHooksConfig;

/**
 *
 */
public class CompBotConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public DoubleHooksConfig doubleHooksConfig;

    /**
     */
    public boolean debug = false;

    /**
     * Constructor
     *
     * @param robot
     */
    public CompBotConfig(IsaacBot robot) {
        this.robot = robot;

        this.doubleHooksConfig = new DoubleHooksConfig(robot);
    }

}
