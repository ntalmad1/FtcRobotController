package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.Control;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.arm.Boom;
import org.firstinspires.ftc.teamcode.library.arm.BoomConfiguration;

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
                topBoom.gotoDegrees(0);
                flag = false;
            }


            this.telemetry.update();

//            double lx = this.gamepad2.left_stick_x;
//            this.telemetry.addData("Left stick x:", "%2f", lx);
//            this.telemetry.update();

        }
    }
}
