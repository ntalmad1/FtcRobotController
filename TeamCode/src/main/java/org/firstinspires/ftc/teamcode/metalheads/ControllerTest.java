package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.IsaacBot;

@TeleOp(name="ControllerTest", group="Linear OpMode")
@Disabled
public class ControllerTest extends IsaacBot {

    public ControllerTest(){
        super();
}


    @Override
    public void runOpMode() throws InterruptedException {

        this.waitForStart();

        while (this.opModeIsActive()) {

            this.telemetry.addData("GP1 Left Stick Y: ", "%2f", this.gamepad1.left_stick_y);
            this.telemetry.addData("GP1 Left Stick X: ", "%2f", this.gamepad1.left_stick_x);
            this.telemetry.addLine();
            this.telemetry.addData("GP1 Right Stick Y: ", "%2f", this.gamepad1.right_stick_y);
            this.telemetry.addData("GP1 Right Stick X: ", "%2f", this.gamepad1.right_stick_x);
            this.telemetry.addLine();
            this.telemetry.addData("GP2 Left Stick Y: ", "%2f", this.gamepad2.left_stick_y);
            this.telemetry.addData("GP2 Left Stick X: ", "%2f", this.gamepad2.left_stick_x);
            this.telemetry.addLine();
            this.telemetry.addData("GP2 Right Stick Y: ", "%2f", this.gamepad2.right_stick_y);
            this.telemetry.addData("GP2 Right Stick X: ", "%2f", this.gamepad2.right_stick_x);
            this.telemetry.update();
        }
    }
}



