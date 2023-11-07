package org.firstinspires.ftc.teamcode.library.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

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
    protected final IsaacBot robot;

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
        this.rightFrontMotor = this.initMotor(this.getConfig().rightFrontDeviceName);
        this.rightRearMotor = this.initMotor(this.getConfig().rightRearDeviceName);
        this.leftRearMotor = this.initMotor(this.getConfig().leftRearDeviceName);

        this.motorGroup.add(this.leftFrontMotor);
        this.motorGroup.add(this.rightFrontMotor);
        this.motorGroup.add(this.rightRearMotor);
        this.motorGroup.add(this.leftRearMotor);

        this.robot.initImu(this.getConfig().imuName);
    }

    /**
     *
     * @return The configuration variables and values for the drive train
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
        DcMotor motor;
        motor = this.robot.hardwareMap.get(DcMotor.class, deviceName);

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
