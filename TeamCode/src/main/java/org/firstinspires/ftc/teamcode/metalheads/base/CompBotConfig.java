package org.firstinspires.ftc.teamcode.metalheads.base;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.metalheads.components.ArmConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.ClawConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.DoubleHooksConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.FlapperBarsConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.IntakeConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.WinchConfig;

/**
 *
 */
public class CompBotConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public ArmConfig armConfig;

    /**
     */
    public ClawConfig clawConfig;

    /**
     */
    public DoubleHooksConfig doubleHooksConfig;

    /**
     */
    public FlapperBarsConfig flapperBarsConfig;

    /**
     */
    public IntakeConfig intakeConfig;

    /**
     */
    public WinchConfig winchConfig;

    /**
     */
    public boolean debugAll = false;

    /**
     */
    public boolean debugArm = false;

    /**
     */
    public boolean debugClaw = false;

    /**
     */
    public boolean debugDoubleHooks = false;

    /**
     */
    public boolean debugFlapperBars = false;

    /**
     */
    public boolean debugIntake = false;

    /**
     */
    public boolean debugWinch = false;

    /**
     * Constructor
     *
     * @param robot
     */
    public CompBotConfig(IsaacBot robot) {
        this.robot = robot;

        this.armConfig = new ArmConfig(robot);
        this.clawConfig = new ClawConfig(robot);
        this.doubleHooksConfig = new DoubleHooksConfig(robot);
        this.flapperBarsConfig = new FlapperBarsConfig(robot);
        this.intakeConfig = new IntakeConfig(robot);
        this.winchConfig = new WinchConfig(robot);
    }
}
