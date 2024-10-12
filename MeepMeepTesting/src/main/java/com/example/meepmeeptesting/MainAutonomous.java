package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.io.IOException;

public class MainAutonomous {
    public static void main(String[] args) throws IOException {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35, -61, Math.toRadians(90)))
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35.5, -61, Math.toRadians(90)))
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35.5, -61, Math.toRadians(180)))
                        .strafeTo(new Vector2d(8, -40)) //Go infront of bar to hang specimen

                        //hang specimen
                        .setTangent(Math.toRadians(0))
                        .lineTo(new Vector2d(26, -40)) //Go towards sample
                        //first specimen
                        .splineToConstantHeading(new Vector2d(41, -12), Math.toRadians(0)) //left side of arc
                        .splineToConstantHeading(new Vector2d(46, -30), Math.toRadians(270)) //right side
                        .lineToConstantHeading(new Vector2d(46, -60))
                        //second specimen
                        .splineToConstantHeading(new Vector2d(49, -12), Math.toRadians(0)) //left side of arc
                        .splineToConstantHeading(new Vector2d(56.5, -30), Math.toRadians(270)) //right side
                        .lineToConstantHeading(new Vector2d(56.5, -60))

                        //3rd specimem pickup method currently not certain

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