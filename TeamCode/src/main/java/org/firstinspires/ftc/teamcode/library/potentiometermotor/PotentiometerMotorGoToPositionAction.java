package org.firstinspires.ftc.teamcode.library.potentiometermotor;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;

/**
 *
 */
public class PotentiometerMotorGoToPositionAction extends AbstractAction {

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
     *
     * @param motor
     * @param voltage
     * @param power
     */
    public PotentiometerMotorGoToPositionAction(PotentiometerMotor motor, double voltage, double power) {
        this.motor = motor;
        this.voltage = voltage;
        this.power = power;
    }

    /**
     *
     */
    public void init () {
        this.motor.moveToVoltage(power, voltage);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean run() {
        if (this.motor.isBusy() && !this.motor.isHolding())
        {
            if (this.motor.getConfig().debug) {
                this.motor.getRobot().telemetry.addData("Running to Volt: ",  " %7d", voltage);
                this.motor.getRobot().telemetry.addData("Currently at: ",  " at %7d", this.motor.getCurrentPosition());
                this.motor.getRobot().telemetry.addData("Motor Power: ", " %f", this.motor.getPower());
                this.motor.getRobot().telemetry.update();
            }

            return CONTIUE;
        }
        else {
            return STOP;
        }

    }
}
