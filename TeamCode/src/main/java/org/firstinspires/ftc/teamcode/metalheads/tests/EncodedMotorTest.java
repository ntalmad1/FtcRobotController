package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.utility.Control;

@TeleOp(name="EcodedMotorTest", group="Tests")
//@Disabled
public class EncodedMotorTest extends IsaacBot {

    /**
     */
    private EncodedMotor motor;

    /**
     */
    private String motorName;

    public EncodedMotorTest() {
        super();

        EncodedMotorConfig config = new EncodedMotorConfig(this);
        config.brakeOn = false;
        config.maxTics = 10000;
        config.minTics = 0;
        config.motorName = "motor0";
        config.increment = 10000;
        config.control = Control.Gp1_LeftStickY;

        motor = new EncodedMotor(config);
    }

    /**
     *
     */
    public void initBot() {
        super.initBot();

        this.motor.init();
//        this.motor = this.hardwareMap.get(DcMotor.class, motorName);
//
//        this.addGp1_LeftStick_Y_Handler(event -> {
//
//            double power = event.getPosition() ;
//
//            int currentPos = motor.getCurrentPosition();
//            int direction = event.getPosition() > 0 ?  1 : -1;
//
//            int targetPosition = event.getPosition() == 0 ? currentPos : currentPos + 10000 * direction;
//
//            motor.setTargetPosition(targetPosition);
//            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            motor.setPower(Math.abs(power));
//
//        });
//
//        this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        this.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     *
     */
    @Override
    public void go() {

        this.motor.gotoPosition(5000, 1);

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
        this.telemetry.update();
    }



}
