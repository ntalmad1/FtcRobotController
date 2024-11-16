package org.firstinspires.ftc.teamcode.metalheads.tests;

// RR-specific imports

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

@Config
@Autonomous(name = "RoadrunnerSampleTester", group = "Autonomous")
//@Disabled
public class SampleAutonTest extends LinearOpMode {

    /**
     */
    private TrajectoryFactory trajectoryFactory;

    private Pose2d initialPose;

    private MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        // instantiate your MecanumDrive at a particular pose.
        initialPose = new Pose2d(8, -61, Math.toRadians(90));

        drive = new MecanumDrive(hardwareMap, initialPose);

        this.trajectoryFactory = new TrajectoryFactory();

        waitForStart();

        Actions.runBlocking(trajectoryFactory.getTab2().build());

    }

    /**
     * Private class
     *
     */
    private class TrajectoryFactory {

        /**
         * Current Sample pusher
         * @return tab2
         */
        public TrajectoryActionBuilder getTab2() {
            double velocityLow = 25;

            TrajectoryActionBuilder tab2 = drive.actionBuilder(initialPose)
                    .lineToY( -39) //Go infront of bar to hang specimen


                    /**
                     * First Sample
                     */
                    .setTangent(Math.toRadians(270))

                    .splineToConstantHeading(new Vector2d(24, -44), Math.toRadians(0))//away + right of bar

                    .splineToConstantHeading(new Vector2d(36, -18.6), Math.toRadians(0))// up torwards sample

                    .splineToConstantHeading(new Vector2d(43, -12), Math.toRadians(0))//arc onto sample (left)

                    .splineToConstantHeading(new Vector2d(45.3, -14), Math.toRadians(270))//arc onto sample (right)

                    .lineToYConstantHeading(-52) //Go to Observation zone


//                    /**
//                     * Second Sample
//                     */
//                    .splineToConstantHeading(new Vector2d(51, -12), Math.toRadians(0))
//
//                    .splineToConstantHeading(new Vector2d(58, -30), Math.toRadians(270))
//
//                    .lineToYConstantHeading(-57.5) //Go to Observation zone
//
//
//                    /**
//                     * Grab Specimen 2
//                     */
//                    .setTangent(Math.toRadians(90))
//                    .splineToConstantHeading(new Vector2d(48,-47), Math.toRadians(180))
//                    .splineToConstantHeading(new Vector2d(40, -54), Math.toRadians(270))
//                    .lineToY(-57, new TranslationalVelConstraint(velocityLow))
//                    .waitSeconds(0.5)
//                    //TODO: Grab Specimen
//
//                    /**
//                     * hang specimen
//                     */
//                    //go to bar
//                    .strafeTo(new Vector2d(7,-40))
//                    //go forwards to hang specimen
//                    .lineToY(-36)
//
//                    /**
//                     * grab specimen 3
//                     */
//                    .strafeTo(new Vector2d(40,-54))
//                    .lineToY(-57, new TranslationalVelConstraint(velocityLow))
//                    .waitSeconds(0.5)
//
//                    /**
//                     * hang specimen
//                     */
//                     //go to bar
//                     .strafeTo(new Vector2d(7,-40))
//                     //go forwards to hang specimen
//                     .lineToY(-36)
//
//                     /**
//                     * go back to observation zone
//                     */
//                     .strafeTo(new Vector2d(44,-55))


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

                    //hang specimen
                    .strafeToConstantHeading(new Vector2d(-8,-45)) //go in front of bar
                    .lineToY(-38,
                            new TranslationalVelConstraint(extraLowV)) //go forwards to hang specimen



                    //Go to first sample
                    .setTangent(-90)
                    .splineToConstantHeading(new Vector2d(-43, -48), Math.toRadians(180),
                            new TranslationalVelConstraint(lowV))
                    .strafeTo(new Vector2d(-50, -48),
                            new TranslationalVelConstraint(extraLowV)) //strafe into sample
                    // TODO: Pickup Sample 1

                    //GoToBucket
                    .setTangent(-90)
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
