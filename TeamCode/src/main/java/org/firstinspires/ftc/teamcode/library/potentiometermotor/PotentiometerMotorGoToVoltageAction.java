package org.firstinspires.ftc.teamcode.library.potentiometermotor;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;

/**
 *
 */
public class PotentiometerMotorGoToVoltageAction extends AbstractAction {

    /**
     */
    private double power;

    /**
     */
    private double voltage;

    /**
     */
    private PotentiometerMotor motor;

    /**
     */
    private Integer timeout = 250;

    /**
     */
    private int tolerance = 40;

    /**
     */
    private ElapsedTime timer;

    /**
     */
    private boolean initialTouchSensorIsPressed;

    /**
     *
     * @param motor
     * @param voltage
     * @param power
     */
    public PotentiometerMotorGoToVoltageAction(PotentiometerMotor motor, double voltage, double power) {
        this.motor = motor;
        this.voltage = voltage;
        this.power = power;
    }

    /**
     *
     */
    public void init () {
        if (this.motor.getTouchSensor() != null) {
            initialTouchSensorIsPressed = this.motor.getTouchSensor().isPressed();
        }

        this.motor.moveToVoltage(power, voltage);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean run() {
        if (this.motor.isBusy())
        {
            if (this.motor.getConfig().debug) {
                this.motor.getRobot().telemetry.addData("Running to Volt: ", voltage);
                this.motor.getRobot().telemetry.addData("Currently at: ",  this.motor.getCurrentPosition());
                this.motor.getRobot().telemetry.addData("Motor Power: ", this.motor.getPower());
            }

            if (this.initialTouchSensorIsPressed && this.motor.getTargetPosition() < 0) {
                this.motor.setPower(0);
                return STOP;
            }


            if (this.motor.getTouchSensor() != null && !this.initialTouchSensorIsPressed) {
                if (this.motor.getTouchSensor().isPressed()) {
                    this.motor.setPower(0);
                    return STOP;
                }
            }

            return CONTIUE;
        }
        else {

            if (this.motor.getTouchSensor() != null && this.motor.getTouchSensor().isPressed()) {
                    this.motor.setPower(0);
            }

            return STOP;
        }

    }

    /**
     *
     * @return
     */
    private boolean isTimedOut() {
        if (this.timer == null) {
            return false;
        }

        boolean timedOut = this.timer.milliseconds() > this.timeout;
        if (timedOut && this.motor.isBrakeOn()) {
            this.motor.setTargetPosition(this.motor.getCurrentPosition());
        }
        else {
            this.motor.setPower(0);
        }

        return timedOut;
    }

    /**
     *
     * @return
     */
    private boolean withinTolerance() {
        int position = this.motor.getTargetPosition();
        return position - tolerance > this.motor.getCurrentPosition() || position + tolerance < this.motor.getCurrentPosition();
    }
}
