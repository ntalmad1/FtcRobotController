package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.action.InstantActionImpl;
import org.firstinspires.ftc.teamcode.library.action.ParallelActionImpl;
import org.firstinspires.ftc.teamcode.library.action.SequentialActionImpl;
import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.metalheads.compbot.ActionFactory;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoActionFactory;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.CompBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

/**
 *
 */
@Autonomous(name = "Right-Observation", group = "Auto")
//@Disabled
public class RightObsBot extends AutoBot {

    private AutoActionFactory autoActionFactory;

    /**
     * Constructor
     *
     */
    public RightObsBot() {
        super();

        this.setTrajectoryFactory(new RightObsTrajectoryFactory(this));

        this.setConfig(new RightObsBotConfig(this));
        this.configureBot();
    }

    /**
     *
     */
    @Override
    protected void configureBot() {
        super.configureBot();

        this.autoActionFactory = new AutoActionFactory(this);

        // initialize roadrunner from last op pose
        this.setInitialPose(new Pose2d(8, -61, Math.toRadians(90)));
    }

    /**
     *
     */
    @Override
    public void initBot() {
        super.initBot();

        this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
        this.bigArm.mainBoom.setTargetPosition(525);
        this.bigArm.mainBoom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.bigArm.mainBoom.setPower(1);
        this.setArmPos(ArmPos.INIT);
        this.telemetry.log().add("INIT DONE");

    }

    @Override
    public void go() {
        super.go();

        TrajectoryActionBuilder trajectory = this.getDrive().actionBuilder(this.initialPose)
                .stopAndAdd(() -> {
                    this.bigArm.mainBoom.setTargetPosition(Constants.SPECIMEN_PLACE_HIGH_READY.mainBoomPos.getPos());
                    this.bigArm.mainBoom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    this.bigArm.mainBoom.setPower(1);
                }) //this.bigArm.mainBoom.gotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.mainBoomPos))
                .stopAndAdd(() -> {
                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos.getPos());
                })
                .waitSeconds(0.25)
                .stopAndAdd(() -> this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos.getPos()))
                .stopAndAdd(this.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.SPECIMEN_PLACE_HIGH_READY.vSlidePos))

                .waitSeconds(0.8)
                .lineToY(-37,
                        new TranslationalVelConstraint(20),
                        new ProfileAccelConstraint(-14, 14))
                .stopAndAdd(this.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_OPEN_POS, 1))
                .waitSeconds(0.5)
                .lineToYConstantHeading(-48)// Go back away from bar
                .stopAndAdd(this.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.VIPER_SLIDES_MIN_TICS))
                .waitSeconds(0.8)
                .stopAndAdd(() -> {
                    this.littleArm.doubleServos.setPosition(this.littleArm.doubleServos.getConfig().homePosition);
                    this.littleArm.middleServo.setPosition(this.littleArm.middleServo.getConfig().homePosition);
                    this.littleArm.clawRotator.setPosition(this.littleArm.clawRotator.getConfig().homePosition);
                })
                .waitSeconds(1)
                .stopAndAdd(this.bigArm.mainBoom.gotoPositionAction(0, 1, 300))

                /*
                 * first sample
                 */
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(35, -44), Math.toRadians(90)) //Go towards sample

                .splineToConstantHeading(new Vector2d(35, -18.6), Math.toRadians(90))

                .splineToConstantHeading(new Vector2d(43, -12), Math.toRadians(0),
                        new TranslationalVelConstraint(20),
                        new ProfileAccelConstraint(-15, 15))

                .splineToConstantHeading(new Vector2d(45.3, -14), Math.toRadians(270),
                        new TranslationalVelConstraint(20),
                        new ProfileAccelConstraint(-15, 15))

                .lineToYConstantHeading(-52,
                        null,
                        new ProfileAccelConstraint(-20, 20))


                /*
                 * second sample
                 */
                .lineToYConstantHeading(-18.6)

                .splineToConstantHeading(new Vector2d(52, -12), Math.toRadians(0))
                //.splineToConstantHeading(new Vector2d(52, -12), Math.toRadians(0))

                .splineToConstantHeading(new Vector2d(56, -14), Math.toRadians(270))
                //.splineToConstantHeading(new Vector2d(56, -14), Math.toRadians(270))

                .lineToYConstantHeading(-52)
                //.lineToYConstantHeading(-52)


                /*
                 * Third sample
                 */
                .lineToYConstantHeading(-18.6)

                .splineToConstantHeading(new Vector2d(58, -12), Math.toRadians(0),
                        new TranslationalVelConstraint(20),
                        new ProfileAccelConstraint(-15, 15))

                .splineToConstantHeading(new Vector2d(61.7, -14), Math.toRadians(270),
                        new TranslationalVelConstraint(20),
                        new ProfileAccelConstraint(-15, 15))

                .lineToYConstantHeading(-52,
                        new TranslationalVelConstraint(20),
                        new ProfileAccelConstraint(-15, 15))
                .stopAndAdd(() -> {
                    this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);

                    this.bigArm.mainBoom.setTargetPosition(0);
                    this.bigArm.mainBoom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    this.bigArm.mainBoom.setPower(1);

                    this.bigArm.viperSlide.setTargetPosition(0);
                    this.bigArm.viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    this.bigArm.viperSlide.setPower(1);
                });


            Actions.runBlocking(trajectory.build());






//        Actions.runBlocking(
//            new SequentialAction(
//
//                    // step one - place speciman 1 ready - drive it on
//                    new ParallelAction(
//                            this.getAutoActionFactory().specimenPlaceHighReady(),
//                            new SequentialAction(
//                                    new WaitAction(800),
//                                    // single line forward
//                                    this.getTrajectoryFactory().stepOne_placeSpeciman(initialPose).build()
//                            )
//                    ),
//
//                    // step two, three, & four
//                    new SequentialAction(
//                            this.getAutoActionFactory().specimenPlaceHigh(),  // release and goto speciman ready
//                            new WaitAction(1000),
//                            this.getTrajectoryFactory().stepTwo_Three_releaseSpeciman_pushSamples().build(),
//                            this.getTrajectoryFactory().stepFour_arcToSpecimanPick().build()
//                    )
//
//                    // cycle speciman 1
////                    new SequentialAction(
////                            new WaitAction(1000),
////                            this.getAutoActionFactory().specimenPick(),
////                            new ParallelAction(
////                                    this.getAutoActionFactory().specimenPlaceHighReady(),
////                                      new SequentialAction(
////                                            new WaitAction(1000),
////                                    this.getTrajectoryFactory().splineToPlaceFirstSpeciman().build()
////        )
////                            ),
////                            this.getAutoActionFactory().specimenPlaceHigh(), // let go and return to specimen ready
////                            this.getTrajectoryFactory().splineToSecondSpecimanPick().build()
////                    ),
//
//                    // cycle speciman 2 & end
////                    new SequentialAction(
////                        new WaitAction(1000),
////                        this.getAutoActionFactory().specimenPick(),
////                        new ParallelAction(
////                                this.getAutoActionFactory().specimenPlaceHighReady(),
////                                new SequentialAction(
////                                        new WaitAction(1000),
////                                        this.getTrajectoryFactory().splineToPlaceSecondSpeciman().build()
////                                 )
////                        ),
////                        this.getAutoActionFactory().specimenPlaceHigh(),
////                        new ParallelAction(
////                                this.getTrajectoryFactory().parkInObservationStepOne().build(),
////                                this.getAutoActionFactory().initPos()
////                        ),
////                        this.getTrajectoryFactory().parkInObservationStepTwo().build()
////                    )
//            )
//        );
    }

    /**
     *
     * @return
     */
    protected RightObsTrajectoryFactory getTrajectoryFactory () {
        return (RightObsTrajectoryFactory)super.getTrajectoryFactory();
    }

    /**
     *
     * @return
     */
    public AutoActionFactory getAutoActionFactory () {
        return this.autoActionFactory;
    }
}
