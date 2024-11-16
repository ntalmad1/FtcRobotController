package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.library.action.WaitAction;

/**
 *
 */
public class AutoActionFactory {

    /**
     */
    private CompBot compBot;

    /**
     * Contructor
     */
    public AutoActionFactory(CompBot compBot) {
        this.compBot = compBot;
    }

    /**
     * @return
     */
    public Action hangReady() {
        return new SequentialAction(
            this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.HANG_READY.mainBoomPos),
            new ParallelAction(
                this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.HANG_READY.vSlidePos),
                this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.HANG_READY.doubleServosPos),
                this.compBot.littleArm.middleServo.gotoPositionAction(Constants.HANG_READY.middleServoPos),
                this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.HANG_READY.clawRotatorPos),
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.HANG_READY.clawPincherPos)),
            new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.HANG_READY); })
        );
    }

    /**
     *
     * @return
     */
    public Action doHang() {
        return new SequentialAction(
            this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.HANG.vSlidePos),
            this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.HANG.mainBoomPos),
            new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.HANG); })
        );
    }

    /**
     * @return
     */
    public Action initPos() {
        return new SequentialAction(
            AutoActionFactory.this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.VIPER_SLIDES_MIN_TICS),
            new ParallelAction(
                AutoActionFactory.this.compBot.littleArm.doubleServos.gotoPositionAction(
                        AutoActionFactory.this.compBot.getConfig().littleArmConfig.doubleServosConfig.homePosition, 1),
                AutoActionFactory.this.compBot.littleArm.middleServo.gotoPositionAction(
                        AutoActionFactory.this.compBot.getConfig().littleArmConfig.middleServoConfig.homePosition, 1),
                AutoActionFactory.this.compBot.littleArm.clawRotator.gotoPositionAction(
                        AutoActionFactory.this.compBot.getConfig().littleArmConfig.clawRotatorConfig.homePosition, 1),
                AutoActionFactory.this.compBot.littleArm.clawPincher.gotoPositionAction(
                        AutoActionFactory.this.compBot.getConfig().littleArmConfig.clawPincherConfig.homePosition, 1)
            ),
            new WaitAction(1000),
            AutoActionFactory.this.compBot.bigArm.mainBoom.gotoPositionAction(0, 1, 300),
            new InstantAction(() -> { AutoActionFactory.this.compBot.setArmPos(CompBot.ArmPos.INIT_READY); })
        );
    }

    /**
     * @return
     */
    public Action samplePickReady() {
        if (this.compBot.getArmPos().equals(CompBot.ArmPos.INIT_READY)) {
            return new SequentialAction(
                    new ParallelAction(
                            this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.SAMPLE_PICK_READY.vSlidePos),
                            this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_READY.doubleServosPos),
                            this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_READY.middleServoPos),
                            this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawRotatorPos),
                            this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawPincherPos)),
                    new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY); }));
        }
        else {
            return new SequentialAction(
                    new ParallelAction(
                            this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.SAMPLE_PICK_READY.vSlidePos),
                            this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_READY.doubleServosPos),
                            this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_READY.middleServoPos),
                            this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawRotatorPos),
                            this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawPincherPos)),
                    this.compBot.bigArm.mainBoom.gotoPositionAction(0, 1, 300),
                    new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY); }));

        }
    }

    /**
     *
     * @return
     */
    public Action samplePickDown() {
        return new SequentialAction(
                this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_DOWN.middleServoPos),
                new WaitAction(75),
                this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_DOWN.doubleServosPos),
            new InstantAction(() -> this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_DOWN))
        );
    }


    /**
     *
     * @return
     */
    public Action samplePickUp() {
        return new SequentialAction(
                new ParallelAction(
                        this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_UP.doubleServosPos),
                        this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_UP.middleServoPos)
                        ),
                new InstantAction(() -> this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_UP))
        );
    }

    /**
     *
     * @return
     */
    public Action pickSample() {
        return new SequentialAction(
                this.compBot.getActionFactory().samplePickDown(),
                new WaitAction(250),
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_CLOSE_POS, 1),
                new WaitAction(350),
                this.compBot.getActionFactory().samplePickUp()
        );
    }

    /**
     *
     * @return
     */
    public Action inverseSamplePick() {
        return new SequentialAction(
            new ParallelAction(
                this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_READY.doubleServosPos),
                this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_READY.middleServoPos),
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_OPEN_POS, 1)),
            new InstantAction(() -> this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY))
        );
    }

    /**
     * @return
     */
    public Action sampleExtendReady() {
        return new SequentialAction(
                this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.VIPER_SLIDES_MIN_TICS),
                new ParallelAction(
                    this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.MAIN_BOOM_MAX_TICS, 1, 300),
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
            this.compBot.littleArm.middleServo.gotoPositionAction(0.5, 1),
            new WaitAction(250),
            this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.VIPER_SLIDES_MIN_TICS),
            new InstantAction(() -> { this.compBot.setArmPos( CompBot.ArmPos.SAMPLE_DROPPED_HIGH );})
        );
    }

    /**
     *
     * @return
     */
    public Action extendToSampleDropHigh() {
        return new SequentialAction(
                this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.VIPER_SLIDES_MAX_TICS),
                new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_DROP_HIGH_READY); })
        );
    }

    /**
     *
     * @return
     */
    public Action retractSample() {
        return new SequentialAction(
                this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.VIPER_SLIDES_MIN_TICS),
                new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_RETRACTED); })
        );
    }

    //------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------

    /**
     * @return
     */
    public Action specimenPickReady() {
        return new SequentialAction(
                new ParallelAction(
                        this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.MAIN_BOOM_MAX_TICS, 1, 300),
                        this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.SPECIMEN_PICK_READY.vSlidePos),
                        this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SPECIMEN_PICK_READY.doubleServosPos),
                        this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SPECIMEN_PICK_READY.middleServoPos),
                        this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SPECIMEN_PICK_READY.clawRotatorPos),
                        this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.SPECIMEN_PICK_READY.clawPincherPos)),
                new InstantAction(() -> this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY))
        );
    }

    /**
     * @return
     */
    public Action specimenPick() {
        return new SequentialAction(
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_CLOSE_POS, 1),
                new WaitAction(250),
                this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.MAIN_BOOM_MAX_TICS - 375),
                new InstantAction(() -> this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK))
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
                this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.MAIN_BOOM_MAX_TICS, 1, 300),
                new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY); })
            );
    }


    /**
     *
     * @return
     */
    public Action specimenPlaceHighReady() {
        return new SequentialAction(
                new ParallelAction(
                        this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.mainBoomPos),
                        this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos),
                        this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos)
                ),
                new ParallelAction(
                        new SequentialAction(
                                this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos)
                        ),
                        this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.vSlidePos)
                ),
                new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PLACE_HIGH_READY); })
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
                this.compBot.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.VIPER_SLIDES_MIN_TICS, 1),
                this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SPECIMEN_PICK_READY.middleServoPos),
                new WaitAction(250),
                new ParallelAction(
                        this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.SPECIMEN_PICK_READY.mainBoomPos.getPos(), 1, 300),
                        this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SPECIMEN_PICK_READY.doubleServosPos),
                        this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SPECIMEN_PICK_READY.clawRotatorPos)
                ),
                new InstantAction(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY); })
        );
    }
}