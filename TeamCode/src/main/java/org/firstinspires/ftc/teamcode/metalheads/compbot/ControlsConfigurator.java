package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.library.utility.Control;

/**
 *
 */
public class ControlsConfigurator {

    /**
     */
    private CompBot compBot;

    /**
     * Constructor
     *
     * @param compBot
     */
    public ControlsConfigurator(CompBot compBot) {
        this.compBot = compBot;
    }

    /**
     *
     */
    public void configureGamePad1() {

        // panic button / kill switch
        this.compBot.addGp1_Back_PressHandler(event -> {
            this.compBot.terminateOpModeNow();
        });

        // arm
        if (this.compBot.getConfig().useArm) {

            // clear and re-init
            this.compBot.addGp1_Start_PressHandler(event -> {
                this.compBot.setMode(CompBot.Mode.NONE);
                this.compBot.setArmPos(CompBot.ArmPos.INIT);
                this.compBot.runAction(this.compBot.getActionFactory().moveArmToInitPos());
            });
        }

        // double hooks
        if (this.compBot.getConfig().useDoubleHooks) {
            this.compBot.doubleHooks.linearActuator.addControl(Control.Gp1_Dpad_UpDown);
            this.compBot.doubleHooks.doubleServos.addControl(Control.Gp1_Dpad_LeftRight);
        }

        // flapper bars
        if (this.compBot.getConfig().useFlapperBars) {
            this.compBot.addGp1_Left_Bumper_DownHandler(event -> {
                compBot.flapperBars.move(-1);
            });

            this.compBot.addGp1_Left_Bumper_UpHandler(event -> {
                compBot.flapperBars.move(0);
            });

            this.compBot.addGp1_Right_Bumper_DownHandler(event -> {
                compBot.flapperBars.move(1);
            });

            this.compBot.addGp1_Right_Bumper_UpHandler(event -> {
                compBot.flapperBars.move(0);
            });
        }

        // compBot.winch
        if (this.compBot.getConfig().useWinch) {
            this.compBot.addGp1_RightTrigger_DownHandler(event -> {
                compBot.winch.move(-1);
            });

            this.compBot.addGp1_RightTrigger_UpHandler(event -> {
                compBot.winch.move(0);
            });

            this.compBot.addGp1_LeftTrigger_DownHandler(event -> {
                compBot.winch.move(1);
            });

            this.compBot.addGp1_LeftTrigger_UpHandler(event -> {
                compBot.winch.move(0);
            });
        }

    }

    /**
     *
     */
    public void configureGamePad2() {

        // Panic Button / Kill Switch
        this.compBot.addGp2_Back_PressHandler(event -> {
            this.compBot.terminateOpModeNow();
        });

        // arm
        if (this.compBot.getConfig().useArm) {
            // clear and re-init
            this.compBot.addGp2_Start_PressHandler(event -> {
                this.compBot.setMode(CompBot.Mode.NONE);
                this.compBot.setArmPos(CompBot.ArmPos.INIT);
                this.compBot.runAction(this.compBot.getActionFactory().moveArmToInitPos());
            });

            // manually move main boom
            this.compBot.arm.mainBoom.addControl(Control.Gp2_LeftStickY);

            // manually move viper slide
            this.compBot.arm.viperSlide.addGp2_RightStick_X_Handler(event -> {

                this.compBot.arm.viperSlide.move(event.getPosition());

                if (CompBot.ArmPos.SAMPLE_PICK_READY.equals(this.compBot.getArmPos())) {
                    // tics = -1308.8 * v^2 + 3193.07 * v + -2115.4

                    double v = compBot.arm.viperSlide.getVoltage();
                    int tics = (int) (-1308.8 * Math.pow(v, 2) + 3193.07 * v - 2115.4);

                    double vMin = Constants.SAMPLE_PICK_READY_MIN.vSlideVolts;
                    double vMax = Constants.SAMPLE_PICK_READY_MAX.vSlideVolts;
                    double vServoMin = Constants.SAMPLE_PICK_READY_MIN.vServoPos.getPos();
                    double vServoMax = Constants.SAMPLE_PICK_READY_MAX.vServoPos.getPos();

                    double m = (vServoMax - vServoMin) / (vMax - vMin);
                    double vServoPos = m * (v - vMin) + vServoMin;

                    compBot.intake.vServo.setServoPosition(vServoPos);
                    compBot.arm.mainBoom.moveToPosition(1, tics);
                }
            });

            this.gp2_Dpad();
        }

        // triggers
        this.gp2_Triggers();

        // left bumper
        this.gp2_Left_Bumper();

        // right bumper
        this.gp2_Right_Bumper();

        // A button
        this.gp2_A_Button();

        // B button
        this.gp2_B_Button();

        // X button
        this.gp2_X_BUtton();

        // Y button
        this.gp2_Y_Button();
    }

    /**
     *
     */
    public void gp2_Dpad() {
        // dpad up
        this.compBot.addGp2_Dpad_Up_DownHandler(event -> {
            if (CompBot.Mode.SPECIMEN_MODE.equals(this.compBot.getMode())) {
                this.compBot.claw.clawRotator.move(-1);
            }
            //if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode())) {
            else {
                this.compBot.intake.vServo.move(1);
            }
        });

        // dpad down
        this.compBot.addGp2_Dpad_Down_DownHandler(event -> {
            if (CompBot.Mode.SPECIMEN_MODE.equals(this.compBot.getMode())) {
                this.compBot.claw.clawRotator.move(1);
            }
            //else if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode())) {
            else {
                this.compBot.intake.vServo.move(-1);
            }
        });

        // dpad left
        this.compBot.addGp2_Dpad_Left_DownHandler(event -> {
            if (CompBot.Mode.SPECIMEN_MODE.equals(this.compBot.getMode())) {
                this.compBot.claw.clawRotator.move(-1);
            }
            //else if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode())) {
            else {
                this.compBot.intake.hServo.move(-1);
            }
        });

        //dpad right
        this.compBot.addGp2_Dpad_Right_DownHandler(event -> {
            //else if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode())) {
            if (CompBot.Mode.SPECIMEN_MODE.equals(this.compBot.getMode())) {
                this.compBot.claw.clawRotator.move(1);
            }
            else {
                this.compBot.intake.hServo.move(1);
            }
        });
    }

    /**
     *
     */
    public void gp2_Left_Bumper() {
        this.compBot.addGp2_Left_Bumper_PressHandler(event -> {
            if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode())) {
                if (CompBot.IntakePos.STRAIGHT.equals(this.compBot.getIntakePos())) {
                    if (this.compBot.arm.viperSlide.getVoltage() <= Constants.SAMPLE_PICK_LEFT_READY_MIN.vSlideVolts) {
                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickLeftReadyMin());
                    } else if (this.compBot.arm.viperSlide.getVoltage() >= Constants.SAMPLE_PICK_LEFT_READY_MAX.vSlideVolts) {
                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickLeftReadyMax());
                    } else {
                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickLeftReady());
                    }
                    this.compBot.setIntakePos(CompBot.IntakePos.LEFT);
                }
                else if (CompBot.IntakePos.RIGHT.equals(this.compBot.getIntakePos())) {
                    if (this.compBot.arm.viperSlide.getVoltage() <= Constants.SAMPLE_PICK_READY_MIN.vSlideVolts) {
                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickReadyMin());
                    } else if (this.compBot.arm.viperSlide.getVoltage() >= Constants.SAMPLE_PICK_READY_MAX.vSlideVolts) {
                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickReadyMax());
                    } else {
                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickReady());
                    }
                    this.compBot.setIntakePos(CompBot.IntakePos.STRAIGHT);
                }
            }
            else {
                this.compBot.claw.clawRotator.move(-1);
            }
        });
    }

    /**
     *
     */
    public void gp2_Right_Bumper() {
        this.compBot.addGp2_Right_Bumper_PressHandler(event -> {
            if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode())) {
                if (CompBot.IntakePos.STRAIGHT.equals(this.compBot.getIntakePos())) {
                    if (this.compBot.arm.viperSlide.getVoltage() <= Constants.SAMPLE_PICK_RIGHT_READY_MIN.vSlideVolts) {
                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickRightReadyMin());
                    } else if (this.compBot.arm.viperSlide.getVoltage() >= Constants.SAMPLE_PICK_RIGHT_READY_MAX.vSlideVolts) {
                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickRightReadyMax());
                    } else {
                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickRightReady());
                    }
                    this.compBot.setIntakePos(CompBot.IntakePos.RIGHT);
                }
                else if (CompBot.IntakePos.LEFT.equals(this.compBot.getIntakePos())) {
                    if (this.compBot.arm.viperSlide.getVoltage() <= Constants.SAMPLE_PICK_READY_MIN.vSlideVolts) {
                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickReadyMin());
                    } else if (this.compBot.arm.viperSlide.getVoltage() >= Constants.SAMPLE_PICK_READY_MAX.vSlideVolts) {
                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickReadyMax());
                    } else {
                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickReady());
                    }
                    this.compBot.setIntakePos(CompBot.IntakePos.STRAIGHT);
                }
            }
            else {
                this.compBot.claw.clawRotator.move(1);
            }
        });
    }

    /**
     *
     */
    public void gp2_A_Button() {
        this.compBot.addGp2_A_PressHandler(event -> {
            if (CompBot.Mode.NONE.equals(this.compBot.getMode())) {
                if (this.compBot.arm.viperSlide.getVoltage() <= Constants.SAMPLE_PICK_READY_MIN.vSlideVolts) {
                    this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickReadyMin());
                }
                else if (this.compBot.arm.viperSlide.getVoltage() >= Constants.SAMPLE_PICK_READY_MAX.vSlideVolts) {
                    this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickReadyMax());
                }
                else {
                    this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickReady());
                }

                this.compBot.setMode(CompBot.Mode.SAMPLE_MODE);
                this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY);
            }
            else if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode()) && CompBot.ArmPos.SAMPLE_PICK_READY.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(this.compBot.getActionFactory().samplePick());
                this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK);
            }
            else if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode()) && CompBot.ArmPos.SAMPLE_PICK.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(this.compBot.getActionFactory().inverseSamplePick());
                this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY);
            }
            else if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode()) && (CompBot.ArmPos.SAMPLE_DROP_LOW_READY.equals(this.compBot.getArmPos()) || CompBot.ArmPos.SAMPLE_DROP_HIGH_READY.equals(this.compBot.getArmPos()))) {
                this.compBot.setMode(CompBot.Mode.NONE);
                this.compBot.setArmPos(CompBot.ArmPos.INIT);
                this.compBot.runAction(this.compBot.getActionFactory().moveArmToInitPos());
            }
            else if (CompBot.Mode.SPECIMEN_MODE.equals(this.compBot.getMode()) && (CompBot.ArmPos.SPECIMEN_PICK_READY.equals(this.compBot.getArmPos()))) {
                this.compBot.runAction(this.compBot.getActionFactory().specimenPick());
                this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK);
            }
            else if (CompBot.Mode.SPECIMEN_MODE.equals(this.compBot.getMode()) && (CompBot.ArmPos.SPECIMEN_PICK.equals(this.compBot.getArmPos()))) {
                this.compBot.runAction(this.compBot.getActionFactory().inverseSpecimenPick());
                this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK);
            }
            else if (CompBot.Mode.SPECIMEN_MODE.equals(this.compBot.getMode()) && (
                    CompBot.ArmPos.SPECIMEN_PLACE_LOW_READY.equals(this.compBot.getArmPos())
                            || CompBot.ArmPos.SPECIMEN_PLACE_LOW.equals(this.compBot.getArmPos())
                            || CompBot.ArmPos.SPECIMEN_PLACE_HIGH_READY.equals(this.compBot.getArmPos())
                            || CompBot.ArmPos.SPECIMEN_PLACE_HIGH.equals(this.compBot.getArmPos()))) {
                this.compBot.runAction(this.compBot.getActionFactory().moveArmToSpecimenPickReady());
                this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY);
            }
        });
    }

    /**
     *
     */
    public void gp2_B_Button() {
        this.compBot.addGp2_B_PressHandler(event -> {
            if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode()) && (
                    CompBot.ArmPos.SAMPLE_PICK_READY.equals(this.compBot.getArmPos())
                            || CompBot.ArmPos.SAMPLE_PICK.equals(this.compBot.getArmPos()))) {
                this.compBot.runAction(compBot.getActionFactory().moveArmToSampleCarry());
                this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_CARRY);
            }
            else if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode()) && CompBot.ArmPos.SAMPLE_CARRY.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(compBot.getActionFactory().moveArmToSampleExtendReady());
                this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_EXTEND_READY);
            }
            else if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode()) && (CompBot.ArmPos.SAMPLE_DROP_LOW_READY.equals(this.compBot.getArmPos()) || CompBot.ArmPos.SAMPLE_DROP_HIGH_READY.equals(this.compBot.getArmPos()))) {
                this.compBot.setMode(CompBot.Mode.NONE);
                this.compBot.setArmPos(CompBot.ArmPos.INIT);
                this.compBot.runAction(this.compBot.getActionFactory().moveArmToInitPos());
            }
            else if (CompBot.Mode.SPECIMEN_MODE.equals(this.compBot.getMode()) && (
                    CompBot.ArmPos.SPECIMEN_PLACE_LOW_READY.equals(this.compBot.getArmPos())
                            || CompBot.ArmPos.SPECIMEN_PLACE_LOW.equals(this.compBot.getArmPos())
                            || CompBot.ArmPos.SPECIMEN_PLACE_HIGH_READY.equals(this.compBot.getArmPos())
                            || CompBot.ArmPos.SPECIMEN_PLACE_HIGH.equals(this.compBot.getArmPos()))) {
                this.compBot.runAction(this.compBot.getActionFactory().moveArmToSpecimenPickReady());
                this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY);
            }
        });
    }

    /**
     *
     */
    public void gp2_X_BUtton() {
        this.compBot.addGp2_X_PressHandler(event -> {
            if (CompBot.Mode.NONE.equals(this.compBot.getMode())) {
                this.compBot.runAction(this.compBot.getActionFactory().moveArmToSpecimenPickReady());
                this.compBot.setMode(CompBot.Mode.SPECIMEN_MODE);
                this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY);
            }
            else if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode()) && CompBot.ArmPos.SAMPLE_CARRY.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(new SequentialAction(
                        compBot.getActionFactory().moveArmToSampleExtendReady(),
                        compBot.getActionFactory().moveArmToSamplePlaceLowReady()));
                this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_DROP_LOW_READY);
            }
            else if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode()) && (
                    CompBot.ArmPos.SAMPLE_EXTEND_READY.equals(this.compBot.getArmPos()))
                    || CompBot.ArmPos.SAMPLE_DROP_LOW.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(compBot.getActionFactory().moveArmToSamplePlaceLowReady());
                this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_DROP_LOW_READY);
            }
            else if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode()) && CompBot.ArmPos.SAMPLE_DROP_LOW_READY.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(compBot.getActionFactory().sampleDropLow());
                this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_DROP_LOW_READY);
            }
            else if (CompBot.Mode.SPECIMEN_MODE.equals(this.compBot.getMode()) && !CompBot.ArmPos.SPECIMEN_PLACE_LOW_READY.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(compBot.getActionFactory().moveArmToSpecimenPlaceLowReady());
                this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PLACE_LOW_READY);
            }
            else if (CompBot.Mode.SPECIMEN_MODE.equals(this.compBot.getMode()) && CompBot.ArmPos.SPECIMEN_PLACE_LOW_READY.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(compBot.getActionFactory().specimenPlaceLow());
                this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PLACE_LOW);
            }
        });
    }

    /**
     *
     */
    public void gp2_Y_Button() {
        this.compBot.addGp2_Y_PressHandler(event -> {
            if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode()) && CompBot.ArmPos.SAMPLE_CARRY.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(new SequentialAction(
                        compBot.getActionFactory().moveArmToSampleExtendReady(),
                        compBot.getActionFactory().moveArmToSampPlaceHighReady()));
                this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_DROP_HIGH_READY);
            }
            else if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode()) && (
                    CompBot.ArmPos.SAMPLE_EXTEND_READY.equals(this.compBot.getArmPos()))
                    || CompBot.ArmPos.SAMPLE_DROP_HIGH.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(compBot.getActionFactory().moveArmToSampPlaceHighReady());
                this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_DROP_HIGH_READY);
            }
            else if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode()) && CompBot.ArmPos.SAMPLE_DROP_HIGH_READY.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(compBot.getActionFactory().sampleDropHigh());
                this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_DROP_HIGH_READY);
            }
            else if (CompBot.Mode.SPECIMEN_MODE.equals(this.compBot.getMode()) && !CompBot.ArmPos.SPECIMEN_PLACE_HIGH_READY.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(compBot.getActionFactory().moveArmToSpecimenPlaceHighReady());
                this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PLACE_HIGH_READY);
            }
            else if (CompBot.Mode.SPECIMEN_MODE.equals(this.compBot.getMode()) && CompBot.ArmPos.SPECIMEN_PLACE_HIGH_READY.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(compBot.getActionFactory().specimenPlaceHigh());
                this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PLACE_HIGH);
            }
        });
    }

    /**
     *
     */
    public void gp2_Triggers() {
        // claw
        if (this.compBot.getConfig().useClaw) {
            this.compBot.claw.addGp2_Right_Trigger_Handler(event -> {
                if (CompBot.Mode.SPECIMEN_MODE.equals(this.compBot.getMode())) {
                    this.compBot.claw.pincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
                }
            });

            this.compBot.claw.addGp2_Left_Trigger_Handler(event -> {
                if (CompBot.Mode.SPECIMEN_MODE.equals(this.compBot.getMode())) {
                    this.compBot.claw.pincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS);
                }
            });
        }

        // intake
        if (this.compBot.getConfig().useIntake) {
            this.compBot.intake.addGp2_Right_Trigger_Handler(event -> {
                if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode())) {
                    this.compBot.intake.pincher.setPosition(Constants.INTAKE_PINCHER_CLOSE_POS);
                }
            });

            this.compBot.intake.addGp2_Left_Trigger_Handler(event -> {
                if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode())) {
                    this.compBot.intake.pincher.setPosition(Constants.INTAKE_PINCHER_OPEN_POS);
                }
            });
        }
    }
}
