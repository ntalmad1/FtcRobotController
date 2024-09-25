package org.firstinspires.ftc.teamcode.archive.competition.config;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.archive.library.winch.WinchConfig;

/**
 *
 */
public class WinchCompConfig extends WinchConfig {

    /**
     * @param robot
     */
    public WinchCompConfig(IsaacBot robot) {
        super(robot);

        EncodedMotorConfig encodedMotorConfig = new EncodedMotorConfig(robot);
        encodedMotorConfig.motorName = "winchMotor";
        encodedMotorConfig.initialMotorDirection = DcMotorSimple.Direction.FORWARD;
        encodedMotorConfig.minTics = -100000;
        encodedMotorConfig.maxTics = 100000;
        encodedMotorConfig.increment = 100;
        encodedMotorConfig.brakeOn = false;
        encodedMotorConfig.control = null;

        this.encodedMotorConfig = encodedMotorConfig;

        this.debug = false;
    }
}
