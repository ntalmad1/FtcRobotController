package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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
@TeleOp(name = "Right-Observation", group = "Auto")
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

        AbstractAction action = new SequentialActionImpl(
                this.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_CLOSE_POS, 1),
                new WaitAction(300),
                this.bigArm.mainBoom.gotoPositionAction(525),
                new InstantActionImpl(() -> { this.setArmPos(ArmPos.INIT); })
        );

        TelemetryPacket tp = new TelemetryPacket();
        while (action.run(tp) == AbstractAction.CONTIUE){}
    }

    @Override
    public void go() {
        super.go();

        TrajectoryActionBuilder trajectory = this.getDrive().actionBuilder(this.initialPose)
                .afterTime(0, this.getActionFactory().specimenPlaceHighReady())
                .waitSeconds(0.8)
                .lineToY(-38)
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
                .splineToConstantHeading(new Vector2d(38, -44), Math.toRadians(90)) //Go towards sample

                .splineToConstantHeading(new Vector2d(38, -18.6), Math.toRadians(90))

                .splineToConstantHeading(new Vector2d(43, -12), Math.toRadians(0))

                .splineToConstantHeading(new Vector2d(45.3, -14), Math.toRadians(270))

                .lineToYConstantHeading(-52)


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

                .splineToConstantHeading(new Vector2d(58, -12), Math.toRadians(0))
                //.splineToConstantHeading(new Vector2d(52, -12), Math.toRadians(0))

                .splineToConstantHeading(new Vector2d(61.4, -14), Math.toRadians(270))
                //.splineToConstantHeading(new Vector2d(56, -14), Math.toRadians(270))

                .lineToYConstantHeading(-52);
        //.lineToYConstantHeading(-52)

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
