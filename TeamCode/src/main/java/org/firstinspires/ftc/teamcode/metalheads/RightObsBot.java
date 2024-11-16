package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.action.InstantActionImpl;
import org.firstinspires.ftc.teamcode.library.action.SequentialActionImpl;
import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

/**
 *
 */
@TeleOp(name = "Right-Observation", group = "Auto")
//@Disabled
public class RightObsBot extends AutoBot {

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


        Actions.runBlocking(
            new SequentialAction(

                    // step one - place speciman 1 ready - drive it on
                    new ParallelAction(
                            this.getActionFactory().specimenPlaceHighReady(),
                            new SequentialAction(
                                    new WaitAction(800),
                                    // single line forward
                                    this.getTrajectoryFactory().stepOne_placeSpeciman(initialPose).build()
                            )
                    ),

                    // step two, three, & four
                    new SequentialAction(
                            new ParallelAction(
                                    this.getActionFactory().specimenPlaceHigh(),  // release and goto speciman ready
                                    new SequentialAction(
                                       new WaitAction(1000),
                                       this.getTrajectoryFactory().stepTwo_Three_releaseSpeciman_pushSamples().build()
                                    )
                            ),
                            this.getTrajectoryFactory().stepFour_arcToSpecimanPick().build()
                    )

                    // cycle speciman 1
//                    new SequentialAction(
//                            new WaitAction(1000),
//                            this.getActionFactory().specimenPick(),
//                            new ParallelAction(
//                                    this.getActionFactory().specimenPlaceHighReady(),
//                                      new SequentialAction(
//                                            new WaitAction(1000),
//                                    this.getTrajectoryFactory().splineToPlaceFirstSpeciman().build()
//        )
//                            ),
//                            this.getActionFactory().specimenPlaceHigh(), // let go and return to specimen ready
//                            this.getTrajectoryFactory().splineToSecondSpecimanPick().build()
//                    ),

                    // cycle speciman 2 & end
//                    new SequentialAction(
//                        new WaitAction(1000),
//                        this.getActionFactory().specimenPick(),
//                        new ParallelAction(
//                                this.getActionFactory().specimenPlaceHighReady(),
//                                new SequentialAction(
//                                        new WaitAction(1000),
//                                        this.getTrajectoryFactory().splineToPlaceSecondSpeciman().build()
//                                 )
//                        ),
//                        this.getActionFactory().specimenPlaceHigh(),
//                        new ParallelAction(
//                                this.getTrajectoryFactory().parkInObservationStepOne().build(),
//                                this.getActionFactory().initPos()
//                        ),
//                        this.getTrajectoryFactory().parkInObservationStepTwo().build()
//                    )
            )
        );
    }

    /**
     *
     * @return
     */
    protected RightObsTrajectoryFactory getTrajectoryFactory () {
        return (RightObsTrajectoryFactory)super.getTrajectoryFactory();
    }
}
