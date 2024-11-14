package org.firstinspires.ftc.teamcode.library.potentiometermotor;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.utility.Direction;

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
    private Integer timeout = 300;

    /**
     */
    private int tolerance = 40;

    /**
     */
    private ElapsedTime timer;

    /**
     */
    private boolean initialTouchSensorIsPressed;

    private boolean useTimer = false;

    private int targetPosition;

    /**
     */
    private Direction direction;

    /**
     *
     * @param motor
     * @param voltage
     * @param power
     */
    public PotentiometerMotorGoToVoltageAction(PotentiometerMotor motor, double voltage, double power, Boolean brake) {
        this.motor = motor;
        this.voltage = voltage;
        this.power = power;

        if (brake == null) {
            brake = this.motor.getConfig().brakeOn;
        } else if (!brake) {
            this.useTimer = true;
        }



        this.motor.setBrake(brake);
    }

    /**
     *
     */
    public void init () {
        if (this.motor.getTouchSensor() != null) {
            initialTouchSensorIsPressed = this.motor.getTouchSensor().isPressed();
        }

        this.targetPosition = this.motor.moveToVoltage(power, voltage);

        if (this.motor.getCurrentPosition() <= this.targetPosition) {
            //direction = Direction.FORWARD;
            direction = Direction.REVERSE;
        } else {
            direction = Direction.FORWARD;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean run() {
        if (this.initialTouchSensorIsPressed && Direction.FORWARD.equals(this.direction)) {
            this.motor.setPower(0);
            return STOP;
        }

        if (this.useTimer && this.timeout != null && this.withinTolerance()) {
            if (this.timer == null) {
                this.timer = new ElapsedTime();
                this.timer.reset();
            }
        }

        if (this.motor.isBusy())
        {
            if (this.motor.getConfig().debug) {
                this.motor.getRobot().telemetry.addData("Running to Volt: ", voltage);
                this.motor.getRobot().telemetry.addData("Currently at: ",  this.motor.getCurrentPosition());
                this.motor.getRobot().telemetry.addData("Motor Power: ", this.motor.getPower());
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
        if (timedOut) {
            this.motor.setPower(0);
            this.timer = null;
        }

        return timedOut;
    }

    /**
     *
     * @return
     */
    private boolean withinTolerance() {
        if (Direction.REVERSE.equals(direction)) { // Opening
            return this.motor.getCurrentPosition() >= this.targetPosition - tolerance;
        } else {
            return this.motor.getCurrentPosition() <= this.targetPosition + tolerance;
        }
    }
}
