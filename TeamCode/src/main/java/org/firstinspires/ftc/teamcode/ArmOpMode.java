package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.Control;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.arm.Arm;
import org.firstinspires.ftc.teamcode.library.arm.ArmConfiguration;
import org.firstinspires.ftc.teamcode.library.boom.BoomConfiguration;

@TeleOp(name="ArmOpMode", group="Linear OpMode")
//@Disabled
public class ArmOpMode extends IsaacBot {

    //private Boom topBoom;
    private Arm arm;

    public ArmOpMode(){
        super();

        BoomConfiguration topBoomConfig = new BoomConfiguration();
        topBoomConfig.robot = this;
        topBoomConfig.servoName = "topServo";
        topBoomConfig.direction = Servo.Direction.FORWARD;
        topBoomConfig.controllerInputMethod = Control.Gp2_RightStickY;
        topBoomConfig.invertInput = true;
        topBoomConfig.maxIncrement = 0.001;
        topBoomConfig.zeroDegreePosition = 0.586;

        BoomConfiguration midBoomConfig = new BoomConfiguration();
        midBoomConfig.robot = this;
        midBoomConfig.servoName = "middleServo";
        midBoomConfig.direction = Servo.Direction.FORWARD;
        midBoomConfig.controllerInputMethod = Control.Gp2_RightStickX;
        midBoomConfig.invertInput = false;
        midBoomConfig.maxIncrement = 0.005;
        midBoomConfig.zeroDegreePosition = 0.54;

        BoomConfiguration bottomBoomConfig = new BoomConfiguration();
        bottomBoomConfig.robot = this;
        bottomBoomConfig.servoName = "bottomLeftServo";
        bottomBoomConfig.isDualServo = true;
        bottomBoomConfig.secondaryServoName = "bottomRightServo";
        bottomBoomConfig.direction = Servo.Direction.FORWARD;
        bottomBoomConfig.controllerInputMethod = Control.Gp2_LeftStickX;
        bottomBoomConfig.invertInput = true;
        bottomBoomConfig.maxIncrement = 0.00054;
        bottomBoomConfig.zeroDegreePosition = 0.29;
        bottomBoomConfig.degree = 0.000556;

        ArmConfiguration armConfig = new ArmConfiguration();
        armConfig.robot = this;
        armConfig.topBoomConfig = topBoomConfig;
        armConfig.midBoomConfig = midBoomConfig;
        armConfig.bottomBoomConfig = bottomBoomConfig;

        this.arm = new Arm(armConfig);

        // pickup pixel routine
        this.arm.addGp2_A_PressHandler(event -> {
            ArmOpMode.this.arm.cancelAllCommands();

            ArmOpMode.this.arm
                    .moveBottomFromCurrentPosition(-10)
                    .moveTopFromCurrentPosition(-15)
                    .wait(0)

                    .moveMiddle(-110, 0.005)
                    .moveBottom(45, 0.001)
                    .wait(0)

                    .moveMiddle(-30, 0.005)
                    .wait(0)

//                    .moveBottom(0, 0.001)
//                    .moveMiddle(10,0.005)
//                    .wait(0)
            ;
            //ArmOpMode.this.arm.moveBottom(0);

        });

    }


    @Override
    public void runOpMode() throws InterruptedException {
        this.arm.init();


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
