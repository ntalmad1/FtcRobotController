package org.firstinspires.ftc.teamcode.metalheads.calibration;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.utility.Control;

/**
 *
 */
@TeleOp(name="MainBoomCalib", group="Calibration")
//@Disabled
public class MainBoomCalib extends IsaacBot {

    /**
     */
    private EncodedMotor motor;

    private EncodedMotorConfig config;

    /**
     * Constructor
     *
     */
    public MainBoomCalib() {
        super();

        config = new EncodedMotorConfig(this);

        config.motorName = "rightWorm";
        config.brakeOn = false;
        config.isDualMotor = true;
        config.minTics = 0;
        config.maxTics = 1155;
        config.scale = 200;
        config.control = Control.Gp1_LeftStickY;

        config.secondaryMotorName = "leftWorm";
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

        this.telemetry.addData("Direction:", this.motor.getDirection());
        this.telemetry.addData("Position:", this.motor.getCurrentPosition());
        this.telemetry.update();
    }



}
