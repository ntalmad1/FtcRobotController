package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.io.IOException;

public class TangentExample {
    public static void main(String[] args) throws IOException {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35, -61, Math.toRadians(90)))
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))


                        //CounterClockwise Circle
                        .setTangent(0)
                        .splineToConstantHeading(new Vector2d(7.5, 7.5), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(0, 15), Math.toRadians(180))
                        .splineToConstantHeading(new Vector2d(-7.5, 7.5), Math.toRadians(270))
                        .splineToConstantHeading(new Vector2d(0, 0), 0)

                        //Clockwise Circle
                        .setTangent(Math.toRadians(180))
                        .splineToConstantHeading(new Vector2d(-30, 30), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(0, 60), Math.toRadians(0))
                        .splineToConstantHeading(new Vector2d(30, 30), Math.toRadians(270))
                        .splineToConstantHeading(new Vector2d(0, 0), Math.toRadians(180))


                        //.splineToConstantHeading(new Vector2d(45,-60), Math.toRadians(90))
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