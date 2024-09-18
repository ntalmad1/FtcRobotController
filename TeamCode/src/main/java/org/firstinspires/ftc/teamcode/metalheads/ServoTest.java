package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 *
 */
@TeleOp(name="ServoTest", group="Tests")
//@Disabled
public class ServoTest extends LinearOpMode {

    /**
     *
     * @throws InterruptedException
     */
    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addLine("Waiting for the start button!");
        telemetry.update();

        this.waitForStart();

        while (this.opModeIsActive()) {

            telemetry.addLine("Do something here!");
            telemetry.update();

        }

        telemetry.addLine("Shutting down!");
        telemetry.update();
    }
}
