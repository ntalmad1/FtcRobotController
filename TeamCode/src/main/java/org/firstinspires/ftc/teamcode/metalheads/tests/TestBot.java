package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.action.ParallelActionImpl;
import org.firstinspires.ftc.teamcode.library.action.SequentialActionImpl;
import org.firstinspires.ftc.teamcode.library.action.WaitAction;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.utility.Control;

/**
 *
 */
@TeleOp(name = "TestBot", group = "Tests")
public class TestBot extends IsaacBot {

    /**
     */
    private EncodedMotor motor0;

    /**
     */
    private EncodedMotor motor1;

    /**
     */
    private EncodedMotorConfig motorConfig0;

    /**
     */
    private EncodedMotorConfig motorConfig1;

    /**
     * Constructor
     *
     */
    public TestBot () {
        super();

        motorConfig0 = new EncodedMotorConfig(this);

        motorConfig0.motorName = "motor0";
        motorConfig0.minTics = 0;
        motorConfig0.maxTics = 10000;
        motorConfig0.brakeOn = false;
        motorConfig0.control = Control.Gp1_LeftStickY;
        motorConfig0.scale = 200;
        //motorConfig0.debug = true;


        motorConfig1 = new EncodedMotorConfig(this);

        motorConfig1.motorName = "motor1";
        motorConfig1.minTics = 0;
        motorConfig1.maxTics = 10000;
        motorConfig1.brakeOn = false;
        motorConfig1.control = Control.Gp1_RightStickY;
        motorConfig1.scale = 200;
        //motorConfig1.debug = true;
    }

    /**
     *
     */
    @Override
    public void initBot () {
        super.initBot();

        this.motor0 = new EncodedMotor(motorConfig0);
        this.motor0.init();

        this.motor1 = new EncodedMotor(motorConfig1);
        this.motor1.init();
    }

    /**
     *
     */
    @Override
    public void go () {

        AbstractAction action =
                new SequentialActionImpl(
                        this.motor0.gotoPositionAction(2000),
                        new WaitAction(1000),
                        this.motor1.gotoPositionAction(2000),
                    new ParallelActionImpl(
                        this.motor0.gotoPositionAction(0),
                        this.motor1.gotoPositionAction(0)
                ));

        this.runAction(action);

    }

    /**
     *
     */
    @Override
    public void run () {
        super.run();

        this.motor0.run();
        this.motor1.run();

//        this.telemetry.addData("Pos: ", this.motor.getCurrentPosition());
//        this.telemetry.update();
    }

}
