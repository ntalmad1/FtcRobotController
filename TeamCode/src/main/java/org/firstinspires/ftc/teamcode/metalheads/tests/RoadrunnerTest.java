package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.action.WaitAction;

@TeleOp(name = "RoadrunnerTest", group = "Tests")
//@Disabled
public class RoadrunnerTest extends IsaacBot {


    public RoadrunnerTest() {
        super();
    }


    public void initBot() {
        super.initBot();

        this.addGp1_A_PressHandler(event -> {
            telemetry.log().add("A");
        });

        this.addGp1_B_PressHandler(event -> {

            telemetry.log().add("B");

            Action myAction = new InstantAction(() -> telemetry.log().add("Hello World"));

            RoadrunnerTest.this.runAction(myAction);

//            RoadrunnerTest.this.runAction(new ParallelAction(
//                        new InstantAction(() -> Actions.runBlocking(new SequentialAction(myAction,
//                        new WaitAction(4000),
//                        new InstantAction(() -> telemetry.log().add("Done")))))));
        });

    }

    public void go() {
//        Action myAction = new InstantAction(() -> telemetry.log().add("Hello World"));
//
//        Actions.runBlocking(
//                );

//        Actions.runBlocking(
//                new ParallelAction(
//                        );
    }

//    public void run() {
//        super.run();
//    }


//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        Pose2d beginPose = new Pose2d(0, 0, 0);
//
//        //MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
//
//        Action myAction = new InstantAction(() -> telemetry.log().add("Hello World"));
//
//        waitForStart();
//
//        Actions.runBlocking(
//                new ParallelAction(
//                        myAction,
//                        new WaitAction(2000),
//                        new InstantAction(() -> telemetry.log().add("Done"))));
//
//        telemetry.log().add("my event");
//
//        Actions.runBlocking(new InstantAction(() -> telemetry.log().add("HERE!")));
//
//
//        while (opModeIsActive()){
//            // wait for user input to stop
//        }
//
////        Actions.runBlocking(
////                    drive.actionBuilder(beginPose)
////                            .lineToX(10)
////                            .lineToX(0)
////                            .build());
//
//    }
}
