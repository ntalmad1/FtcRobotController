package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;
import org.firstinspires.ftc.teamcode.library.drivetrain.RoadrunnerDriveTrain;
import org.firstinspires.ftc.teamcode.library.servo.ServoPos;
import org.firstinspires.ftc.teamcode.metalheads.components.Arm;
import org.firstinspires.ftc.teamcode.metalheads.components.Claw;
import org.firstinspires.ftc.teamcode.metalheads.components.DoubleHooks;
import org.firstinspires.ftc.teamcode.metalheads.components.FlapperBars;
import org.firstinspires.ftc.teamcode.metalheads.components.Intake;
import org.firstinspires.ftc.teamcode.metalheads.components.Winch;

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
        SPECIMEN_MODE
    }

    public enum IntakePos {
        LEFT, RIGHT, STRAIGHT
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
    protected Arm arm;

    /**
     */
    protected ArmPos armPos = ArmPos.INIT;

    protected IntakePos intakePos = IntakePos.STRAIGHT;

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
    public Claw claw;

    /**
     */
    public DoubleHooks doubleHooks;

    /**
     */
    public RoadrunnerDriveTrain driveTrain;

    /**
     */
    public FlapperBars flapperBars;

    /**
     */
    public Intake intake;

    /**
     */
    public Winch winch;

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
        this.config.debugArm = true;
        this.config.debugClaw = true;
        this.config.debugDoubleHooks = false;
        this.config.debugFlapperBars = false;
        this.config.debugIntake = true;
        this.config.debugWinch = false;
        this.config.debugAll = false;

        if (this.config.useDriveTrain) {
            this.driveTrain = new RoadrunnerDriveTrain(this.config.driveTrainConfig);
        }

        if (this.config.useArm) {
            this.arm = new Arm(this.config.armConfig);
        }

        if (this.config.useClaw) {
            this.claw = new Claw(this.config.clawConfig);
        }

        if (this.config.useDoubleHooks) {
            this.doubleHooks = new DoubleHooks(this.config.doubleHooksConfig);
        }

        if (this.config.useFlapperBars) {
            this.flapperBars = new FlapperBars(this.config.flapperBarsConfig);
        }

        if (this.config.useIntake) {
            this.intake = new Intake(this.config.intakeConfig);
        }

        if (this.config.useWinch) {
            this.winch = new Winch(this.config.winchConfig);
        }

        this.actionFactory = new ActionFactory();
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

        if (this.config.useArm) {
            this.arm.init();
        }

        if (this.config.useClaw) {
            this.claw.init();
            this.claw.pincher.setPosition(config.clawConfig.pincherConfig.homePosition);
        }

        if (this.config.useDoubleHooks) {
            this.doubleHooks.init();
        }

        if (this.config.useFlapperBars) {
            this.flapperBars.init();
        }

        if (this.config.useIntake) {
            this.intake.init();
        }

        if (this.config.useWinch) {
            this.winch.init();
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

        if (this.config.useArm) {
            this.arm.run(this.config.debugArm || this.config.debugAll);
        }

        if (this.config.useClaw) {
            this.claw.run(this.config.debugClaw || this.config.debugAll);
        }

        if (this.config.useDoubleHooks) {
            this.doubleHooks.run(this.config.debugDoubleHooks || this.config.debugAll);
        }

        if (this.config.useFlapperBars) {
            this.flapperBars.run(this.config.debugFlapperBars || this.config.debugAll);
        }

        if (this.config.useIntake) {
            this.intake.run(this.config.debugIntake || this.config.debugAll);
        }

        if (this.config.useWinch) {
            this.winch.run(this.config.debugWinch || this.config.debugAll);
        }

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
    protected IntakePos getIntakePos() {
        return this.intakePos;
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
    protected ParallelAction moveArmAction(PositionConstants positions) {

        return new ParallelAction(
                intake.hServo.gotoPositionAction(positions.hServoPos),
                intake.vServo.gotoPositionAction(positions.vServoPos),
                intake.pincher.gotoPositionAction(positions.intakePincherPos),
                arm.viperSlide.gotoVoltageAction(positions.vSlideVolts),
                arm.mainBoom.gotoPositionAction(positions.mainBoomPos),
                claw.clawRotator.gotoPositionAction(positions.clawRotatorPos),
                claw.pincher.gotoPositionAction(positions.clawPincherPos)
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
     * @param pos
     */
    protected void setIntakePos(IntakePos pos) {
        this.intakePos = pos;
    }

    /**
     *
     * @param mode
     */
    protected void setMode(Mode mode) {
        this.mode = mode;
    }

    /**
     * Internal Class
     */
    public class ActionFactory {

        public Action moveArmToInitPos() {
            return new SequentialAction(
                    CompBot.this.arm.viperSlide.gotoVoltageAction(Constants.SAMPLE_PICK_READY_MIN.vSlideVolts),
                    CompBot.this.arm.mainBoom.gotoPositionAction(0, 1),
                    new ParallelAction(
                            CompBot.this.intake.hServo.gotoPositionAction(getConfig().intakeConfig.hServoConfig.homePosition, 1),
                            CompBot.this.intake.vServo.gotoPositionAction(getConfig().intakeConfig.vServoConfig.homePosition, 1),
                            CompBot.this.intake.closePincherAction(),
                            CompBot.this.claw.closeClawAction(),
                            CompBot.this.claw.clawRotator.gotoPositionAction(0, 1)
                    ),
                    new WaitAction(1000),
                    CompBot.this.arm.viperSlide.gotoVoltageAction(0.586)
            );
        }



        /**
         *
         * @return
         */
        public Action moveArmToSamplePickReadyMin() {
            return new ParallelAction(
                            intake.hServo.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.hServoPos),
                            intake.vServo.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.vServoPos),
                            arm.viperSlide.gotoVoltageAction(Constants.SAMPLE_PICK_READY_MIN.vSlideVolts),
                            new WaitAction(1000),
                            intake.openPincherAction(),
                            arm.mainBoom.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.mainBoomPos.getPos(), 0.5));
        }

        /**
         * @return
         */
        public Action moveArmToSamplePickReady() {

            // tics = -1308.8 * v^2 + 3193.07 * v + -2115.4

            double v = arm.viperSlide.getVoltage();

            int tics = (int) (-1308.8 * Math.pow(v, 2) + 3193.07 * v - 2115.4);

            // m = (vServoMax - vServoMin) / (vMax - vMin)
            // vServoPos = m\m(v-vMin) + vServoMin
            double vMin = Constants.SAMPLE_PICK_READY_MIN.vSlideVolts;
            double vMax = Constants.SAMPLE_PICK_READY_MAX.vSlideVolts;
            double vServoMin = Constants.SAMPLE_PICK_READY_MIN.vServoPos.getPos();
            double vServoMax = Constants.SAMPLE_PICK_READY_MAX.vServoPos.getPos();

            double m = (vServoMax - vServoMin) / (vMax - vMin);
            double vServoPos = m * (v - vMin) + vServoMin;

            return new ParallelAction(
                    intake.hServo.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.hServoPos),
                    intake.vServo.gotoPositionAction(vServoPos, 1),
                    //arm.viperSlide.gotoVoltageAction(Constants.SAMPLE_PICK_READY_MIN.vSlideVolts),
                    intake.openPincherAction(),
                    arm.mainBoom.gotoPositionAction(tics, 0.5),
                    claw.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.clawRotatorPos));
        }


        /**
         * @return
         */
        public Action moveArmToSamplePickLeftReady() {
            return new ParallelAction(
                    intake.hServo.gotoPositionAction(Constants.SAMPLE_PICK_LEFT_READY_MIN.hServoPos),
                    intake.vServo.gotoPositionAction(Constants.SAMPLE_PICK_LEFT_READY_MIN.hServoPos),
                    intake.openPincherAction(),
                    claw.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.clawRotatorPos));
        }

        /**
         * @return
         */
        public Action moveArmToSamplePickRightReady() {
            return new ParallelAction(
                    intake.hServo.gotoPositionAction(Constants.SAMPLE_PICK_RIGHT_READY_MIN.hServoPos),
                    intake.vServo.gotoPositionAction(Constants.SAMPLE_PICK_RIGHT_READY_MIN.hServoPos),
                    intake.openPincherAction(),
                    claw.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.clawRotatorPos));
        }


        /**
         * @return
         */
        public Action moveArmToSamplePickLeftReadyMin() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_PICK_LEFT_READY_MIN);
        }

        /**
         * @return
         */
        public Action moveArmToSamplePickRightReadyMin() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_PICK_RIGHT_READY_MIN);
        }

        /**
         * @return
         */
        public Action moveArmToSamplePickReadyMax() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_PICK_READY_MAX);
        }

        /**
         * @return
         */
        public Action moveArmToSamplePickLeftReadyMax() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_PICK_LEFT_READY_MAX);
        }

        /**
         * @return
         */
        public Action moveArmToSamplePickRightReadyMax() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_PICK_RIGHT_READY_MAX);
        }


        /**
         * @return
         */
        public Action sampleDropLow() {

            this.mainBoomReturnPos = CompBot.this.arm.mainBoom.getCurrentPosition();

            return new SequentialAction(
                    CompBot.this.intake.vServo.gotoPositionAction(
                            Constants.SAMPLE_PLACE_LOW_READY.vServoPos.getPos() + 0.1, 1),
                    CompBot.this.intake.vServo.gotoPositionAction(
                            Constants.SAMPLE_PLACE_LOW_READY.vServoPos.getPos() - 0.05, 1),
                    CompBot.this.intake.openPincherAction(),
                    CompBot.this.intake.vServo.gotoPositionAction(Constants.SAMPLE_PLACE_LOW_READY.vServoPos)
            );

        }

        /**
         * @return
         */
        public Action sampleDropHigh() {
            this.mainBoomReturnPos = CompBot.this.arm.mainBoom.getCurrentPosition();

            return new SequentialAction(
                    CompBot.this.intake.vServo.gotoPositionAction(
                            Constants.SAMPLE_PLACE_HIGH_READY.vServoPos.getPos() + 0.1, 1),
                    CompBot.this.intake.vServo.gotoPositionAction(
                            Constants.SAMPLE_PLACE_HIGH_READY.vServoPos.getPos() - 0.05, 1),
                    CompBot.this.intake.openPincherAction(),
                    CompBot.this.intake.vServo.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.vServoPos)
            );
        }

        /**
         */
        private int mainBoomReturnPos = 0;

        /**
         * @return
         */
        public Action samplePick() {

            this.mainBoomReturnPos = CompBot.this.arm.mainBoom.getCurrentPosition();

            return new SequentialAction(
                    CompBot.this.intake.closePincherAction(),
                    new WaitAction(500),
                    CompBot.this.arm.mainBoom.gotoPositionAction(Constants.SAMPLE_CARRY.mainBoomPos)
            );

        }

        /**
         * @return
         */
        public Action inverseSamplePick() {

            return new SequentialAction(
                    CompBot.this.intake.openPincherAction(),
                    new WaitAction(500),
                    CompBot.this.arm.mainBoom.gotoPositionAction(this.mainBoomReturnPos)
            );
        }

        /**
         * @return
         */
        public Action moveArmToSampleCarry() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_CARRY);
        }

        /**
         * @return
         */
        public Action moveArmToSampleExtendReady() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_EXTEND_READY);
        }

        /**
         * @return
         */
        public Action moveArmToSamplePlaceLowReady() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_PLACE_LOW_READY);
        }

        /**
         * @return
         */
        public Action moveArmToSampPlaceHighReady() {

            CompBot.this.arm.viperSlide.resetEncoder();

            return new ParallelAction(
                    intake.hServo.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.hServoPos),
                    intake.vServo.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.vServoPos),
                    intake.pincher.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.intakePincherPos),
                    arm.viperSlide.gotoPositionAction(2900),
                    arm.mainBoom.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.mainBoomPos),
                    claw.clawRotator.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.clawRotatorPos),
                    claw.pincher.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.clawPincherPos)
            );
        }

        //------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------

        /**
         * @return
         */
        public Action moveArmToSpecimenPickReady() {
            return CompBot.this.moveArmAction(Constants.SPECIMEN_PICK_READY);
        }

        /**
         * @return
         */
        public Action specimenPick() {
            return new SequentialAction(
                    new InstantAction(() -> {
                      CompBot.this.claw.pincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
                    }),
                    new WaitAction(500),
                    CompBot.this.arm.mainBoom.gotoPositionAction(
                            new MotorPos(CompBot.this.arm.mainBoom.getCurrentPosition() + 400, 1))
            );
        }

        /**
         *
         * @return
         */
        public Action inverseSpecimenPick() {
            return CompBot.this.moveArmAction(Constants.SPECIMEN_PICK_READY);
        }

        /**
         * @return
         */
        public Action moveArmToSpecimenPlaceHighReady() {
            return CompBot.this.moveArmAction(Constants.SPECIMEN_PLACE_HIGH_READY);
        }

        /**
         * @return
         */
        public Action moveArmToSpecimenPlaceHigh() {
            return CompBot.this.moveArmAction(Constants.SPECIMEN_PLACE_HIGH);
        }

        /**
         *
         * @return
         */
        public Action specimenPlaceHigh() {
            return new SequentialAction(
                    CompBot.this.arm.mainBoom.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH.mainBoomPos)
            );
        }

        /**
         * @return
         */
        public Action moveArmToSpecimenPlaceLowReady() {
            return CompBot.this.moveArmAction(Constants.SPECIMEN_PLACE_LOW_READY);
        }

        /**
         * @return
         */
        public Action moveArmToSpecimenPlaceLow() {
            return CompBot.this.moveArmAction(Constants.SPECIMEN_PLACE_LOW);
        }

        /**
         * @return
         */
        public Action specimenPlaceLow() {
            return new SequentialAction(
                    CompBot.this.arm.mainBoom.gotoPositionAction(Constants.SPECIMEN_PLACE_LOW.mainBoomPos)
            );
        }

        /**
         * Hidden constructor to make class static
         */
        protected ActionFactory() {
        }
    }

    /**
     * Internal Class
     */
    public static abstract class PositionConstants {

        public ServoPos hServoPos;

        public ServoPos vServoPos;

        public ServoPos intakePincherPos;

        public Double vSlideVolts;

        public MotorPos mainBoomPos;

        public ServoPos clawRotatorPos;

        public ServoPos clawPincherPos;

        public PositionConstants () {
            this.setValues();
        }

        /**
         *
         */
        public abstract void setValues();
    }
}