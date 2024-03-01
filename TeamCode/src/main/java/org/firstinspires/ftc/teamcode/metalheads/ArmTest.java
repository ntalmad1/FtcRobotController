package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.component.event.gp2_dpad_down_down.Gp2_Dpad_Down_DownEvent;
import org.firstinspires.ftc.library.component.event.gp2_dpad_down_down.Gp2_Dpad_Down_DownHandler;
import org.firstinspires.ftc.library.component.event.gp2_dpad_up_down.Gp2_Dpad_Up_DownEvent;
import org.firstinspires.ftc.library.component.event.gp2_dpad_up_down.Gp2_Dpad_Up_DownHandler;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.ArmCompConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.ClawCompConfig;
import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.boom.arm.Arm;
import org.firstinspires.ftc.library.boom.arm.ArmConfig;
import org.firstinspires.ftc.library.claw.ClawConfig;

@TeleOp(name="Arm Test", group="Linear OpMode")
//@Disabled
public class ArmTest extends IsaacBot {

    //private Boom topBoom;
    private Arm arm;

    public ArmTest(){
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

        ArmConfig armConfig = new ArmCompConfig(this);
//        armConfig.robot = this;
//        armConfig.clawConfig = clawConfig;
//        armConfig.bottomBoomConfig = bottomBoomConfig;
        armConfig.debug = true;
        this.arm = new Arm(armConfig);

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
        this.arm.init();

        this.addGp2_Dpad_Down_DownHandler(new Gp2_Dpad_Down_DownHandler() {
            @Override
            public void onGp2_Dpad_Down_Down(Gp2_Dpad_Down_DownEvent event) {
                double increment = ArmTest.this.arm.getBottomBoomIncrement();
                increment = increment - 0.005;
                ArmTest.this.arm.setBottomBoomIncrement(increment);
            }
        });

        this.addGp2_Dpad_Up_DownHandler(new Gp2_Dpad_Up_DownHandler() {
            @Override
            public void onGp2_Dpad_Up_Down(Gp2_Dpad_Up_DownEvent event) {
                double increment = ArmTest.this.arm.getBottomBoomIncrement();
                increment = increment + 0.005;
                ArmTest.this.arm.setBottomBoomIncrement(increment);
            }
        });


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
            this.arm.run();




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
