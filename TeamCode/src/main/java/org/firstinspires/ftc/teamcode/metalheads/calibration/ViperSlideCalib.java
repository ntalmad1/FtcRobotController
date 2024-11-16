package org.firstinspires.ftc.teamcode.metalheads.calibration;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.utility.Control;

/**
 * max motor position 2977
 */
@TeleOp(name="ViperSlideCalib", group="Calibration")
@Disabled
public class ViperSlideCalib extends IsaacBot {

    /**
     */
    private EncodedMotor motor;

    private EncodedMotorConfig config;

    /**
     *
     */
    public ViperSlideCalib() {
        super();

        EncodedMotorConfig config = new EncodedMotorConfig(this);
        config.minTics = 0;
        config.maxTics = 100000;

//        config.minVolts = 0.456;
//        config.maxVolts = 1.284;
        config.motorName = "rightSlide";
        config.control = Control.Gp1_LeftStickY;
        config.brakeOn = true;
        config.initialMotorDirection = DcMotorSimple.Direction.REVERSE;
        config.scale = 200;

        config.isDualMotor = true;
        config.secondaryMotorName = "leftSlide";
        config.secondaryInitialMotorDirection = DcMotorSimple.Direction.FORWARD;

//        config.potentiometerConfig = new PotentiometerConfig(this);
//        config.potentiometerConfig.potentiometerName = "pot";

        this.config = config;
    }

    /**
     *
     */
    public void initBot() {
        super.initBot();

        this.motor = new EncodedMotor(config);
        this.motor.init();
    }

    /**
     *
     */
    public void run() {
        super.run();

        this.motor.run();

       // this.telemetry.addData("Direction:", this.motor.getDirection());
        this.telemetry.addData("Position:", this.motor.getCurrentPosition());
       // this.telemetry.addData("Voltage:", this.motor.getVoltage());
       // this.telemetry.addData("Power:", this.motor.getPower());
        this.telemetry.update();
    }



}
