package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;

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

                        .lineTo(new Vector2d(8, -37))
                        //.lineToY( -39)



                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(18,-50), 0)

                        //.splineToSplineHeading(new Pose2d(29.75, -33.77, Math.toRadians(34.3)), Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(29.75, -33.77, Math.toRadians(34.3)), Math.toRadians(90))


                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(33, -33.5, Math.toRadians(-55.7)), Math.toRadians(-90))


                        .setTangent(0)
                        .splineToLinearHeading(new Pose2d(35, -37.5, Math.toRadians(37.8)), Math.toRadians(0))


                        .setTangent(Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(36.5, -34, Math.toRadians(-57)), Math.toRadians(-90))


                        .setTangent(0)
                        .splineToLinearHeading(new Pose2d(37.3, -32, Math.toRadians(22.3)), Math.toRadians(0))






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