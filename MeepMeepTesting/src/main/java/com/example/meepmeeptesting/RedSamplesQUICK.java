package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.io.IOException;

public class RedSamplesQUICK {
    public static void main(String[] args) throws IOException {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35, -61, Math.toRadians(90)))
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35.5, -61, Math.toRadians(90)))
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(8, -61, Math.toRadians(90)))

                        .lineTo(new Vector2d(8, -38))

                        .waitSeconds(0.6)

                        .lineToConstantHeading(new Vector2d(8, -48))// Go back away from bar

                        .waitSeconds(0.6)


                        /**
                        * first sample
                        */
                        .setTangent(Math.toRadians(0))
                        .splineToConstantHeading(new Vector2d(38, -44), Math.toRadians(90)) //Go towards sample

                        .splineToConstantHeading(new Vector2d(38, -18.6), Math.toRadians(90))

                        .splineToConstantHeading(new Vector2d(43, -12), Math.toRadians(0))

                        .splineToConstantHeading(new Vector2d(45.3, -14), Math.toRadians(270))

                        .lineToConstantHeading(new Vector2d(45.3, -52))


                        /**
                        * second sample
                        */
                        .lineToConstantHeading(new Vector2d(45.3, -18.6))

                        .splineToConstantHeading(new Vector2d(52, -12), Math.toRadians(0))
                        //.splineToConstantHeading(new Vector2d(52, -12), Math.toRadians(0))

                        .splineToConstantHeading(new Vector2d(56, -14), Math.toRadians(270))
                        //.splineToConstantHeading(new Vector2d(56, -14), Math.toRadians(270))

                        .lineToConstantHeading(new Vector2d(56, -52))
                        //.lineToYConstantHeading(-52)


                        /**
                         * Third sample
                         */
                        .lineToConstantHeading(new Vector2d(56, -18.6))

                        .splineToConstantHeading(new Vector2d(58, -12), Math.toRadians(0))
                        //.splineToConstantHeading(new Vector2d(52, -12), Math.toRadians(0))

                        .splineToConstantHeading(new Vector2d(61.4, -14), Math.toRadians(270))
                        //.splineToConstantHeading(new Vector2d(56, -14), Math.toRadians(270))

                        .lineToConstantHeading(new Vector2d(61.4, -52))
                        //.lineToYConstantHeading(-52)


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