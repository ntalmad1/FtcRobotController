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
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
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
    private Pose2d redSamplePose;
    private Pose2d redBucketPose;

    private MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        // instantiate your MecanumDrive at a particular pose.
        //initialPose = new Pose2d(10.3, -61, Math.toRadians(90)); //Inner Tile
        redSamplePose = new Pose2d(35.5, -61, Math.toRadians(90));
        redBucketPose = new Pose2d(-35.5, -61, Math.toRadians(90));

        initialPose = redBucketPose;  //inside edge of tile
        drive = new MecanumDrive(hardwareMap, initialPose);

        this.trajectoryFactory = new TrajectoryFactory();

        waitForStart();

        Actions.runBlocking(trajectoryFactory.getTab3().build());

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

        /**
         * Original test for sample pusher
         * @return tab1
         */
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

        /**
         * Current Sample pusher
         * @return tab2
         */
        public TrajectoryActionBuilder getTab2() {
            double velocityLow = 25;

            TrajectoryActionBuilder tab2 = drive.actionBuilder(initialPose)
                    .strafeTo(new Vector2d(8, -40)) //Go infront of bar to hang specimen

                    //hang specimen
                    .setTangent(Math.toRadians(0))
                    .strafeTo(new Vector2d(26, -40)) //Go towards sample


                    /**
                     * First Specimen
                     */
                    .splineToConstantHeading(new Vector2d(41, -12), Math.toRadians(0), //left side of arc
                        new TranslationalVelConstraint(velocityLow))

                    .splineToConstantHeading(new Vector2d(49, -30), Math.toRadians(270), //right side of arc
                            new TranslationalVelConstraint(velocityLow))

                    .lineToYConstantHeading(-50) //Go to Observation zone


                    /**
                     * Second Specimen
                     */
                    .splineToConstantHeading(new Vector2d(51, -12), Math.toRadians(0), //left side of arc
                            new TranslationalVelConstraint(velocityLow))

                    .splineToConstantHeading(new Vector2d(58, -30), Math.toRadians(270), //right side of arc
                            new TranslationalVelConstraint(velocityLow))

                    .lineToYConstantHeading(-57.5) //Go to Observation zone


                    /**
                     * 3rd Specimen
                     */
                    .lineToY(-21)
                    .splineTo(new Vector2d(62, -12), Math.toRadians(0),
                            new TranslationalVelConstraint(15))
                    .waitSeconds(6)
                    .strafeTo(new Vector2d(62, -55))
                    ;

            return tab2;
        }


        /**
         * Basket Auton Positions
         * @return Tab3
         */
        public TrajectoryActionBuilder getTab3() {
            double lowV = 20;
            double extraLowV = 8;

            TrajectoryActionBuilder tab3 = drive.actionBuilder(initialPose)
                    //Go to first sample
                    .setTangent(0)
                    .strafeToLinearHeading(new Vector2d(-43, -48), Math.toRadians(90),
                            new TranslationalVelConstraint(lowV))
                    .strafeTo(new Vector2d(-50, -48),
                            new TranslationalVelConstraint(extraLowV))
                    // TODO: Pickup Sample 1

                    //GoToBucket
                    .strafeToLinearHeading(new Vector2d(-55,-55), Math.toRadians(225),
                            new TranslationalVelConstraint(lowV))
                    // TODO: Drop Sample

                    //Go To Sample 2
                    .strafeToLinearHeading(new Vector2d(-55, -48), Math.toRadians(90),
                            new TranslationalVelConstraint(lowV))
                    .strafeTo(new Vector2d(-61, -48),
                            new TranslationalVelConstraint(extraLowV))
                    // TODO: Pickup Sample 2

                    //GoToBucket
                    .strafeToLinearHeading(new Vector2d(-55,-55), Math.toRadians(225),
                            new TranslationalVelConstraint(lowV))
                    // TODO: Drop Sample

                    //GoBackToNextSample
                    .splineToLinearHeading(new Pose2d(-44, -24, Math.toRadians(180)), Math.toRadians(180),
                            new TranslationalVelConstraint(lowV))
                    // TODO: Pickup Sample 3

                    //GoToBucket
                    .strafeToLinearHeading(new Vector2d(-55,-55), Math.toRadians(225),
                            new TranslationalVelConstraint(lowV))
                    // TODO: Drop Sample

                    //Park
                    .setTangent(90)
                    .splineToLinearHeading(new Pose2d(-27, -10, Math.toRadians(0)), Math.toRadians(0),
                            new TranslationalVelConstraint(lowV));

            return tab3;
        }

    }

}
