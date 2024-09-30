package org.firstinspires.ftc.teamcode.metalheads.calibration;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

/**
 * max motor position 2977
 */
@TeleOp(name="ViperSlideCalib", group="Calibration")
//@Disabled
public class ViperSlideCalib extends IsaacBot {

    /**
     */
    private DcMotor motor;

    /**
     */
    private String motorName;

    public ViperSlideCalib() {
        super();

        this.motorName = "viperSlide";


    }

    /**
     *
     */
    public void initBot() {
        super.initBot();

        this.motor = this.hardwareMap.get(DcMotor.class, motorName);

        this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
