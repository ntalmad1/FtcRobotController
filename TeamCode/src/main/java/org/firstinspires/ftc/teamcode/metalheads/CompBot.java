package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.metalheads.base.CompBotConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.Arm;
import org.firstinspires.ftc.teamcode.metalheads.components.Claw;
import org.firstinspires.ftc.teamcode.metalheads.components.DoubleHooks;
import org.firstinspires.ftc.teamcode.metalheads.components.FlapperBars;
import org.firstinspires.ftc.teamcode.metalheads.components.Intake;
import org.firstinspires.ftc.teamcode.metalheads.components.Winch;

@TeleOp(name="CompBot", group="Base")
//@Disabled
public class CompBot extends IsaacBot {

    /**
     */
    protected CompBotConfig config;

    protected Arm arm;

    /**
     */
    private Claw claw;

    /**
     */
    private DoubleHooks doubleHooks;

    /**
     */
    private FlapperBars flapperBars;

    /**
     */
    private Intake intake;

    /**
     */
    private Winch winch;

    /**
     * Constructor
     *
     */
    public CompBot(){
        this(null);
    }


    /**
     * Constructor
     *
     * @param compBotConfig
     */
    public CompBot(CompBotConfig compBotConfig) {
        super();

        if (compBotConfig == null) {
            compBotConfig = new CompBotConfig(this);
        }

        this.config = compBotConfig;

        // this.config.debugArm = true;
        // this.config.debugClaw = true;
        // this.config.debugDoubleHooks = true;
        // this.config.debugFlapperBars = true;
        // this.config.debugIntake = true;
        // this.config.debugWinch = true;

        this.config.debugAll = true;

        this.arm = new Arm(this.config.armConfig);
        this.claw = new Claw(this.config.clawConfig);
        this.doubleHooks = new DoubleHooks(this.config.doubleHooksConfig);
        this.flapperBars = new FlapperBars(this.config.flapperBarsConfig);
        this.intake = new Intake(this.config.intakeConfig);
        this.winch = new Winch(this.config.winchConfig);
    }

    /**
     *
     */
    @Override
    public void initBot(){
        super.initBot();

        this.arm.init();
        this.claw.init();
        this.doubleHooks.init();
        this.flapperBars.init();
        this.intake.init();
        this.winch.init();
    }

    /**
     *
     */
    @Override
    public void go (){

    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        this.arm.run(this.config.debugArm || this.config.debugAll);
        this.claw.run(this.config.debugClaw || this.config.debugAll);
        this.doubleHooks.run(this.config.debugDoubleHooks || this.config.debugAll);
        this.flapperBars.run(this.config.debugFlapperBars || this.config.debugAll);
        this.intake.run(this.config.debugIntake || this.config.debugAll);
        this.winch.run(this.config.debugWinch || this.config.debugAll);

        if (this.config.debugAll
            || this.config.debugArm
            || this.config.debugClaw
            || this.config.debugDoubleHooks
            || this.config.debugFlapperBars
            || this.config.debugIntake
            || this.config.debugWinch) {
            telemetry.update();
        }
    }

    /**
     *
     */
    @Override
    public void onStop() {

    }
}