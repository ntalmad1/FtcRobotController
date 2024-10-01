package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.TankDrive;
import org.firstinspires.ftc.teamcode.roadrunner.tuning.TuningOpModes;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "RoadrunnerTest", group = "Tests")
//@Disabled
public class RoadrunnerTest extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d beginPose = new Pose2d(0, 0, 0);

        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        Action myAction = new InstantAction(() -> telemetry.speak("Jacob is gay!"));

        waitForStart();

        Actions.runBlocking(myAction);
        Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .lineToX(10)
                            .lineToX(0)
                            .build());

    }
}
