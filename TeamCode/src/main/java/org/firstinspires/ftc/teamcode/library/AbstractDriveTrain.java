package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class AbstractDriveTrain
{
    /**
     */
    protected SimpleDriveTrain.MotorGroup motorGroup = new MotorGroup();

    /**
     */
    protected final LinearOpMode robot;

    /**
     */
    protected final ElapsedTime runtime = new ElapsedTime();

    /**
     */
    protected DcMotor leftFrontMotor;
    protected DcMotor rightFrontMotor;
    protected DcMotor rightRearMotor;
    protected DcMotor leftRearMotor;

    /**
     */
    private final AbstractDriveTrainConfiguration config;

    /**
     *
     */
    protected AbstractDriveTrain(AbstractDriveTrainConfiguration config)
    {
        this.config = config;

        this.robot = config.robot;
    }

    /**
     */
    public void init ()
    {
        this.leftFrontMotor = this.initMotor(this.getConfig().leftFrontDeviceName);
        // this.rightFrontMotor = this.initMotor(config.rightFrontDeviceName);
        // this.rightRearMotor = this.initMotor(config.leftRearDeviceName);
        // this.leftRearMotor = this.initMotor(config.rightRearDeviceName);

        this.motorGroup.add(this.leftFrontMotor);
        //this.motorGroup.add(this.rightFrontMotor);
        //this.motorGroup.add(this.rightRearMotor);
        //this.motorGroup.add(this.leftRearMotor);
    }

    /**
     *
     * @return
     */
    protected AbstractDriveTrainConfiguration getConfig ()
    {
        return this.config;
    }

    /**
     *
     * @param deviceName The name of the device in the control hub
     */
    protected DcMotor initMotor (String deviceName)
    {
        DcMotor motor = this.robot.hardwareMap.get(DcMotor.class, deviceName);

        return motor;
    }

    /**
     *
     */
    protected static class MotorGroup
    {
        /**
         */
        private final List<DcMotor> motors = new ArrayList<>();

        /**
         * Adds a motor to the group
         *
         * @param motor The motor to add to the group
         */
        public void add (DcMotor motor)
        {
            this.motors.add(motor);
        }

        /**
         *
         * @param mode Sets the mode of all motors in the group
         */
        public void setMode (DcMotor.RunMode mode)
        {
            for (DcMotor motor : this.motors ) {
                motor.setMode(mode);
            }
        }

        /**
         *
         * @param tics The number of tics to rotate to
         */
        public void setTargetPosition (int tics)
        {
            for (DcMotor motor : this.motors ) {
                motor.setTargetPosition(tics);
            }
        }

        /**
         *
         * @param power Sets the power on each motor in the group
         */
        public void setPower (double power)
        {
            for (DcMotor motor : this.motors ) {
                motor.setPower(power);
            }
        }

        /**
         *
         * @return True: if all of the motors in group are busy, False: if one of the motors is not busy
         */
        public boolean isBusy ()
        {
            if (this.motors.size() == 0) {
                return false;
            }

            for (DcMotor motor : this.motors ) {
                if (!motor.isBusy()) {
                    return false;
                }
            }

            return true;
        }
    }
}
