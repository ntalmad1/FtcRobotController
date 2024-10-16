package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;
import org.firstinspires.ftc.teamcode.library.drivetrain.RoadrunnerDriveTrain;
import org.firstinspires.ftc.teamcode.library.servo.ServoPos;
import org.firstinspires.ftc.teamcode.library.utility.Control;
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
    private Claw claw;

    /**
     */
    private DoubleHooks doubleHooks;

    /**
     */
    private RoadrunnerDriveTrain driveTrain;

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

        this.configureGamePad1();
        this.configureGamePad2();
    }

    /**
     *
     */
    private void configureGamePad1() {

        // panic button / kill switch
        this.addGp1_Back_PressHandler(event -> {
            this.terminateOpModeNow();
        });

        // arm
        if (this.getConfig().useArm) {

            // clear and re-init
            this.addGp1_Start_PressHandler(event -> {
                this.setMode(Mode.NONE);
                this.setArmPos(ArmPos.INIT);
                this.runAction(this.actionFactory.moveArmToInitPos());
            });
        }

        // double hooks
        if (this.getConfig().useDoubleHooks) {
            this.doubleHooks.linearActuator.addControl(Control.Gp1_Dpad_UpDown);
            this.doubleHooks.doubleServos.addControl(Control.Gp1_Dpad_LeftRight);
        }

        // flapper bars
        if (this.getConfig().useFlapperBars) {
            this.addGp1_Left_Bumper_DownHandler(event -> {
                flapperBars.move(-1);
            });

            this.addGp1_Left_Bumper_UpHandler(event -> {
                flapperBars.move(0);
            });

            this.addGp1_Right_Bumper_DownHandler(event -> {
                flapperBars.move(1);
            });

            this.addGp1_Right_Bumper_UpHandler(event -> {
                flapperBars.move(0);
            });
        }

        // winch
        if (this.getConfig().useWinch) {
            this.addGp1_RightTrigger_DownHandler(event -> {
                winch.move(-1);
            });

            this.addGp1_RightTrigger_UpHandler(event -> {
                winch.move(0);
            });

            this.addGp1_LeftTrigger_DownHandler(event -> {
                winch.move(1);
            });

            this.addGp1_LeftTrigger_UpHandler(event -> {
                winch.move(0);
            });
        }

    }

    /**
     *
     */
    private void configureGamePad2() {

        // Panic Button / Kill Switch
        this.addGp2_Back_PressHandler(event -> {
            this.terminateOpModeNow();
        });

        // arm
        if (this.getConfig().useArm) {

            // clear and re-init
            this.addGp2_Start_PressHandler(event -> {
                this.setMode(Mode.NONE);
                this.setArmPos(ArmPos.INIT);
                this.runAction(this.actionFactory.moveArmToInitPos());
            });

            // manually move main boom
            this.arm.mainBoom.addControl(Control.Gp2_LeftStickY);

            // manually move viper slide
            this.arm.viperSlide.addControl(Control.Gp2_RightStickX);

            // dpad up
            this.addGp2_Dpad_Up_DownHandler(event -> {
                if (Mode.SAMPLE_MODE.equals(this.getMode())) {
                    CompBot.this.intake.vServo.move(1);
                }
                else if (Mode.SPECIMEN_MODE.equals(this.getMode())) {
                    CompBot.this.claw.clawRotator.move(-1);
                }
            });

            // dpad down
            this.addGp2_Dpad_Down_DownHandler(event -> {
                if (Mode.SAMPLE_MODE.equals(this.getMode())) {
                    CompBot.this.intake.vServo.move(-1);
                }
                else if (Mode.SPECIMEN_MODE.equals(this.getMode())) {
                    CompBot.this.claw.clawRotator.move(1);
                }
            });

            // dpad left
            this.addGp2_Dpad_Left_DownHandler(event -> {
                if (Mode.SAMPLE_MODE.equals(this.getMode())) {
                    CompBot.this.intake.hServo.move(-1);
                }
                else if (Mode.SPECIMEN_MODE.equals(this.getMode())) {
                    CompBot.this.claw.clawRotator.move(-1);
                }
            });

            // dpad right
            this.addGp2_Dpad_Right_DownHandler(event -> {
                if (Mode.SAMPLE_MODE.equals(this.getMode())) {
                    CompBot.this.intake.hServo.move(1);
                }
                else if (Mode.SPECIMEN_MODE.equals(this.getMode())) {
                    CompBot.this.claw.clawRotator.move(1);
                }
            });
        }

        // claw
        if (this.getConfig().useClaw) {

            this.claw.addGp2_Right_Trigger_Handler(event -> {
                if (Mode.SPECIMEN_MODE.equals(this.getMode())) {
                    this.claw.pincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
                }
            });

            this.claw.addGp2_Left_Trigger_Handler(event -> {
                if (Mode.SPECIMEN_MODE.equals(this.getMode())) {
                    this.claw.pincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS);
                }
            });
        }


        // intake
        if (this.getConfig().useIntake) {

            this.intake.addGp2_Right_Trigger_Handler(event -> {
                if (Mode.SAMPLE_MODE.equals(this.getMode())) {
                    this.intake.pincher.setPosition(Constants.INTAKE_PINCHER_CLOSE_POS);
                }
            });

            this.intake.addGp2_Left_Trigger_Handler(event -> {
                if (Mode.SAMPLE_MODE.equals(this.getMode())) {
                    this.intake.pincher.setPosition(Constants.INTAKE_PINCHER_OPEN_POS);
                }
            });
        }

        // A button
        this.addGp2_A_PressHandler(event -> {
            if (Mode.NONE.equals(this.getMode())) {
                if (this.arm.viperSlide.getVoltage() <= Constants.SAMPLE_PICK_READY_MIN.vSlideVolts) {
                    this.runAction(this.actionFactory.moveArmToSamplePickReadyMin());
                }
                else if (this.arm.viperSlide.getVoltage() >= Constants.SAMPLE_PICK_READY_MAX.vSlideVolts) {
                    this.runAction(this.actionFactory.moveArmToSamplePickReadyMax());
                }
                else {
                    this.runAction(this.actionFactory.moveArmToSamplePickReady());
                }

                this.setMode(Mode.SAMPLE_MODE);
                this.setArmPos(ArmPos.SAMPLE_PICK_READY);
            }
            else if (Mode.SAMPLE_MODE.equals(this.getMode()) && ArmPos.SAMPLE_PICK_READY.equals(this.getArmPos())) {
                this.runAction(this.actionFactory.samplePick());
                this.setArmPos(ArmPos.SAMPLE_PICK);
            }
            else if (Mode.SAMPLE_MODE.equals(this.getMode()) && ArmPos.SAMPLE_PICK.equals(this.getArmPos())) {
                this.runAction(this.actionFactory.inverseSamplePick());
                this.setArmPos(ArmPos.SAMPLE_PICK_READY);
            }
            else if (Mode.SAMPLE_MODE.equals(this.getMode()) && (ArmPos.SAMPLE_DROP_LOW_READY.equals(this.getArmPos()) || ArmPos.SAMPLE_DROP_HIGH_READY.equals(this.getArmPos()))) {
                this.setMode(Mode.NONE);
                this.setArmPos(ArmPos.INIT);
                this.runAction(this.actionFactory.moveArmToInitPos());
            }
            else if (Mode.SPECIMEN_MODE.equals(this.getMode()) && (ArmPos.SPECIMEN_PICK_READY.equals(this.getArmPos()))) {
                this.runAction(this.actionFactory.specimenPick());
                this.setArmPos(ArmPos.SPECIMEN_PICK);
            }
            else if (Mode.SPECIMEN_MODE.equals(this.getMode()) && (ArmPos.SPECIMEN_PICK.equals(this.getArmPos()))) {
                this.runAction(this.actionFactory.inverseSpecimenPick());
                this.setArmPos(ArmPos.SPECIMEN_PICK);
            }
            else if (Mode.SPECIMEN_MODE.equals(this.getMode()) && (
                    ArmPos.SPECIMEN_PLACE_LOW_READY.equals(this.getArmPos())
                 || ArmPos.SPECIMEN_PLACE_LOW.equals(this.getArmPos())
                 || ArmPos.SPECIMEN_PLACE_HIGH_READY.equals(this.getArmPos())
                 || ArmPos.SPECIMEN_PLACE_HIGH.equals(this.getArmPos()))) {
                this.setMode(Mode.NONE);
                this.setArmPos(ArmPos.INIT);
                this.runAction(this.actionFactory.moveArmToInitPos());
            }
        });

        // left bumper
        this.addGp2_Left_Bumper_PressHandler(event -> {
            if (Mode.SAMPLE_MODE.equals(this.getMode())) {
                if (this.arm.viperSlide.getVoltage() <= Constants.SAMPLE_PICK_LEFT_READY_MIN.vSlideVolts) {
                    this.runAction(this.actionFactory.moveArmToSamplePickLeftReadyMin());
                }
                else if (this.arm.viperSlide.getVoltage() >= Constants.SAMPLE_PICK_LEFT_READY_MAX.vSlideVolts) {
                    this.runAction(this.actionFactory.moveArmToSamplePickLeftReadyMax());
                }
                else {
                    this.runAction(this.actionFactory.moveArmToSamplePickLeftReady());
                }
            }
        });

        // right bumper
        this.addGp2_Right_Bumper_PressHandler(event -> {
            if (this.arm.viperSlide.getVoltage() <= Constants.SAMPLE_PICK_RIGHT_READY_MIN.vSlideVolts) {
                this.runAction(this.actionFactory.moveArmToSamplePickRightReadyMin());
            }
            else if (this.arm.viperSlide.getVoltage() >= Constants.SAMPLE_PICK_RIGHT_READY_MAX.vSlideVolts) {
                this.runAction(this.actionFactory.moveArmToSamplePickRightReadyMax());
            }
            else {
                this.runAction(this.actionFactory.moveArmToSamplePickRightReady());
            }
        });

        // X button
        this.addGp2_X_PressHandler(event -> {
            if (Mode.NONE.equals(this.getMode())) {
                this.runAction(this.actionFactory.moveArmToSpecimenPickReady());
                this.setMode(Mode.SPECIMEN_MODE);
                this.setArmPos(ArmPos.SPECIMEN_PICK_READY);
            }
            else if (Mode.SAMPLE_MODE.equals(this.getMode()) && ArmPos.SAMPLE_CARRY.equals(this.getArmPos())) {
                this.runAction(new SequentialAction(
                        actionFactory.moveArmToSampleExtendReady(),
                        actionFactory.moveArmToSampleBasketLowReady()));
                this.setArmPos(ArmPos.SAMPLE_DROP_LOW_READY);
            }
            else if (Mode.SAMPLE_MODE.equals(this.getMode()) && (
                    ArmPos.SAMPLE_EXTEND_READY.equals(this.getArmPos()))
                 || ArmPos.SAMPLE_DROP_LOW.equals(this.getArmPos())) {
                this.runAction(actionFactory.moveArmToSampleBasketLowReady());
                this.setArmPos(ArmPos.SAMPLE_DROP_LOW_READY);
            }
            else if (Mode.SAMPLE_MODE.equals(this.getMode()) && ArmPos.SAMPLE_DROP_LOW_READY.equals(this.getArmPos())) {
                this.runAction(actionFactory.sampleDropLow());
                this.setArmPos(ArmPos.SAMPLE_DROP_LOW_READY);
            }
            else if (Mode.SPECIMEN_MODE.equals(this.getMode()) && !ArmPos.SPECIMEN_PLACE_LOW_READY.equals(this.getArmPos())) {
                this.runAction(actionFactory.moveArmToSpecimenPlaceLowReady());
                this.setArmPos(ArmPos.SPECIMEN_PLACE_LOW_READY);
            }
            else if (Mode.SPECIMEN_MODE.equals(this.getMode()) && ArmPos.SPECIMEN_PLACE_LOW_READY.equals(this.getArmPos())) {
                this.runAction(actionFactory.moveArmToSpecimenPlaceLowReady());
                this.setArmPos(ArmPos.SPECIMEN_PLACE_LOW);
            }
        });

        // Y button
        this.addGp2_Y_PressHandler(event -> {
            if (Mode.NONE.equals(this.getMode())) {
                // do nothing
            }
            else if (Mode.SAMPLE_MODE.equals(this.getMode()) && ArmPos.SAMPLE_CARRY.equals(this.getArmPos())) {
                this.runAction(new SequentialAction(
                        actionFactory.moveArmToSampleExtendReady(),
                        actionFactory.moveArmToSampleBasketHighReady()));
                this.setArmPos(ArmPos.SAMPLE_DROP_HIGH_READY);
            }
            else if (Mode.SAMPLE_MODE.equals(this.getMode()) && (
                    ArmPos.SAMPLE_EXTEND_READY.equals(this.getArmPos()))
                    || ArmPos.SAMPLE_DROP_HIGH.equals(this.getArmPos())) {
                this.runAction(actionFactory.moveArmToSampleBasketHighReady());
                this.setArmPos(ArmPos.SAMPLE_DROP_HIGH_READY);
            }
            else if (Mode.SAMPLE_MODE.equals(this.getMode()) && ArmPos.SAMPLE_DROP_HIGH_READY.equals(this.getArmPos())) {
                this.runAction(actionFactory.sampleDropHigh());
                this.setArmPos(ArmPos.SAMPLE_DROP_HIGH_READY);
            }
            else if (Mode.SPECIMEN_MODE.equals(this.getMode()) && !ArmPos.SPECIMEN_PLACE_HIGH_READY.equals(this.getArmPos())) {
                this.runAction(actionFactory.moveArmToSpecimenPlaceHighReady());
                this.setArmPos(ArmPos.SPECIMEN_PLACE_HIGH_READY);
            }
            else if (Mode.SPECIMEN_MODE.equals(this.getMode()) && ArmPos.SPECIMEN_PLACE_HIGH_READY.equals(this.getArmPos())) {
                this.runAction(actionFactory.moveArmToSpecimenPlaceHighReady());
                this.setArmPos(ArmPos.SPECIMEN_PLACE_HIGH);
            }
        });

        // B button
        this.addGp2_B_PressHandler(event -> {
            if (Mode.SAMPLE_MODE.equals(this.getMode()) && (
                    ArmPos.SAMPLE_PICK_READY.equals(this.getArmPos())
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

            double volts = arm.viperSlide.getVoltage();

            double tpd = 41.821; // tics per degree
            double vpi = 0; // TODO

            double h = 20; // height from ground to point of rotation
            double v = 0; // TODO viper slide length in inches

            double d = Math.acos(h / v) * (180 / Math.PI) - 28.23;

            int tics = 0; // TODO

            // mm = (vServoMax - vServoMin) / (vMax - vMin)
            // vServoPos = m\m(v-vMin) + vServoMin
            double vMin = Constants.SAMPLE_PICK_READY_MIN.vSlideVolts;
            double vMax = Constants.SAMPLE_PICK_READY_MAX.vSlideVolts;
            double vServoMin = Constants.SAMPLE_PICK_READY_MIN.vServoPos.getPos();
            double vServoMax = Constants.SAMPLE_PICK_READY_MAX.vServoPos.getPos();

            double m = (vServoMax - vServoMin) / (vMax - vMin);
            double vServoPos = m * (volts-vMin) + vServoMin;

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

            double volts = arm.viperSlide.getVoltage();

            // TODO calcualate tics

            int tics = 0; // TODO

            // mm = (vServoMax - vServoMin) / (vMax - vMin)
            // vServoPos = m\m(v-vMin) + vServoMin
            double vMin = Constants.SAMPLE_PICK_LEFT_READY_MIN.vSlideVolts;
            double vMax = Constants.SAMPLE_PICK_LEFT_READY_MAX.vSlideVolts;
            double vServoMin = Constants.SAMPLE_PICK_LEFT_READY_MIN.vServoPos.getPos();
            double vServoMax = Constants.SAMPLE_PICK_LEFT_READY_MAX.vServoPos.getPos();


            double m = (vServoMax - vServoMin) / (vMax - vMin);
            double vServoPos = m * (volts-vMin) + vServoMin;

            return new ParallelAction(
                    intake.hServo.gotoPositionAction(Constants.SAMPLE_PICK_LEFT_READY_MIN.hServoPos),
                    intake.vServo.gotoPositionAction(vServoPos, 1),
                    //arm.viperSlide.gotoVoltageAction(Constants.SAMPLE_PICK_READY_MIN.vSlideVolts),
                    intake.openPincherAction(),
                    arm.mainBoom.gotoPositionAction(tics, 0.5),
                    claw.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.clawRotatorPos));
        }

        /**
         * @return
         */
        public Action moveArmToSamplePickRightReady() {

            double volts = arm.viperSlide.getVoltage();

            // TODO calcualate tics

            int tics = 0; // TODO

            // mm = (vServoMax - vServoMin) / (vMax - vMin)
            // vServoPos = m\m(v-vMin) + vServoMin
            double vMin = Constants.SAMPLE_PICK_RIGHT_READY_MIN.vSlideVolts;
            double vMax = Constants.SAMPLE_PICK_RIGHT_READY_MAX.vSlideVolts;
            double vServoMin = Constants.SAMPLE_PICK_RIGHT_READY_MIN.vServoPos.getPos();
            double vServoMax = Constants.SAMPLE_PICK_RIGHT_READY_MAX.vServoPos.getPos();


            double m = (vServoMax - vServoMin) / (vMax - vMin);
            double vServoPos = m * (volts-vMin) + vServoMin;

            return new ParallelAction(
                    intake.hServo.gotoPositionAction(Constants.SAMPLE_PICK_RIGHT_READY_MIN.hServoPos),
                    intake.vServo.gotoPositionAction(vServoPos, 1),
                    //arm.viperSlide.gotoVoltageAction(Constants.SAMPLE_PICK_READY_MIN.vSlideVolts),
                    intake.openPincherAction(),
                    arm.mainBoom.gotoPositionAction(tics, 0.5),
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
            return null;
            // TODO
        }

        /**
         * @return
         */
        public Action sampleDropHigh() {
            return null;
            // TODO
        }

        /**
         * @return
         */
        public Action samplePick() {
            return null;
            // TODO
        }

        /**
         * @return
         */
        public Action inverseSamplePick() {
            return null;
            // TODO
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
        public Action moveArmToSampleBasketLowReady() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_BASKET_LOW_READY);
        }

        /**
         * @return
         */
        public Action moveArmToSampleBasketHighReady() {
            return CompBot.this.moveArmAction(Constants.SAMPLE_BASKET_HIGH_READY);
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
            return CompBot.this.moveArmAction(Constants.SPECIMEN_PICK);
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