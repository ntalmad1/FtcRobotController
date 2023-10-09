package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.Control;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.arm.ArmConfiguration;
import org.firstinspires.ftc.teamcode.library.arm.BoomConfiguration;
import org.firstinspires.ftc.teamcode.library.component.command.CommandGroup;
import org.firstinspires.ftc.teamcode.library.component.command.GoToDegreesCommand;
import org.firstinspires.ftc.teamcode.library.component.command.WaitCommand;

@TeleOp(name="ArmOpMode", group="Linear OpMode")
//@Disabled
public class ArmOpMode extends IsaacBot {

    //private Boom topBoom;

    public ArmOpMode(){
        super();

        BoomConfiguration topBoomConfig = new BoomConfiguration();
        topBoomConfig.robot = this;
        topBoomConfig.servoName = "topServo";
        topBoomConfig.direction = Servo.Direction.FORWARD;
        topBoomConfig.controllerInputMethod = Control.Gp2_LeftStickX;
        topBoomConfig.invertInput = false;
        topBoomConfig.maxIncrement = 0.001;
        topBoomConfig.zeroDegreePosition = 0.586;

        BoomConfiguration midBoomConfig = new BoomConfiguration();


        BoomConfiguration bottomBoomConfig = new BoomConfiguration();

        ArmConfiguration armConfig = new ArmConfiguration();
        armConfig.topBoomConfig = topBoomConfig;
        armConfig.midBoomConfig = midBoomConfig;
        armConfig.bottomBoomConfig = bottomBoomConfig;

        this.arm = new Arm(armConfig);
    }


    @Override
    public void runOpMode() throws InterruptedException {
        this.topBoom.init();

        this.waitForStart();

        boolean flag = true;

        while (this.opModeIsActive()) {
            topBoom.run();

            if (flag) {
                sleep(2000);

                CommandGroup commandGroup = new CommandGroup();
                commandGroup.add(new GoToDegreesCommand(0));
                commandGroup.add(new WaitCommand(2000));
                commandGroup.add(new GoToDegreesCommand(90));


                topBoom.addCommand(commandGroup);

                flag = false;
            }


            this.telemetry.update();

//            double lx = this.gamepad2.left_stick_x;
//            this.telemetry.addData("Left stick x:", "%2f", lx);
//            this.telemetry.update();

        }
    }
}
