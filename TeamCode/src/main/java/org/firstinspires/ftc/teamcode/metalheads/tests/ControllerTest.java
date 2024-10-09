package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

@TeleOp(name = "ControllerTest", group = "Tests")
//@Disabled
public class ControllerTest extends IsaacBot {

    /**
     * Constructor
     */
    public ControllerTest() {
        super();
    }

    /**
     *
     */
    @Override
    public void initBot() {
        super.initBot();

        this.addGp1_Back_PressHandler(event -> {
            telemetry.log().add("gamepad1 back press");
        });

        this.addGp1_Start_PressHandler(event -> {
            telemetry.log().add("gamepad1 start press");
        });

        this.addGp2_Back_PressHandler(event -> {
            telemetry.log().add("gamepad2 back press");
        });

        this.addGp2_Start_PressHandler(event -> {
            telemetry.log().add("gamepad2 start press");
        });
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();
    }
}
