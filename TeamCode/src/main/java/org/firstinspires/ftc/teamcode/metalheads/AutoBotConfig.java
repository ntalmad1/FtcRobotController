package org.firstinspires.ftc.teamcode.metalheads;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

/**
 *
 */
public class AutoBotConfig extends CompBotConfig {

    /**
     * Constructor
     *
     * @param robot
     */
    public AutoBotConfig(IsaacBot robot) {
        super(robot);

        this.useIntake = true;
        this.useClaw = true;
        this.useArm = true;
        this.useFlapperBars = false;
        this.useDoubleHooks = false;
        this.useWinch = false;
    }
}
