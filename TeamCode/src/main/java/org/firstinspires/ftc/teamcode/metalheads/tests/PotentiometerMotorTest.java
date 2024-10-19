package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.potentiometer.PotentiometerConfig;
import org.firstinspires.ftc.teamcode.library.potentiometermotor.PotentiometerMotor;
import org.firstinspires.ftc.teamcode.library.potentiometermotor.PotentiometerMotorConfig;
import org.firstinspires.ftc.teamcode.library.utility.Control;

@TeleOp(name="PotentiometerMotorTest", group="Tests")
@Disabled
public class PotentiometerMotorTest extends IsaacBot {

    /**
     */
    private PotentiometerMotor motor;

    /**
     */
    private String motorName;

    public PotentiometerMotorTest() {
        super();

        PotentiometerMotorConfig config = new PotentiometerMotorConfig(this);
        config.brakeOn = true;
        config.minTics = 100;
        config.maxTics = 2900;
        config.minVolts = 0.586;
        config.maxVolts = 1.129;
        config.motorName = "viperSlide";
        config.potentiometerConfig = new PotentiometerConfig(this);
        config.potentiometerConfig.potentiometerName = "pot";
        config.control = Control.Gp1_LeftStickY;

        motor = new PotentiometerMotor(config);
    }

    /**
     *
     */
    public void initBot() {
        super.initBot();

        this.motor.init();
    }

    /**
     *
     */
    @Override
    public void go() {

        //this.motor.gotoPosition(5000, 1);

    }

    /**
     *
     */
    public void run() {
        super.run();

        this.motor.run();

        this.telemetry.addData("Direction:", this.motor.getDirection());
        this.telemetry.addData("Position:", this.motor.getCurrentPosition());
        this.telemetry.addData("TargetPosition:", this.motor.getTargetPosition());
        this.telemetry.addData("Power:", this.motor.getPower());
        this.telemetry.addData("Voltage:", this.motor.getVoltage());
        this.telemetry.update();
    }



}
