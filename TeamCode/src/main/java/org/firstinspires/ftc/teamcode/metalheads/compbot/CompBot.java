package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.drivetrain.RoadrunnerDriveTrain;
import org.firstinspires.ftc.teamcode.metalheads.components.BigArm;
import org.firstinspires.ftc.teamcode.metalheads.components.LittleArm;

/**
 *
 */
public abstract class CompBot extends IsaacBot {

    /**
     *
     */
    public enum Mode {
        NONE,
        SAMPLE_MODE,
        SPECIMEN_MODE,
        HANG_READY,
        ASCENDING
    }

    /**
     *
     */
    public enum ArmPos {
        INIT,
        SAMPLE_PICK_READY,
        SAMPLE_PICK,
        SAMPLE_CARRY,
        SAMPLE_EXTEND_READY,
        SAMPLE_DROP_LOW_READY,
        SAMPLE_DROP_LOW,
        SAMPLE_DROP_HIGH_READY,
        SAMPLE_DROP_HIGH,
        SPECIMEN_PICK_READY,
        SPECIMEN_PICK,
        SPECIMEN_PLACE_LOW_READY,
        SPECIMEN_PLACE_LOW,
        SPECIMEN_PLACE_HIGH_READY,
        SPECIMEN_PLACE_HIGH
    }

    /**
     */
    protected BigArm bigArm;

    /**
     */
    protected LittleArm littleArm;

    /**
     */
    protected ArmPos armPos = ArmPos.INIT;

    /**
     */
    protected Mode mode = Mode.NONE;

    /**
     */
    protected CompBotConfig config;

    /**
     */
    private ActionFactory actionFactory;

    /**
     */
    public RoadrunnerDriveTrain driveTrain;

    /**
     */
    protected Pose2d initialPose;

    /**
     * Constructor
     *
     */
    public CompBot(){
        super();
    }


    /**
     * Constructor
     *
     * @param compBotConfig
     */
    public CompBot(CompBotConfig compBotConfig) {
        super();

        this.config = compBotConfig;
        this.configureBot();
    }

    /**
     *
     */
    protected void configureBot() {
        if (this.getConfig() == null) {
            this.config = new CompBotConfig(this);
        }

        this.config.debugDriveTrain = true;
        this.config.debugBigArm = true;
        this.config.debugLittleArm = true;
        this.config.debugAll = false;

        if (this.config.useDriveTrain) {
            this.driveTrain = new RoadrunnerDriveTrain(this.config.driveTrainConfig);
        }

        if (this.config.useBigArm) {
            this.bigArm = new BigArm(this.config.bigArmConfig);
        }

        if (this.config.useLittleArm) {
            this.littleArm = new LittleArm(this.config.littleArmConfig);
        }

        this.actionFactory = new ActionFactory(this);
    }

    /**
     *
     */
    @Override
    public void initBot(){
        super.initBot();

        if (this.config.useDriveTrain) {
            this.driveTrain.init(this.initialPose);
        }

        if (this.config.useBigArm) {
            this.bigArm.init();
        }

        if (this.config.useLittleArm) {
            this.littleArm.init();
        }

        ControlsConfigurator controlsConfigurator = new ControlsConfigurator(this);
        controlsConfigurator.configureGamePad1();
        controlsConfigurator.configureGamePad2();
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

        if (this.config.useDriveTrain) {
            this.driveTrain.run(this.config.debugDriveTrain || this.config.debugAll);
        }

        if (this.config.useBigArm) {
            this.bigArm.run(this.config.debugBigArm || this.config.debugAll);
        }

        if (this.config.useLittleArm) {
            this.littleArm.run(this.config.debugLittleArm || this.config.debugAll);
        }

        if (this.config.debugAll
            || this.config.debugDriveTrain
            || this.config.debugLittleArm
            || this.config.debugBigArm) {
            telemetry.update();
        }
    }

    /**
     *
     */
    @Override
    public void onStop() {

    }

    /**
     *
     * @return
     */
    public CompBotConfig getConfig() {
        return this.config == null ? null : this.config;
    }

    /**
     *
     * @return
     */
    public ActionFactory getActionFactory() {
        return this.actionFactory;
    }

    /**
     *
     * @param pos
     */
    public void setInitialPose(Pose2d pos) {
        this.initialPose = pos;
    }

    /**
     *
     * @param config
     */
    public void setConfig(CompBotConfig config) {
        this.config = config;
    }

    /**
     *
     * @return
     */
    protected ArmPos getArmPos() {
        return this.armPos;
    }

    /**
     *
     * @return
     */
    protected Mode getMode() {
        return this.mode;
    }

    /**
     *
     * @param positions
     * @return
     */
    protected ParallelAction moveArmAction(PositionsStruct positions) {

        return new ParallelAction(
                /*
                intake.hServo.gotoPositionAction(positions.hServoPos),
                intake.vServo.gotoPositionAction(positions.vServoPos),
                intake.pincher.gotoPositionAction(positions.intakePincherPos),
                arm.viperSlide.gotoVoltageAction(positions.vSlideVolts),
                arm.mainBoom.gotoPositionAction(positions.mainBoomPos),
                claw.clawRotator.gotoPositionAction(positions.clawRotatorPos),
                claw.pincher.gotoPositionAction(positions.clawPincherPos)
                */
        );

    }

    /**
     *
     * @param pos
     */
    protected void setArmPos(ArmPos pos) {
        this.armPos = pos;
    }

    /**
     *
     * @param mode
     */
    protected void setMode(Mode mode) {
        this.mode = mode;
    }
}