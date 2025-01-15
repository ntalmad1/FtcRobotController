package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoActionFactory;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToSpecimenHighReady;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToSpecimenPickReady;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToZero;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToSpecimenHighReady;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToZero;

/**
 *
 */
@Autonomous(name = "RightObsBot_NEW", group = "Auto")
//@Disabled
public class RightObsBot_NEW extends AutoBot {

    private AutoActionFactory autoActionFactory;

    /**
     * Constructor
     *
     */
    public RightObsBot_NEW() {
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
        this.telemetry.log().add("INIT DONE");

    }

    @Override
    public void go() {
        super.go();

        int initialHangExtraTicks = 8;
        int specimenCycleExtraTicks = 38;


        TrajectoryActionBuilder mainTrajectory = this.getDrive().actionBuilder(this.initialPose)


                .lineToY( -37)

                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(18,-50), 0)

                .splineToSplineHeading(new Pose2d(29.75, -33.77, Math.toRadians(34.3)), Math.toRadians(90))
                //.splineToLinearHeading(new Pose2d(29.75, -33.77, Math.toRadians(34.3)), Math.toRadians(90))


                .waitSeconds(0.8)
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(33, -33.5, Math.toRadians(-55.7)), Math.toRadians(-90))


                .waitSeconds(0.8)
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(35, -37.5, Math.toRadians(37.8)), Math.toRadians(0))


                .waitSeconds(0.8)
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(36.5, -34, Math.toRadians(-57)), Math.toRadians(-90))


                .waitSeconds(0.8)
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(37.3, -32, Math.toRadians(22.3)), Math.toRadians(0))

                .waitSeconds(0.8)
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(45.25, -40, Math.toRadians(90)), Math.toRadians(-90))

                .lineToY( -57.25)



                ;
        Actions.runBlocking(mainTrajectory.build());

    }

    /**
     *
     * @return
     */
    protected RightObsTrajectoryFactory getTrajectoryFactory () {
        return (RightObsTrajectoryFactory)super.getTrajectoryFactory();
    }



}
