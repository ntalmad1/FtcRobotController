package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.library.action.WaitAction;

/**
 *
 */
public class ActionFactory {

    /**
     */
    private CompBot compBot;

    /**
     * Contructor
     */
    public ActionFactory(CompBot compBot) {
        this.compBot = compBot;
    }

    /**
     * @return
     */
    public Action hangReady() {
        return new ParallelAction(
//                ActionFactory.this.compBot.arm.mainBoom.gotoPositionAction(HangConstants.HANG_READY.mainBoomPos),
//                ActionFactory.this.compBot.arm.viperSlide.gotoVoltageAction(0.887),
//                ActionFactory.this.compBot.claw.clawRotator.gotoPositionAction(HangConstants.HANG_READY.clawRotatorPos),
//                ActionFactory.this.compBot.intake.hServo.gotoPositionAction(HangConstants.HANG_READY.hServoPos),
//                ActionFactory.this.compBot.intake.vServo.gotoPositionAction(HangConstants.HANG_READY.vServoPos),
//                ActionFactory.this.compBot.claw.pincher.gotoPositionAction(HangConstants.HANG_READY.clawPincherPos),
//                ActionFactory.this.compBot.intake.pincher.gotoPositionAction(HangConstants.HANG_READY.intakePincherPos),
//                ActionFactory.this.compBot.doubleHooks.linearActuator.gotoPositionAction(HangConstants.HANG_READY.linearActuatorPos),
//                ActionFactory.this.compBot.doubleHooks.doubleServos.gotoPositionAction(HangConstants.HANG_READY.dHookServos)
                );
    }

    public Action doHang() {
        return new SequentialAction(
//                ActionFactory.this.compBot.arm.viperSlide.gotoVoltageAction(HangConstants.HANG_READY_1.vSlideVolts),
//                new WaitAction(500),
//                ActionFactory.this.compBot.arm.mainBoom.gotoPositionAction(HangConstants.HANG_READY_1.mainBoomPos),
//                new WaitAction(500),
//                ActionFactory.this.compBot.arm.viperSlide.gotoVoltageAction(HangConstants.HANG_READY_2.vSlideVolts),
//                ActionFactory.this.compBot.doubleHooks.doubleServos.gotoPositionAction(HangConstants.HANG_READY_2.dHookServos),
//                new WaitAction(500),
//                ActionFactory.this.compBot.doubleHooks.linearActuator.gotoPositionAction(HangConstants.HANG_READY_3.linearActuatorPos),
//                new WaitAction(1000),
//                ActionFactory.this.compBot.doubleHooks.doubleServos.gotoPositionAction(HangConstants.HANG_READY_3.dHookServos),
//                ActionFactory.this.compBot.doubleHooks.linearActuator.gotoPositionAction(HangConstants.HANG_READY_4.linearActuatorPos),
//                new WaitAction(500),
//                new ParallelAction(
//                        ActionFactory.this.compBot.arm.mainBoom.gotoPositionAction(HangConstants.HANG_READY_4.mainBoomPos),
//                        ActionFactory.this.compBot.arm.viperSlide.gotoVoltageAction(HangConstants.HANG_READY_4.vSlideVolts),
//                        ActionFactory.this.compBot.doubleHooks.doubleServos.gotoPositionAction(HangConstants.HANG_READY_4.dHookServos)
//                )
        );
    }

    /**
     * @return
     */
    public Action initPos() {
        return new SequentialAction(
            ActionFactory.this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.VIPER_SLIDES_VOLTS_MIN),
            new ParallelAction(
                ActionFactory.this.compBot.littleArm.doubleServos.gotoPositionAction(
                        ActionFactory.this.compBot.getConfig().littleArmConfig.doubleServosConfig.homePosition, 1),
                ActionFactory.this.compBot.littleArm.middleServo.gotoPositionAction(
                        ActionFactory.this.compBot.getConfig().littleArmConfig.middleServoConfig.homePosition, 1),
                ActionFactory.this.compBot.littleArm.clawRotator.gotoPositionAction(
                        ActionFactory.this.compBot.getConfig().littleArmConfig.clawRotatorConfig.homePosition, 1),
                ActionFactory.this.compBot.littleArm.clawPincher.gotoPositionAction(
                        ActionFactory.this.compBot.getConfig().littleArmConfig.clawPincherConfig.homePosition, 1)
            ),
            new WaitAction(1000),
            ActionFactory.this.compBot.bigArm.mainBoom.gotoPositionAction(0),
            new InstantAction(() -> { ActionFactory.this.compBot.setArmPos(CompBot.ArmPos.INIT_READY); })

        );
    }

    /**
     * @return
     */
    public Action samplePickReady() {
        if (this.compBot.getArmPos().equals(CompBot.ArmPos.INIT_READY)) {
            return new SequentialAction(
                    new ParallelAction(
                            this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.SAMPLE_PICK_READY.vSlideVolts),
                            this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_READY.doubleServosPos),
                            this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_READY.middleServoPos),
                            this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawRotatorPos),
                            this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawPincherPos)),
                    new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY); }));
        }
        else {
            return new SequentialAction(
                    new ParallelAction(
                            this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.SAMPLE_PICK_READY.vSlideVolts),
                            this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_READY.doubleServosPos),
                            this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_READY.middleServoPos),
                            this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawRotatorPos),
                            this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawPincherPos)),
                    new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY); }),
                    this.compBot.bigArm.mainBoom.gotoPositionAction(100, 1),
                    this.compBot.bigArm.mainBoom.gotoPositionAction(0, 0.5));
        }
    }

    /**
     *
     * @return
     */
    public Action samplePickDown() {
        return new SequentialAction(
            this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_DOWN.doubleServosPos),
            new WaitAction(75),
            this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_DOWN.middleServoPos),
            new InstantAction(() -> this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_DOWN))
        );
    }


    /**
     *
     * @return
     */
    public Action samplePickUp() {
        return new ParallelAction(
                this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_UP.doubleServosPos),
                this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_UP.middleServoPos),
                new InstantAction(() -> this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_UP))
        );
    }

    /**
     *
     * @return
     */
    public Action inverseSamplePick() {
        return new ParallelAction(
                this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_READY.doubleServosPos),
                this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_READY.middleServoPos),
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_OPEN_POS, 1),
                new InstantAction(() -> this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY))
        );
    }

    /**
     * @return
     */
    public Action sampleExtendReady() {
        return new SequentialAction(
                this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.VIPER_SLIDES_VOLTS_MIN),
                new ParallelAction(
                    this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.MAIN_BOOM_MAX_TICS, 1),
                    new SequentialAction(
                        new WaitAction(500),
                        this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.doubleServosPos),
                        new WaitAction(250),
                        this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.middleServoPos),
                        this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.clawRotatorPos))
                ),
                new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_EXTEND_READY); })
        );
    }

    /**
     * @return
     */
    public Action sampleDropHigh() {
        return new SequentialAction(
            this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_OPEN_POS, 1),
            new WaitAction(250),
            this.compBot.littleArm.middleServo.gotoPositionAction(0.5, 1),
            new WaitAction(250),
            this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.VIPER_SLIDES_VOLTS_MIN),
            new InstantAction(() -> { this.compBot.setArmPos( CompBot.ArmPos.SAMPLE_DROP_HIGH );})
        );
    }




    //------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------

    /**
     * @return
     */
    public Action specimenPickReady() {
        return new SequentialAction(
                new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY); }),
                new ParallelAction(
                        this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.MAIN_BOOM_MAX_TICS),
                        this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.SPECIMEN_PICK_READY.vSlideVolts),
                        this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SPECIMEN_PICK_READY.doubleServosPos),
                        this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SPECIMEN_PICK_READY.middleServoPos),
                        this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SPECIMEN_PICK_READY.clawRotatorPos),
                        this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.SPECIMEN_PICK_READY.clawPincherPos))
                );

    }

    /**
     * @return
     */
    public Action specimenPick() {
        return new SequentialAction(
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_CLOSE_POS, 1),
                new WaitAction(250),
                new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK); }),
                this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.MAIN_BOOM_MAX_TICS - 375)
        );
    }

    /**
     *
     * @return
     */
    public Action inverseSpecimenPick() {
        return new SequentialAction(
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_OPEN_POS, 1),
                new WaitAction(250),
                new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY); }),
                this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.MAIN_BOOM_MAX_TICS)
            );
    }


    /**
     *
     * @return
     */
    public Action specimenPlaceHighReady() {
        return new SequentialAction(
                new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PLACE_HIGH_READY); }),
                new ParallelAction(
                        this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.mainBoomPos),
                        this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos),
                        this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos)
                ),
                new ParallelAction(
                        new SequentialAction(
                                this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos)
                        ),
                        this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.SPECIMEN_PLACE_HIGH_READY.vSlideVolts))
        );
    }

    /**
     *
     * @return
     */
    public Action specimenPlaceHigh() {
        return new SequentialAction(
                new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PLACE_HIGH); }),
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_OPEN_POS, 1),
                new WaitAction(400),
                this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.VIPER_SLIDES_VOLTS_MIN, 0.75),
                new ParallelAction(
                        this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.SPECIMEN_PICK_READY.mainBoomPos),
                        this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SPECIMEN_PICK_READY.doubleServosPos),
                        this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SPECIMEN_PICK_READY.middleServoPos)
                ),
                new ParallelAction(
                        new SequentialAction(
                            this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SPECIMEN_PICK_READY.clawRotatorPos)
                        )),
                new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY); })

        );
    }
}