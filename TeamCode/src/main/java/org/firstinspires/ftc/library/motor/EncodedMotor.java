package org.firstinspires.ftc.library.motor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.component.Component;

/**
 *
 */
public class EncodedMotor extends Component {

    /**
     */
    private DcMotor motor;

    /**
     */
    private EncodedMotorConfig config;

    /**
     * @param config
     */
    public EncodedMotor(EncodedMotorConfig config) {
        super(config.robot);

        this.config = config;
    }


    /**
     *
     */
    public void init () {
        super.init();

        this.motor = this.robot.hardwareMap.get(DcMotor.class, this.config.motorName);

        this.motor.setDirection(this.config.initialMotorDirection);
        this.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

}
