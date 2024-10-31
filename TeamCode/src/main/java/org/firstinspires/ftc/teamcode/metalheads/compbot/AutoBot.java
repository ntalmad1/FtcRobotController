package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

public class AutoBot extends CompBot {

    /**
     */
    private TrajectoryFactory trajectoryFactory;

    /**
     * Constructor
     *
     */
    public AutoBot() {
        super();

        this.trajectoryFactory = new TrajectoryFactory();

        //this.setInitialPose(new Pose2d(35.5, -61, Math.toRadians(90)));
        //this.setInitialPose(new Pose2d(31.35, -61, Math.toRadians(90)));
    }

    /**
     *
     */
    @Override
    protected void configureBot() {
        super.configureBot();
    }

    /**
     *
     * @return
     */
    protected TrajectoryFactory getTrajectoryFactory() {
        return this.trajectoryFactory;
    }

    /**
     * Private class
     *
     */
    public static class TrajectoryFactory {

        /**
         *
         * @param drive
         * @return
         */
        public TrajectoryActionBuilder lineToPlaceSpeciman(MecanumDrive drive) {
            return drive.actionBuilder(drive.pose)
                    .lineToY(-33.5, new TranslationalVelConstraint(10));
        }

        public TrajectoryActionBuilder lineBackAfterPlaceSpeciman(MecanumDrive drive) {
            return drive.actionBuilder(drive.pose)
                    .lineToY(-43.0, new TranslationalVelConstraint(25));
        }

        /**
         * Current Sample pusher
         * @return tab2
         */
        public TrajectoryActionBuilder splineToChamber(MecanumDrive drive, Pose2d initialPose) {
            return drive.actionBuilder(initialPose)
                    .strafeTo(new Vector2d(5, -40));
        }

        /**
         *
         * @param drive
         * @return
         */
        public TrajectoryActionBuilder pushSamples(MecanumDrive drive) {

            double velocityLow = 25;

            return drive.actionBuilder(drive.pose)
                .setTangent(Math.toRadians(0))
                .strafeTo(new Vector2d(26, -46)) //Go towards sample

                /*
                 * First Specimen
                 */
                .splineToConstantHeading(new Vector2d(41, -12), Math.toRadians(0), //left side of arc
                        new TranslationalVelConstraint(velocityLow))

                .splineToConstantHeading(new Vector2d(49, -30), Math.toRadians(270), //right side of arc
                        new TranslationalVelConstraint(velocityLow))

                .lineToYConstantHeading(-50) //Go to Observation zone


                /*
                 * Second Specimen
                 */
                .splineToConstantHeading(new Vector2d(51, -12), Math.toRadians(0), //left side of arc
                        new TranslationalVelConstraint(velocityLow))

                .splineToConstantHeading(new Vector2d(58, -30), Math.toRadians(270), //right side of arc
                        new TranslationalVelConstraint(velocityLow))

                .lineToYConstantHeading(-57.5) //Go to Observation zone


                /*
                 * 3rd Specimen
                 */
                .lineToY(-21)
                .splineTo(new Vector2d(58.8, -12), Math.toRadians(0),
                        new TranslationalVelConstraint(15))
                .strafeTo(new Vector2d(58.8, -54),
                        new TranslationalVelConstraint(velocityLow))
                    .strafeToLinearHeading(new Vector2d(53,-54), Math.toRadians(90));

        }
    }
}
