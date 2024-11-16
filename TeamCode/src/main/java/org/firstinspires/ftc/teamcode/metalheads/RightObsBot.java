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

        // step one - place speciman ready - drive it on
        Actions.runBlocking(
            new ParallelAction(
                this.getActionFactory().specimenPlaceHighReady(),
                new SequentialAction(
                    new WaitAction(800),
                    this.getTrajectoryFactory().stepOne_placeSpeciman(this.driveTrain.getDrive(), initialPose).build()
                )
            )
        );

        // step two, three
        Actions.runBlocking(
            new SequentialAction(
                new ParallelAction(
                    this.getActionFactory().specimenPlaceHigh(),
                    this.getTrajectoryFactory().releaseSpeciman(this.driveTrain.getDrive()).build()
                ),
                this.getTrajectoryFactory().stepThree_pushSamples(this.driveTrain.getDrive()).build()
            )
        );
//
//        Actions.runBlocking(new SequentialAction(
//                new WaitAction(1000),
//                this.getTrajectoryFactory().lineToPlaceSpeciman(this.driveTrain.getDrive()).build(),
//                new WaitAction(500),
//                this.arm.mainBoom.gotoPositionAction(new MotorPos(1354, 0.5)),
//                new ParallelAction(
//                    this.getActionFactory().specimenPlaceHigh(),
//                    this.getTrajectoryFactory().lineBackAfterPlaceSpeciman(this.driveTrain.getDrive()).build()),
//                new WaitAction(1000),
//                this.claw.openClawAction(),
//                new WaitAction(1000),
//                new ParallelAction(
//                    this.getActionFactory().moveArmToInitPos(),
//                    new InstantAction(() -> this.claw.pincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS))),
//                this.arm.viperSlide.gotoVoltageAction(this.getConfig().armConfig.viperSlideConfig.minVolts),
//                this.getTrajectoryFactory().pushSamples(this.driveTrain.getDrive()).build()
//        ));
    }

    /**
     *
     * @return
     */
    protected RightObsTrajectoryFactory getTrajectoryFactory () {
        return (RightObsTrajectoryFactory)super.getTrajectoryFactory();
    }
}
