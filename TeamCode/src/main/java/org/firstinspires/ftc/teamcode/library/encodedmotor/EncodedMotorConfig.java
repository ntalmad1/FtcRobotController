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
    public Integer controllerMinTics;

    /**
     */
    public int minTics;

    /**
     */
    public int maxTics;

    /**
     * The number of tics ti adjust positon by for each cyle when input is detected from controller
     */
    public int scale = 100;

    /**
     *
     * @param robot
     */
    public EncodedMotorConfig (IsaacBot robot) {
        super(robot);
    }
}
