package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.io.IOException;

public class RedBasket {
    public static void main(String[] args) throws IOException {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35, -61, Math.toRadians(90)))
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35.5, -61, Math.toRadians(90)))
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-31.35, -61, Math.toRadians(90)))

                        //hang specimen
                        .lineToConstantHeading(new Vector2d(-8,-45)) //go in front of bar
                        .lineTo(new Vector2d(-8,-38)) //go forwards to hang specimen



                        //Go to first sample
                        .setTangent(-90)
                        .splineToConstantHeading(new Vector2d(-43, -48), Math.toRadians(180))
                        .strafeTo(new Vector2d(-50, -48))
                        // TODO: Pickup Sample 1

                        //GoToBucket
                        .lineToSplineHeading(new Pose2d(-55, -55, Math.toRadians(225)))
                        // TODO: Drop Sample

                        //Go To Sample 2
                        .setTangent(Math.toRadians(0))
                        .lineToSplineHeading(new Pose2d(-55, -48, Math.toRadians(90)))
                        .strafeTo(new Vector2d(-61,-48))
                        // TODO: Pickup Sample 2

                        //GoToBucket
                        .lineToSplineHeading(new Pose2d(-55, -55, Math.toRadians(225)))
                        // TODO: Drop Sample

                        //Go To Sample 3
                        .splineToLinearHeading(new Pose2d(-44, -24, Math.toRadians(180)), Math.toRadians(180))
                        // TODO: Pickup Sample 3

                        //GoToBucket
                        .lineToSplineHeading(new Pose2d(-55, -55, Math.toRadians(225)))
                        // TODO: Drop Sample

                        //Park
                        .setTangent(90)
                        .splineToLinearHeading(new Pose2d(-27, -8.5, Math.toRadians(0)), Math.toRadians(0))

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