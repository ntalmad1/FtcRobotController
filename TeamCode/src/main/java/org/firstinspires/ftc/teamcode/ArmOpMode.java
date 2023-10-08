package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.arm.Boom;

@TeleOp(name="ArmOpMode", group="Linear OpMode")
//@Disabled
public class ArmOpMode extends IsaacBot {

    private Boom topBoom;

    public ArmOpMode(){
        super();

        this.topBoom = new Boom(this);
    }


    @Override
    public void runOpMode() throws InterruptedException {
        this.topBoom.init();

        this.waitForStart();

        while (this.opModeIsActive()) {
            topBoom.run();

            this.telemetry.update();
//            double lx = this.gamepad2.left_stick_x;
//            this.telemetry.addData("Left stick x:", "%2f", lx);
//            this.telemetry.update();

        }
    }
}
