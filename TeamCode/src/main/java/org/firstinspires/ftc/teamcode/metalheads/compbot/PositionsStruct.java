package org.firstinspires.ftc.teamcode.metalheads.compbot;

import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;
import org.firstinspires.ftc.teamcode.library.servo.ServoPos;

/**
 *
 */
public abstract class PositionsStruct {

    /**
     */
    public MotorPos mainBoomPos;

    /**
     */
    //public Double vSlideVolts;
    public MotorPos vSlidePos;

    /**
     */
    public ServoPos doubleServosPos;

    /**
     */
    public ServoPos middleServoPos;

    /**
     */
    public ServoPos clawRotatorPos;

    /**
     */
    public ServoPos clawPincherPos;

    /**
     * Constructor
     *
     */
    public PositionsStruct () {
        this.setValues();
    }

    /**
     *
     */
    public abstract void setValues();
}
