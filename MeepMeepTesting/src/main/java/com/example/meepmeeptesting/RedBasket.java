package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;

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
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(56, -52, Math.toRadians(90)))


                        .lineToConstantHeading(new Vector2d(56, -22))

                        .lineToConstantHeading(new Vector2d(56,-18.6))

                        .splineToConstantHeading(new Vector2d(59, -10), Math.toRadians(0))

                        .splineToConstantHeading(new Vector2d(63, -14), Math.toRadians(270))

                        .lineToConstantHeading(new Vector2d(63,-48))

                        .splineToConstantHeading(new Vector2d(63,-52), Math.toRadians(90))




                        .splineToConstantHeading(new Vector2d(52, -48), Math.toRadians(180))
                        .splineToConstantHeading(new Vector2d(42, -56.8), Math.toRadians(270))


                        .waitSeconds(0.3)

                        .setTangent(Math.toRadians(140))
                        .splineToConstantHeading(new Vector2d(15,-60), Math.toRadians(180))
                        .splineToConstantHeading(new Vector2d(6,-36), Math.toRadians(90))


                        .setTangent(Math.toRadians(270))

                        .splineToConstantHeading(new Vector2d(20, -54), Math.toRadians(0))

                        .splineToConstantHeading(new Vector2d(42, -56.5), Math.toRadians(270))


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