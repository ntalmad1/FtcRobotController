package org.firstinspires.ftc.teamcode.library.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.component.command.ICommand;
import org.firstinspires.ftc.teamcode.library.component.command.WaitCommand;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandCallbackHandler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class AbstractDriveTrain extends Component
{
    /**
     */
    protected SimpleDriveTrain.MotorGroup motorGroup = new MotorGroup();

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
    private final AbstractDriveTrainConfig config;

    /**
     *
     */
    protected AbstractDriveTrain(AbstractDriveTrainConfig config)
    {
        super(config.robot);

        this.robot.setImuName(config.imuName);

        this.config = config;
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
    protected AbstractDriveTrainConfig getConfig ()
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
     * @return
     */
    public MotorGroup getMotorGroup () {
        return this.motorGroup;
    }

    /**
     *
     * @return
     */
    public DcMotor getLeftFrontMotor () {
        return this.leftFrontMotor;
    }

    /**
     *
     * @return
     */
    public DcMotor getRightFrontMotor () {
        return this.rightFrontMotor;
    }

    /**
     *
     * @return
     */
    public DcMotor getRightRearMotor () {
        return this.rightRearMotor;
    }

    /**
     *
     * @return
     */
    public DcMotor getLeftRearMotor () {
        return this.leftRearMotor;
    }

    /**
     *
     * @param milliseconds
     * @return
     */
    public AbstractDriveTrain wait (int milliseconds) {
        ICommand command = new WaitCommand(milliseconds);
        this.addCommand(command);

        return this;
    }

    /**
     *
     * @param milliseconds
     * @param handler
     * @return
     */
    public AbstractDriveTrain wait (int milliseconds, CommandCallbackHandler handler) {
        ICommand command = new WaitCommand(milliseconds);
        command.addCallbackHandler(handler);

        this.addCommand(command);

        return this;
    }

    /**
     *
     */
    public static class MotorGroup
    {
        /**
         */
        private final List<DcMotor> motors = new ArrayList<>();

        private final List<DcMotor> disabledMotors = new ArrayList<>();

        private final List<DcMotor> lockedMotors = new ArrayList<>();

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
         * @param motor
         */
        public void disable (DcMotor motor) {
            this.disabledMotors.add(motor);
        }

        /**
         *
         * @param motor
         */
        public void lock (DcMotor motor) {
            this.lockedMotors.add(motor);
        }

        /**
         *
         */
        public void enableAll () {

            this.disabledMotors.clear();
            this.lockedMotors.clear();
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

                if (lockedMotors.contains(motor)) {
                    motor.setTargetPosition(0);
                    continue;
                }

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

                if (disabledMotors.contains(motor)) {
                    continue;
                }

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

                if (this.disabledMotors.contains(motor) || this.lockedMotors.contains(motor)) {
                    continue;
                }

                if (!motor.isBusy()) {
                    return false;
                }
            }

            return true;
        }
    }
}
