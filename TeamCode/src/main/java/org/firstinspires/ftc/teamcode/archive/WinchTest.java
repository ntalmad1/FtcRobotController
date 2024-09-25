package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.archive.competition.config.WinchCompConfig;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.archive.library.winch.Winch;
import org.firstinspires.ftc.teamcode.archive.library.winch.WinchConfig;

@TeleOp(name="Winch Test", group="Linear OpMode")
@Disabled
public class WinchTest extends IsaacBot {

    //private Boom topBoom;
    private Winch winch;

    public WinchTest(){
        super();

//        ClawConfig clawConfig = new ClawCompConfig(this);

//        BoomConfig midBoomConfig = new BoomConfig();
//        midBoomConfig.robot = this;
//        midBoomConfig.isDualServo = true;
//        midBoomConfig.servoName = "middleLeftServo";
//        midBoomConfig.secondaryServoName = "middleRightServo";
//        midBoomConfig.direction = Servo.Direction.REVERSE;
//        midBoomConfig.controllerInputMethod = Control.Gp2_RightStickX;
//        midBoomConfig.invertInput = false;
//        midBoomConfig.maxIncrement = 0.00054;
//        midBoomConfig.degree = 0.000556;
//        midBoomConfig.zeroDegreePosition = 0.0;

//        BoomConfig bottomBoomConfig = new BoomConfig();
//        bottomBoomConfig.robot = this;
//        bottomBoomConfig.isDualServo = true;
//        bottomBoomConfig.servoName = "bottomLeftServo";
//        bottomBoomConfig.secondaryServoName = "bottomRightServo";
//        bottomBoomConfig.direction = Servo.Direction.FORWARD;
//        bottomBoomConfig.controllerInputMethod = Control.Gp2_LeftStickX;
//        bottomBoomConfig.invertInput = true;
//        bottomBoomConfig.maxIncrement = 0.00054;
//        bottomBoomConfig.zeroDegreePosition = 0.28;
//        bottomBoomConfig.degree = 0.000556;

        WinchConfig winchConfig = new WinchCompConfig(this);
//        armConfig.robot = this;
//        armConfig.clawConfig = clawConfig;
//        armConfig.bottomBoomConfig = bottomBoomConfig;
        winchConfig.debug = true;
        this.winch = new Winch(winchConfig);

        // pickup pixel routine
//        this.arm.addGp2_A_PressHandler(event -> {
//            ArmOpMode.this.arm.cancelAllCommands();
//
//            ArmOpMode.this.arm
//                    .moveBottomFromCurrentPosition(-10)
//                 //   .moveTopFromCurrentPosition(-15)
//                    .wait(0)
//
//                    .moveMiddle(-110, 0.005)
//                    .moveBottom(45, 0.001)
//                    .wait(0)
//
//                    .moveMiddle(-30, 0.005)
//                    .wait(0)
//
////                    .moveBottom(0, 0.001)
////                    .moveMiddle(10,0.005)
////                    .wait(0)
//            ;
//            //ArmOpMode.this.arm.moveBottom(0);
//
//        });

    }


    @Override
    public void runOpMode() throws InterruptedException {
        this.winch.init();


//            ArmOpMode.this.arm.cancelAllCommands();
//            ArmOpMode.this.arm
//                    .moveBottom(0)
//                    .moveMiddle(0)
//                    .moveTop(0)
//
//                    .wait(1000)
//
//                    .moveBottom(90)
//                    .moveMiddle(90)
//                    .moveTop(90)
//
//                    .wait(1000)
//
//                    .moveBottom(45)
//                    .moveMiddle(45)
//                    .moveTop(45)
//
//                    .wait(1000)
//
//                    .moveBottom(0)
//                    .moveMiddle(0)
//                    .moveTop(0);
//         });

        this.waitForStart();

        while (this.opModeIsActive()) {

            this.getEventBus().run();
            this.winch.run();




//            if (flag) {
//                sleep(2000);
//
//                CommandGroup commandGroup = new CommandGroup();
//                commandGroup.add(new GoToDegreesCommand(0));
//                commandGroup.add(new WaitCommand(2000));
//                commandGroup.add(new GoToDegreesCommand(90));
//
//
//                topBoom.addCommand(commandGroup);
//
//                flag = false;
//            }


        }
    }
}
