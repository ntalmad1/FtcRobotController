package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.io.IOException;

public class RedSamples {
    public static void main(String[] args) throws IOException {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35, -61, Math.toRadians(90)))
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35.5, -61, Math.toRadians(90)))
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(8, -61, Math.toRadians(90)))

                        .lineTo(new Vector2d(8, -36))
                        //.lineToY( -39)

                        //hang specimen
                        .setTangent(Math.toRadians(270))
                        .splineToConstantHeading(new Vector2d(24, -44), Math.toRadians(0)) //Go towards sample
                        //.splineToConstantHeading(new Vector2d(24, -44), Math.toRadians(0))

                        /**
                         * first sample
                         */
                        .splineToConstantHeading(new Vector2d(36, -18.6), Math.toRadians(90))
                        //.splineToConstantHeading(new Vector2d(36, -18.6), Math.toRadians(0))

                        .splineToConstantHeading(new Vector2d(43, -12), Math.toRadians(0))
                        //.splineToConstantHeading(new Vector2d(43, -7), Math.toRadians(0))

                        .splineToConstantHeading(new Vector2d(45.3, -14), Math.toRadians(270))
                        //.splineToConstantHeading(new Vector2d(45.3, -14), Math.toRadians(270))

                        .lineToConstantHeading(new Vector2d(45.3, -52))
                        //.lineToYConstantHeading(-52) //Go to Observation zone


                          /**
                          * second sample
                          */
//                        .splineToConstantHeading(new Vector2d(51, -12), Math.toRadians(0)) //left side of arc
//                        .splineToConstantHeading(new Vector2d(58, -30), Math.toRadians(270)) //right side
//                        .lineToConstantHeading(new Vector2d(58, -57.5))
//
//                        //grab first specimen
//                        .waitSeconds(0.1)
//                        .setTangent(Math.toRadians(90))
//                        .splineToConstantHeading(new Vector2d(48, -47), Math.toRadians(180))
//                        .splineToConstantHeading(new Vector2d(40, -54), Math.toRadians(270))
//                        .lineTo(new Vector2d(40,-57))
//                        //TODO: Grab Specimen
//
//                        /**
//                         * hang specimen
//                         */
//                         //go to bar
//                         .strafeTo(new Vector2d(6,-50))
//                         //go forwards to hang specimen
//                         .lineTo(new Vector2d(6,-36))

                        .build());





        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

//        try {
//
//            File pathToFile = new File("resources/intothedeep_rotated.png");
//            Image image = ImageIO.read(pathToFile);
//
//            meepMeep.setBackground(image)
//                    .setDarkMode(true)
//                    .setBackgroundAlpha(0.95f)
//                    .addEntity(myBot)
//                    .start();
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

    }
}