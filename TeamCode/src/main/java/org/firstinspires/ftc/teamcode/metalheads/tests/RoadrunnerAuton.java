package org.firstinspires.ftc.teamcode.metalheads.tests;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

import java.util.Queue;

@Config
@Autonomous(name = "Roadrunner_auton", group = "Autonomous")
public class RoadrunnerAuton extends LinearOpMode {

    /**
     */
    private TrajectoryFactory trajectoryFactory;

    private Pose2d initialPose;

    private MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        // instantiate your MecanumDrive at a particular pose.
        initialPose = new Pose2d(10.3, -61, Math.toRadians(90));
        drive = new MecanumDrive(hardwareMap, initialPose);

        this.trajectoryFactory = new TrajectoryFactory();

        waitForStart();

        Actions.runBlocking(trajectoryFactory.getTab1().build());

//        Actions.runBlocking(
//                    drive.actionBuilder(initialPose)
//                            .splineTo(new Vector2d(34, -32), Math.toRadians(90))
//                            .lineToY(-12)
//                            .strafeTo(new Vector2d(47.5, -12))
//                            //.splineToConstantHeading(new Vector2d(47.5, -12), Math.toRadians(90))
//                            .build());

    }

    /**
     * Private class
     *
     */
    private class TrajectoryFactory {

        public TrajectoryActionBuilder getTab1() {
            TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                    .splineTo(new Vector2d(34, -32), Math.toRadians(90))
                    .lineToY(-12)
                    .strafeTo(new Vector2d(47.5, -12))
                    .setTangent(Math.toRadians(-90))
                    .lineToYConstantHeading(-50);
            //.splineToConstantHeading(new Vector2d(47.5, -12), Math.toRadians(90))

            return tab1;
        }

    }

}
