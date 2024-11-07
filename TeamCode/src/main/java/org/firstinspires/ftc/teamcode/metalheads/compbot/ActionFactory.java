package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;

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
    public Action doHangReady() {
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
    public Action moveArmToInitPos() {
        return new SequentialAction(
//                ActionFactory.this.compBot.arm.viperSlide.gotoVoltageAction(Constants.SAMPLE_PICK_READY_MIN.vSlideVolts),
//                ActionFactory.this.compBot.arm.mainBoom.gotoPositionAction(0, 1),
//                new ParallelAction(
//                        ActionFactory.this.compBot.intake.hServo.gotoPositionAction(getConfig().intakeConfig.hServoConfig.homePosition, 1),
//                        ActionFactory.this.compBot.intake.vServo.gotoPositionAction(getConfig().intakeConfig.vServoConfig.homePosition, 1),
//                        ActionFactory.this.compBot.intake.closePincherAction(),
//                        ActionFactory.this.compBot.claw.closeClawAction(),
//                        ActionFactory.this.compBot.claw.clawRotator.gotoPositionAction(0, 1)
//                ),
//                new WaitAction(1000),
//                ActionFactory.this.compBot.arm.viperSlide.gotoVoltageAction(0.586)
        );
    }



    /**
     *
     * @return
     */
    public Action moveArmToSamplePickReadyMin() {
        return new ParallelAction(
//                intake.hServo.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.hServoPos),
//                intake.vServo.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.vServoPos),
//                arm.viperSlide.gotoVoltageAction(Constants.SAMPLE_PICK_READY_MIN.vSlideVolts),
//                new WaitAction(1000),
//                intake.openPincherAction(),
//                arm.mainBoom.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.mainBoomPos.getPos(), 0.5)
        );
    }

    /**
     * @return
     */
    public Action moveArmToSamplePickReady() {

        // tics = -1308.8 * v^2 + 3193.07 * v + -2115.4

//        double v = arm.viperSlide.getVoltage();
//
//        int tics = (int) (-1308.8 * Math.pow(v, 2) + 3193.07 * v - 2115.4);
//
//        // m = (vServoMax - vServoMin) / (vMax - vMin)
//        // vServoPos = m\m(v-vMin) + vServoMin
//        double vMin = Constants.SAMPLE_PICK_READY_MIN.vSlideVolts;
//        double vMax = Constants.SAMPLE_PICK_READY_MAX.vSlideVolts;
//        double vServoMin = Constants.SAMPLE_PICK_READY_MIN.vServoPos.getPos();
//        double vServoMax = Constants.SAMPLE_PICK_READY_MAX.vServoPos.getPos();
//
//        double m = (vServoMax - vServoMin) / (vMax - vMin);
//        double vServoPos = m * (v - vMin) + vServoMin;

        return new ParallelAction(
//                intake.hServo.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.hServoPos),
//                intake.vServo.gotoPositionAction(vServoPos, 1),
//                //arm.viperSlide.gotoVoltageAction(Constants.SAMPLE_PICK_READY_MIN.vSlideVolts),
//                intake.openPincherAction(),
//                arm.mainBoom.gotoPositionAction(tics, 0.5),
//                claw.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.clawRotatorPos)
        );
    }


    /**
     * @return
     */
    public Action moveArmToSamplePickLeftReady() {
        return new ParallelAction(
//                intake.hServo.gotoPositionAction(Constants.SAMPLE_PICK_LEFT_READY_MIN.hServoPos),
//                intake.vServo.gotoPositionAction(Constants.SAMPLE_PICK_LEFT_READY_MIN.vServoPos),
//                intake.openPincherAction(),
//                claw.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.clawRotatorPos)
        );
    }

    /**
     * @return
     */
    public Action moveArmToSamplePickRightReady() {
        return new ParallelAction(
//                intake.hServo.gotoPositionAction(Constants.SAMPLE_PICK_RIGHT_READY_MIN.hServoPos),
//                intake.vServo.gotoPositionAction(Constants.SAMPLE_PICK_RIGHT_READY_MIN.vServoPos),
//                intake.openPincherAction(),
//                claw.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY_MIN.clawRotatorPos)
        );
    }


    /**
     * @return
     */
    public Action moveArmToSamplePickLeftReadyMin() {
//        return ActionFactory.this.compBot.moveArmAction(Constants.SAMPLE_PICK_LEFT_READY_MIN);
        return null;
    }

    /**
     * @return
     */
    public Action moveArmToSamplePickRightReadyMin() {
        //return ActionFactory.this.compBot.moveArmAction(Constants.SAMPLE_PICK_RIGHT_READY_MIN);
        return null;
    }

    /**
     * @return
     */
    public Action moveArmToSamplePickReadyMax() {
        //return ActionFactory.this.compBot.moveArmAction(Constants.SAMPLE_PICK_READY_MAX);
        return null;
    }

    /**
     * @return
     */
    public Action moveArmToSamplePickLeftReadyMax() {
        //return ActionFactory.this.compBot.moveArmAction(Constants.SAMPLE_PICK_LEFT_READY_MAX);
        return null;
    }

    /**
     * @return
     */
    public Action moveArmToSamplePickRightReadyMax() {
        //return ActionFactory.this.compBot.moveArmAction(Constants.SAMPLE_PICK_RIGHT_READY_MAX);
        return null;
    }


    /**
     * @return
     */
    public Action sampleDropLow() {

       // this.mainBoomReturnPos = ActionFactory.this.compBot.arm.mainBoom.getCurrentPosition();

        return new SequentialAction(
//                ActionFactory.this.compBot.intake.vServo.gotoPositionAction(
//                        Constants.SAMPLE_PLACE_LOW_READY.vServoPos.getPos() + 0.1, 1),
//                ActionFactory.this.compBot.intake.vServo.gotoPositionAction(
//                        Constants.SAMPLE_PLACE_LOW_READY.vServoPos.getPos() - 0.05, 1),
//                ActionFactory.this.compBot.intake.openPincherAction(),
//                ActionFactory.this.compBot.intake.vServo.gotoPositionAction(Constants.SAMPLE_PLACE_LOW_READY.vServoPos)
        );

    }

    /**
     * @return
     */
    public Action sampleDropHigh() {

        //this.mainBoomReturnPos = ActionFactory.this.compBot.arm.mainBoom.getCurrentPosition();

        return new SequentialAction(
//                ActionFactory.this.compBot.intake.vServo.gotoPositionAction(
//                        Constants.SAMPLE_PLACE_HIGH_READY.vServoPos.getPos() + 0.1, 1),
//                ActionFactory.this.compBot.intake.vServo.gotoPositionAction(
//                        Constants.SAMPLE_PLACE_HIGH_READY.vServoPos.getPos() - 0.05, 1),
//                ActionFactory.this.compBot.intake.openPincherAction(),
//                ActionFactory.this.compBot.intake.vServo.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.vServoPos)
        );
    }

    /**
     */
    private int mainBoomReturnPos = 0;

    /**
     * @return
     */
    public Action samplePick() {

        //this.mainBoomReturnPos = ActionFactory.this.compBot.arm.mainBoom.getCurrentPosition();

        return new SequentialAction(
//                ActionFactory.this.compBot.intake.closePincherAction(),
//                new WaitAction(500),
//                ActionFactory.this.compBot.arm.mainBoom.gotoPositionAction(Constants.SAMPLE_CARRY.mainBoomPos)
        );

    }

    /**
     * @return
     */
    public Action inverseSamplePick() {

        return new SequentialAction(
//                ActionFactory.this.compBot.intake.openPincherAction(),
//                new WaitAction(500),
//                ActionFactory.this.compBot.arm.mainBoom.gotoPositionAction(this.mainBoomReturnPos)
        );
    }

    /**
     * @return
     */
    public Action moveArmToSampleCarry() {
        //return ActionFactory.this.compBot.moveArmAction(Constants.SAMPLE_CARRY);
        return null;
    }

    /**
     * @return
     */
    public Action moveArmToSampleExtendReady() {
        //return ActionFactory.this.compBot.moveArmAction(Constants.SAMPLE_EXTEND_READY);
        return null;
    }

    /**
     * @return
     */
    public Action moveArmToSamplePlaceLowReady() {
        //return ActionFactory.this.compBot.moveArmAction(Constants.SAMPLE_PLACE_LOW_READY);
        return null;
    }

    /**
     * @return
     */
    public Action moveArmToSampPlaceHighReady() {

        ActionFactory.this.compBot.bigArm.viperSlide.resetEncoder();

        return new ParallelAction(
//                intake.hServo.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.hServoPos),
//                intake.vServo.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.vServoPos),
//                intake.pincher.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.intakePincherPos),
//                arm.viperSlide.gotoPositionAction(2900),
//                arm.mainBoom.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.mainBoomPos),
//                claw.clawRotator.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.clawRotatorPos),
//                claw.pincher.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.clawPincherPos)
        );
    }

    //------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------

    /**
     * @return
     */
    public Action moveArmToSpecimenPickReady() {
        //return ActionFactory.this.compBot.moveArmAction(Constants.SPECIMEN_PICK_READY);
        return null;
    }

    /**
     * @return
     */
    public Action specimenPick() {
        return new SequentialAction(
//                new InstantAction(() -> {
//                    ActionFactory.this.compBot.claw.pincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
//                }),
//                new WaitAction(500),
//                ActionFactory.this.compBot.arm.mainBoom.gotoPositionAction(
//                        new MotorPos(ActionFactory.this.compBot.arm.mainBoom.getCurrentPosition() + 400, 1))
        );
    }

    /**
     *
     * @return
     */
    public Action inverseSpecimenPick() {
        //return ActionFactory.this.compBot.moveArmAction(Constants.SPECIMEN_PICK_READY);
        return null;
    }

    /**
     * @return
     */
    public Action moveArmToSpecimenPlaceHighReady() {
        //return ActionFactory.this.compBot.moveArmAction(Constants.SPECIMEN_PLACE_HIGH_READY);
        return null;
    }

    /**
     * @return
     */
    public Action moveArmToSpecimenPlaceHigh() {
        //return ActionFactory.this.compBot.moveArmAction(Constants.SPECIMEN_PLACE_HIGH);
        return null;
    }

    /**
     *
     * @return
     */
    public Action specimenPlaceHigh() {
        return new SequentialAction(
                // ActionFactory.this.compBot.arm.mainBoom.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH.mainBoomPos)
        );
    }

    /**
     * @return
     */
    public Action moveArmToSpecimenPlaceLowReady() {
        //return ActionFactory.this.compBot.moveArmAction(Constants.SPECIMEN_PLACE_LOW_READY);
        return null;
    }

    /**
     * @return
     */
    public Action moveArmToSpecimenPlaceLow() {
        //return ActionFactory.this.compBot.moveArmAction(Constants.SPECIMEN_PLACE_LOW);
        return null;
    }

    /**
     * @return
     */
    public Action specimenPlaceLow() {
        return new SequentialAction(
                // ActionFactory.this.compBot.arm.mainBoom.gotoPositionAction(Constants.SPECIMEN_PLACE_LOW.mainBoomPos)
        );
    }
}