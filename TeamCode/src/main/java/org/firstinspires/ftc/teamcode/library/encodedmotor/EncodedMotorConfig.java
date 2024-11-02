package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.dcmotor.DcMotorComponentConfig;

/**
 *
 */
public class EncodedMotorConfig extends DcMotorComponentConfig {

    /**
     */
    public int minTics;

    /**
     */
    public int maxTics;

    /**
     *
     * @param robot
     */
    public EncodedMotorConfig (IsaacBot robot) {
        super(robot);
    }
}
