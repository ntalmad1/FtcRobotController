package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.action.InstantActionImpl;
import org.firstinspires.ftc.teamcode.library.action.ParallelActionImpl;
import org.firstinspires.ftc.teamcode.library.action.SequentialActionImpl;
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
    public AbstractAction hangReady() {
        return new SequentialActionImpl(
            this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.HANG_READY.mainBoomPos),
            new ParallelActionImpl(
                this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.HANG_READY.vSlideVolts),
                this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.HANG_READY.doubleServosPos),
                this.compBot.littleArm.middleServo.gotoPositionAction(Constants.HANG_READY.middleServoPos),
                this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.HANG_READY.clawRotatorPos),
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.HANG_READY.clawPincherPos)),
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.HANG_READY); }));
    }

    /**
     *
     * @return
     */
    public AbstractAction doHang() {
        return new SequentialActionImpl(
            this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.HANG.vSlideVolts),
            this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.HANG.mainBoomPos),
            new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.HANG); })
        );
    }

    /**
     * @return
     */
    public AbstractAction initPos() {
        return new SequentialActionImpl(
            ActionFactory.this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.VIPER_SLIDES_VOLTS_MIN, false),
            new ParallelActionImpl(
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
            ActionFactory.this.compBot.bigArm.mainBoom.gotoPositionAction(0, 1, 300),
            new InstantActionImpl(() -> { ActionFactory.this.compBot.setArmPos(CompBot.ArmPos.INIT_READY); })
        );
    }

    /**
     * @return
     */
    public AbstractAction samplePickReady() {
        if (this.compBot.getArmPos().equals(CompBot.ArmPos.INIT_READY)) {
            return new SequentialActionImpl(
                    new ParallelActionImpl(
                            this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.SAMPLE_PICK_READY.vSlideVolts),
                            this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_READY.doubleServosPos),
                            this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_READY.middleServoPos),
                            this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawRotatorPos),
                            this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawPincherPos)),
                    new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY); }));
        }
        else {
            return new SequentialActionImpl(
                    new ParallelActionImpl(
                            this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.SAMPLE_PICK_READY.vSlideVolts),
                            this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_READY.doubleServosPos),
                            this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_READY.middleServoPos),
                            this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawRotatorPos),
                            this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.SAMPLE_PICK_READY.clawPincherPos)),
                    new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY); }),
                    this.compBot.bigArm.mainBoom.gotoPositionAction(100, 1),
                    this.compBot.bigArm.mainBoom.gotoPositionAction(0, 0.5, 300));
        }
    }

    /**
     *
     * @return
     */
    public AbstractAction samplePickDown() {
        return new SequentialActionImpl(
            this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_DOWN.doubleServosPos),
            new WaitAction(75),
            this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_DOWN.middleServoPos),
            new InstantActionImpl(() -> this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_DOWN))
        );
    }


    /**
     *
     * @return
     */
    public AbstractAction samplePickUp() {
        return new ParallelActionImpl(
                this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_UP.doubleServosPos),
                this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_UP.middleServoPos),
                new InstantActionImpl(() -> this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_UP))
        );
    }

    /**
     *
     * @return
     */
    public AbstractAction pickSample() {
        return new SequentialActionImpl(
                this.compBot.getActionFactory().samplePickDown(),
                new WaitAction(250),
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_CLOSE_POS, 1),
                new WaitAction(250),
                this.compBot.getActionFactory().samplePickUp()
        );
    }

    /**
     *
     * @return
     */
    public AbstractAction inverseSamplePick() {
        return new ParallelActionImpl(
                this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PICK_READY.doubleServosPos),
                this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PICK_READY.middleServoPos),
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_OPEN_POS, 1),
                new InstantActionImpl(() -> this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_PICK_READY))
        );
    }

    /**
     * @return
     */
    public AbstractAction sampleExtendReady() {
        return new SequentialActionImpl(
                this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.VIPER_SLIDES_VOLTS_MIN),
                new ParallelActionImpl(
                    this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.MAIN_BOOM_MAX_TICS, 1, 300),
                    new SequentialActionImpl(
                        new WaitAction(500),
                        this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.doubleServosPos),
                        new WaitAction(250),
                        this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.middleServoPos),
                        this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SAMPLE_PLACE_HIGH_READY.clawRotatorPos))
                ),
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_EXTEND_READY); })
        );
    }

    /**
     * @return
     */
    public AbstractAction sampleDropHigh() {
        return new SequentialActionImpl(
            this.compBot.littleArm.middleServo.gotoPositionAction(0.5, 1),
            new WaitAction(250),
            this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.VIPER_SLIDES_VOLTS_MIN),
            new InstantActionImpl(() -> { this.compBot.setArmPos( CompBot.ArmPos.SAMPLE_DROPPED_HIGH );})
        );
    }

    /**
     *
     * @return
     */
    public AbstractAction extendToSampleDropHigh() {
        return new SequentialActionImpl(
                this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.VIPER_SLIDES_VOLTS_MAX),
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_DROP_HIGH_READY); })
        );
    }

    /**
     *
     * @return
     */
    public AbstractAction retractSample() {
        return new SequentialActionImpl(
                this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.VIPER_SLIDES_VOLTS_MIN),
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SAMPLE_RETRACTED); })
        );
    }

    //------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------

    /**
     * @return
     */
    public AbstractAction specimenPickReady() {
        return new SequentialActionImpl(
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY); }),
                new ParallelActionImpl(
                        this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.MAIN_BOOM_MAX_TICS, 1, 300),
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
    public AbstractAction specimenPick() {
        return new SequentialActionImpl(
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_CLOSE_POS, 1),
                new WaitAction(250),
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK); }),
                this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.MAIN_BOOM_MAX_TICS - 375)
        );
    }

    /**
     *
     * @return
     */
    public AbstractAction inverseSpecimenPick() {
        return new SequentialActionImpl(
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_OPEN_POS, 1),
                new WaitAction(250),
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY); }),
                this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.MAIN_BOOM_MAX_TICS, 1, 300)
            );
    }


    /**
     *
     * @return
     */
    public AbstractAction specimenPlaceHighReady() {
        return new SequentialActionImpl(
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PLACE_HIGH_READY); }),
                new ParallelActionImpl(
                        this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.mainBoomPos),
                        this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos),
                        this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos)
                ),
                new ParallelActionImpl(
                        new SequentialActionImpl(
                                this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos)
                        ),
                        this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.SPECIMEN_PLACE_HIGH_READY.vSlideVolts))
        );
    }

    /**
     *
     * @return
     */
    public AbstractAction specimenPlaceHigh() {
        return new SequentialActionImpl(
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PLACE_HIGH); }),
                this.compBot.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_OPEN_POS, 1),
                new WaitAction(400),
                this.compBot.bigArm.viperSlide.gotoVoltageAction(Constants.VIPER_SLIDES_VOLTS_MIN, 0.75),
                new ParallelActionImpl(
                        this.compBot.bigArm.mainBoom.gotoPositionAction(Constants.SPECIMEN_PICK_READY.mainBoomPos.getPos(), 1, 300),
                        this.compBot.littleArm.doubleServos.gotoPositionAction(Constants.SPECIMEN_PICK_READY.doubleServosPos),
                        this.compBot.littleArm.middleServo.gotoPositionAction(Constants.SPECIMEN_PICK_READY.middleServoPos),
                        this.compBot.littleArm.clawRotator.gotoPositionAction(Constants.SPECIMEN_PICK_READY.clawRotatorPos)
                ),
                new InstantActionImpl(() -> { this.compBot.setArmPos(CompBot.ArmPos.SPECIMEN_PICK_READY); })
        );
    }
}