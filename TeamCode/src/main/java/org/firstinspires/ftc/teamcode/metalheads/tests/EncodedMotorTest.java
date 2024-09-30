package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

@TeleOp(name="EcodedMotorTest", group="Tests")
//@Disabled
public class EncodedMotorTest extends IsaacBot {

    /**
     */
    private DcMotor motor;

    /**
     */
    private String motorName;

    public EncodedMotorTest() {
        super();

        this.motorName = "linearActuator";


    }

    /**
     *
     */
    public void initBot() {
        super.initBot();

        this.motor = this.hardwareMap.get(DcMotor.class, motorName);

        this.addGp1_LeftStick_Y_Handler(event -> {

            double power = event.getPosition() * -1;

            this.motor.setPower(power);
        });

        //this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //this.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    /**
     *
     */
    public void run() {
        super.run();

        this.telemetry.addData("Direction:", this.motor.getDirection());
        this.telemetry.addData("Position:", this.motor.getCurrentPosition());
        this.telemetry.addData("TargetPosition:", this.motor.getTargetPosition());
        this.telemetry.addData("Power:", this.motor.getPower());
        this.telemetry.update();
    }



}
