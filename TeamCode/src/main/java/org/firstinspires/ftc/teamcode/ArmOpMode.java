package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.library.Control;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.arm.Boom;
import org.firstinspires.ftc.teamcode.library.arm.BoomConfiguration;
import org.firstinspires.ftc.teamcode.library.component.command.CommandGroup;
import org.firstinspires.ftc.teamcode.library.component.command.GoToDegreesCommand;
import org.firstinspires.ftc.teamcode.library.component.command.WaitCommand;

@TeleOp(name="ArmOpMode", group="Linear OpMode")
//@Disabled
public class ArmOpMode extends IsaacBot {

    private Boom topBoom;

    public ArmOpMode(){
        super();

        BoomConfiguration topBoomConfig = new BoomConfiguration();
        topBoomConfig.robot = this;
        topBoomConfig.deviceName = "topServo";
        topBoomConfig.direction = Servo.Direction.REVERSE;
        topBoomConfig.controllerInputMethod = Control.Gp2_LeftStickX;
        topBoomConfig.minPosition = 0;
        topBoomConfig.maxPosition = 1;
        topBoomConfig.homePosition = 0;
        topBoomConfig.maxIncrement = 0.001;
        topBoomConfig.zeroDegreePosition = 0.586;



        // servo max position / degrees of articulation
        topBoomConfig.degree = 0.0033;

        this.topBoom = new Boom(topBoomConfig);
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
