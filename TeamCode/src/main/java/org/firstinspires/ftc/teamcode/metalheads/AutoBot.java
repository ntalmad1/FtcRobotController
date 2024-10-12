package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

@TeleOp(name = "RedObservation", group = "Red")
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

        this.setConfig(new AutoBotConfig(this));
        this.configureBot();

        this.trajectoryFactory = new TrajectoryFactory();

        //this.setInitialPose(new Pose2d(35.5, -61, Math.toRadians(90)));
        this.setInitialPose(new Pose2d(31.35, -61, Math.toRadians(90)));
    }

    @Override
    protected void configureBot() {
        super.configureBot();
    }

    @Override
    public void go() {
        super.go();

        Actions.runBlocking(new ParallelAction(
           this.getActionFactory().moveArmToSpecimenPlaceHighReady(),
           this.trajectoryFactory.splineToRedChamber(this.getRoadrunner(), initialPose).build()
        ));

        Actions.runBlocking(new SequentialAction(
            this.trajectoryFactory.lineToPlaceSpeciman(this.getRoadrunner()).build(),
            this.arm.mainBoom.gotoPositionAction(985)
        ));

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
//        public TrajectoryActionBuilder getTab1() {
//            TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
//                    .splineTo(new Vector2d(34, -32), Math.toRadians(90))
//                    .lineToY(-12)
//                    .strafeTo(new Vector2d(47.5, -12))
//                    .setTangent(Math.toRadians(-90))
//                    .lineToYConstantHeading(-50);
//            //.splineToConstantHeading(new Vector2d(47.5, -12), Math.toRadians(90))
//
//            return tab1;
//        }

        /**
         *
         * @param drive
         * @return
         */
        public TrajectoryActionBuilder lineToPlaceSpeciman(MecanumDrive drive) {
            return drive.actionBuilder(drive.pose)
                    .lineToY(-33.5, new TranslationalVelConstraint(10));
        }


        /**
         * Current Sample pusher
         * @return tab2
         */
        public TrajectoryActionBuilder splineToRedChamber(MecanumDrive drive, Pose2d initialPose) {
            double velocityLow = 25;

            TrajectoryActionBuilder tab2 = drive.actionBuilder(initialPose)
                    .strafeTo(new Vector2d(8, -40)); //Go infront of bar to hang specimen

                    //hang specimen
                    //.setTangent(Math.toRadians(0))
                    //.strafeTo(new Vector2d(26, -40)); //Go towards sample


                    /**
                     * First Specimen
                     */
//                    .splineToConstantHeading(new Vector2d(41, -12), Math.toRadians(0), //left side of arc
//                            new TranslationalVelConstraint(velocityLow))
//
//                    .splineToConstantHeading(new Vector2d(49, -30), Math.toRadians(270), //right side of arc
//                            new TranslationalVelConstraint(velocityLow))
//
//                    .lineToYConstantHeading(-50) //Go to Observation zone


                    /**
                     * Second Specimen
                     */
//                    .splineToConstantHeading(new Vector2d(51, -12), Math.toRadians(0), //left side of arc
//                            new TranslationalVelConstraint(velocityLow))
//
//                    .splineToConstantHeading(new Vector2d(58, -30), Math.toRadians(270), //right side of arc
//                            new TranslationalVelConstraint(velocityLow))
//
//                    .lineToYConstantHeading(-57.5) //Go to Observation zone


                    /**
                     * 3rd Specimen
                     */
//                    .lineToY(-21)
//                    .splineTo(new Vector2d(62, -12), Math.toRadians(0),
//                            new TranslationalVelConstraint(15))
//                    .waitSeconds(6)
//                    .strafeTo(new Vector2d(62, -54))
//                    .splineToConstantHeading(new Vector2d(27, -10), Math.toRadians(180),
//                            new TranslationalVelConstraint(velocityLow))
//                    ;

            return tab2;
        }

    }
}
