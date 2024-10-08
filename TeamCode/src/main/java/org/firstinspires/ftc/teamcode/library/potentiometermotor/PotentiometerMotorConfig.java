package org.firstinspires.ftc.teamcode.library.potentiometermotor;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.potentiometer.PotentiometerConfig;

/**
 *
 */
public class PotentiometerMotorConfig extends EncodedMotorConfig {

    /**
     */
    public PotentiometerConfig potentiometerConfig;

    /**
     */
    public double minVolts;

    /**
     */
    public double maxVolts;

    /**
     * Constructor
     *
     * @param robot
     */
    public PotentiometerMotorConfig(IsaacBot robot) {
        super(robot);

        this.potentiometerConfig = new PotentiometerConfig(robot);
    }
}
