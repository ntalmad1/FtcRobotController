package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.library.action.WaitAction;
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

        // clear and re-init
        this.compBot.addGp1_Start_PressHandler(event -> {
            this.compBot.setArmPos(CompBot.ArmPos.INIT);
            this.compBot.runAction(this.compBot.getActionFactory().initPos());
        });


        // X button
        this.gp1_X_Button();
    }

    /**
     *
     */
    public void configureGamePad2() {

        // Panic Button / Kill Switch
        this.compBot.addGp2_Back_PressHandler(event -> {
            this.compBot.terminateOpModeNow();
        });

        // main boom
        //this.compBot.bigArm.mainBoom.addControl(Control.Gp2_RightStickY);
        this.compBot.bigArm.mainBoom.addGp2_RightStick_Y_Handler(event -> {
            double deadZone = 0.2;

            double pos = event.getPosition();

            double power = 0;

            if (pos > deadZone) {
                power = (pos - deadZone) / (1 - deadZone);
            }
            else if (pos < (deadZone * -1)) {

                power = (pos - (deadZone * -1)) / (1 - deadZone);
            }

            this.compBot.bigArm.mainBoom.move(power);
        });


        // viper slides
        this.compBot.bigArm.viperSlide.addControl(Control.Gp2_RightStickX);

        // double servos and middle servo
        this.gp2_Dpad();

        // claw rotator
        //this.compBot.littleArm.clawRotator.addControl(Control.Gp2_LeftStickX);
        //this.gp2_Left_Bumper();
        //this.gp2_Right_Bumper();
        this.compBot.littleArm.clawRotator.addGp2_LeftStick_X_Handler(event -> {

            double x = event.getPosition() * -1;

            double servoPos = 0.01 * Math.pow(x, 2) + 0.35 * x + 0.64;

            this.compBot.littleArm.clawRotator.setServoPosition(servoPos);
        });

        // claw pincher
        this.gp2_Triggers();

        // clear and re-init
        this.compBot.addGp2_Start_PressHandler(event -> {
            this.compBot.setArmPos(CompBot.ArmPos.INIT);
            this.compBot.runAction(this.compBot.getActionFactory().initPos());
        });

        // presets
        this.gp2_A_Button();
        this.gp2_B_Button();
        this.gp2_X_Button();
        this.gp2_Y_Button();
    }

    /**
     *
     */
    public void gp1_X_Button()
    {
        this.compBot.addGp1_X_PressHandler(event -> {
            if (!CompBot.ArmPos.HANG_READY.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(this.compBot.getActionFactory().hangReady());
            }
            else if (CompBot.ArmPos.HANG_READY.equals(this.compBot.getArmPos())) {
                this.compBot.runAction(this.compBot.getActionFactory().doHang());
            }
        });
    }

    /**
     *
     */
    public void gp2_Dpad() {
        // dpad up
        this.compBot.addGp2_Dpad_Up_DownHandler(event -> {
            this.compBot.littleArm.doubleServos.move(1);
        });

        // dpad down
        this.compBot.addGp2_Dpad_Down_DownHandler(event -> {
            this.compBot.littleArm.doubleServos.move(-1);
        });

        // dpad left
        this.compBot.addGp2_Dpad_Left_DownHandler(event -> {
            this.compBot.littleArm.middleServo.move(-1);
        });

        //dpad right
        this.compBot.addGp2_Dpad_Right_DownHandler(event -> {
            this.compBot.littleArm.middleServo.move(1);
        });
    }

    /**
     *
     */
    public void gp2_Left_Bumper() {
//        this.compBot.addGp2_Left_Bumper_PressHandler(event -> {
//            if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode())) {
//                if (CompBot.IntakePos.STRAIGHT.equals(this.compBot.getIntakePos())) {
//                    if (this.compBot.arm.viperSlide.getVoltage() <= Constants.SAMPLE_PICK_LEFT_READY_MIN.vSlideVolts) {
//                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickLeftReadyMin());
//                    } else {
//                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickLeftReady());
//                    }
//                    this.compBot.setIntakePos(CompBot.IntakePos.LEFT);
//                }
//                else if (CompBot.IntakePos.RIGHT.equals(this.compBot.getIntakePos())) {
//                    if (this.compBot.arm.viperSlide.getVoltage() <= Constants.SAMPLE_PICK_READY_MIN.vSlideVolts) {
//                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickReadyMin());
//                    } else {
//                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickReady());
//                    }
//                    this.compBot.setIntakePos(CompBot.IntakePos.STRAIGHT);
//                }
//            }
//            else {
//                this.compBot.claw.clawRotator.move(-1);
//            }
//        });
    }

    /**
     *
     */
    public void gp2_Right_Bumper() {
//        this.compBot.addGp2_Right_Bumper_PressHandler(event -> {
//            if (CompBot.Mode.SAMPLE_MODE.equals(this.compBot.getMode())) {
//                if (CompBot.IntakePos.STRAIGHT.equals(this.compBot.getIntakePos())) {
//                    if (this.compBot.arm.viperSlide.getVoltage() <= Constants.SAMPLE_PICK_RIGHT_READY_MIN.vSlideVolts) {
//                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickRightReadyMin());
//                    } else {
//                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickRightReady());
//                    }
//                    this.compBot.setIntakePos(CompBot.IntakePos.RIGHT);
//                }
//                else if (CompBot.IntakePos.LEFT.equals(this.compBot.getIntakePos())) {
//                    if (this.compBot.arm.viperSlide.getVoltage() <= Constants.SAMPLE_PICK_READY_MIN.vSlideVolts) {
//                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickReadyMin());
//                    } else {
//                        this.compBot.runAction(this.compBot.getActionFactory().moveArmToSamplePickReady());
//                    }
//                    this.compBot.setIntakePos(CompBot.IntakePos.STRAIGHT);
//                }
//            }
//            else {
//                this.compBot.claw.clawRotator.move(1);
//            }
//        });
    }

    /**
     *
     */
    public void gp2_A_Button() {
        this.compBot.addGp2_A_PressHandler(event -> {
            if (!this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_PICK_READY)
                && !this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_PICK_DOWN)
                && !this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_PICK_UP)) {

                this.compBot.runAction(this.compBot.getActionFactory().samplePickReady());

            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_PICK_READY)) {

                Action pickAction = new SequentialAction(
                        this.compBot.getActionFactory().samplePickDown(),
                        new WaitAction(250),
                        this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_CLOSE_POS, 1),
                        new WaitAction(250),
                        this.compBot.getActionFactory().samplePickUp()
                );

                this.compBot.runAction(pickAction);

            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_PICK_UP)) {

                this.compBot.runAction(this.compBot.getActionFactory().inverseSamplePick());
            }
        });
    }

    /**
     *
     */
    public void gp2_B_Button() {
        this.compBot.addGp2_B_PressHandler(event -> {
            if (!this.compBot.getArmPos().equals(CompBot.ArmPos.SPECIMEN_PICK_READY)
                    && !this.compBot.getArmPos().equals(CompBot.ArmPos.SPECIMEN_PICK)) {

                this.compBot.runAction(this.compBot.getActionFactory().specimenPickReady());

            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SPECIMEN_PICK_READY)) {

                this.compBot.runAction(this.compBot.getActionFactory().specimenPick());

            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SPECIMEN_PICK)) {

                this.compBot.runAction(this.compBot.getActionFactory().inverseSpecimenPick());
            }
        });
    }

    /**
     *
     */
    public void gp2_X_Button() {
        this.compBot.addGp2_X_PressHandler(event -> {
            if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_PICK_UP)) {
                Action action = new SequentialAction(
                        this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.VIPER_SLIDES_VOLTS_MIN),
                        new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_RETRACTED); })
                );

                this.compBot.runAction(action);
            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_RETRACTED)) {
                this.compBot.runAction(this.compBot.getActionFactory().sampleExtendReady());
            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_EXTEND_READY)) {
                Action action = new SequentialAction(
                        this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.VIPER_SLIDES_VOLTS_MAX),
                        new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_DROP_HIGH_READY); })
                );

                this.compBot.runAction(action);
            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_DROP_HIGH_READY)) {
                Action action = new SequentialAction(
                        this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_OPEN_POS, 1),
                        new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_DROPPING_HIGH); })
                );

                this.compBot.runAction(action);
            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SAMPLE_DROPPING_HIGH)) {
                this.compBot.runAction(this.compBot.getActionFactory().sampleDropHigh());
            }
        });
    }

    /**
     *
     */
    public void gp2_Y_Button() {
        this.compBot.addGp2_Y_PressHandler(event -> {
            if (this.compBot.getArmPos().equals(CompBot.ArmPos.SPECIMEN_PICK)) {

                this.compBot.runAction(this.compBot.getActionFactory().specimenPlaceHighReady());

            }
            else if (this.compBot.getArmPos().equals(CompBot.ArmPos.SPECIMEN_PLACE_HIGH_READY)) {

                this.compBot.runAction(this.compBot.getActionFactory().specimenPlaceHigh());

            }
        });
    }

    /**
     *
     */
    public void gp2_Triggers() {
        // claw
        this.compBot.littleArm.clawPincher.addGp2_Right_Trigger_Handler(event -> {
            this.compBot.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
        });

        this.compBot.littleArm.clawPincher.addGp2_Left_Trigger_Handler(event -> {
            this.compBot.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS);
        });
    }
}
