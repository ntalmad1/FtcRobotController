package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.library.drivetrain.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.metalheads.components.Arm;
import org.firstinspires.ftc.teamcode.metalheads.components.Claw;
import org.firstinspires.ftc.teamcode.metalheads.components.DoubleHooks;
import org.firstinspires.ftc.teamcode.metalheads.components.FlapperBars;
import org.firstinspires.ftc.teamcode.metalheads.components.Intake;
import org.firstinspires.ftc.teamcode.metalheads.components.Winch;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

@TeleOp(name="CompBot", group="Base")
//@Disabled
public class CompBot extends IsaacBot {

    /**
     */
    protected CompBotConfig config;

    /**
     */
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

    private MecanumDrive roadrunner;

    /**
     */
    private MecanumDriveTrain driveTrain;

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

        this.config.debugDriveTrain = false;
        this.config.debugArm = true;
        this.config.debugClaw = true;
        this.config.debugDoubleHooks = true;
        this.config.debugFlapperBars = true;
        this.config.debugIntake = true;
        this.config.debugWinch = true;

        this.config.debugAll = false;

        this.driveTrain = new MecanumDriveTrain(this.config.driveTrainConfig);
        this.arm = new Arm(this.config.armConfig);
        this.claw = new Claw(this.config.clawConfig);
        this.doubleHooks = new DoubleHooks(this.config.doubleHooksConfig);
        this.flapperBars = new FlapperBars(this.config.flapperBarsConfig);
        this.intake = new Intake(this.config.intakeConfig);
        this.winch = new Winch(this.config.winchConfig);

        this.addGp1_B_PressHandler(event -> {
            this.winch.resetEncoder();
        });
    }

    Pose2d beginPose = new Pose2d(0, 0, 0);

    /**
     *
     */
    @Override
    public void initBot(){
        super.initBot();

        roadrunner = new MecanumDrive(hardwareMap, beginPose);

        this.driveTrain.init();
        this.arm.init();
        this.claw.init();
        this.doubleHooks.init();
        this.flapperBars.init();
        this.intake.init();
        this.winch.init();

        this.addGp2_A_PressHandler(event -> {
            runAction(new SequentialAction(
                            new ParallelAction(
                                CompBot.this.intake.hServo.gotoPositionAction(0.485, 1),
                                CompBot.this.intake.vServo.gotoPositionAction(0.6356, 1),
                                CompBot.this.arm.viperSlide.gotoPositionAction(1264),
                            new WaitAction(1000),
                            CompBot.this.arm.mainBoom.gotoPositionAction(-736, 0.5))

            ));
        });

        this.addGp2_B_PressHandler(event -> {
            runAction(new SequentialAction(
                        CompBot.this.arm.mainBoom.gotoPositionAction(0, 0.5),
                        new ParallelAction(
                            CompBot.this.intake.hServo.gotoPositionAction(0.5, 1),
                            CompBot.this.intake.vServo.gotoPositionAction(0.5, 1),
                            CompBot.this.arm.viperSlide.gotoPositionAction(0))

            ));
        });
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

        this.driveTrain.run(this.config.debugDriveTrain || this.config.debugAll);
        this.arm.run(this.config.debugArm || this.config.debugAll);
        this.claw.run(this.config.debugClaw || this.config.debugAll);
        this.doubleHooks.run(this.config.debugDoubleHooks || this.config.debugAll);
        this.flapperBars.run(this.config.debugFlapperBars || this.config.debugAll);
        this.intake.run(this.config.debugIntake || this.config.debugAll);
        this.winch.run(this.config.debugWinch || this.config.debugAll);

        if (this.config.debugAll
            || this.config.debugDriveTrain
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