package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

/**
 *
 */
@Autonomous(name = "Right-Observation", group = "Auto")
//@Disabled
public class RightObsBot extends AutoBot {

    /**
     * Constructor
     *
     */
    public RightObsBot() {
        super();

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
        this.setInitialPose(new Pose2d(31.35, -61, Math.toRadians(90)));
    }

    @Override
    public void go() {
        super.go();

        Actions.runBlocking(new ParallelAction(
                this.getActionFactory().moveArmToSpecimenPlaceHighReady(),
                this.getTrajectoryFactory().splineToChamber(this.driveTrain.getDrive(), initialPose).build()
        ));

        Actions.runBlocking(new SequentialAction(
                new WaitAction(1000),
                this.getTrajectoryFactory().lineToPlaceSpeciman(this.driveTrain.getDrive()).build(),
                new WaitAction(500),
                this.arm.mainBoom.gotoPositionAction(new MotorPos(1354, 0.5)),
                new ParallelAction(
                    this.getActionFactory().specimenPlaceHigh(),
                    this.getTrajectoryFactory().lineBackAfterPlaceSpeciman(this.driveTrain.getDrive()).build()),
                new WaitAction(1000),
                this.claw.openClawAction(),
                new WaitAction(1000),
                new ParallelAction(
                    this.getActionFactory().moveArmToInitPos(),
                    new InstantAction(() -> this.claw.pincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS))),
                this.arm.viperSlide.gotoVoltageAction(this.getConfig().armConfig.viperSlideConfig.minVolts),
                this.getTrajectoryFactory().pushSamples(this.driveTrain.getDrive()).build()
        ));
    }

}
