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
    private int position;

    /**
     */
    private PotentiometerMotor motor;


    /**
     *
     * @param motor
     * @param position
     * @param power
     */
    public PotentiometerMotorGoToPositionAction(PotentiometerMotor motor, int position, double power) {
        this.motor = motor;
        this.position = position;
        this.power = power;
    }

    /**
     *
     */
    public void init () {
        this.motor.moveToPosition(power, position);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean run() {
        if (this.motor.isBusy() && !this.motor.isHolding())
        {
            if (this.motor.getVoltage() >= 1.20) {
                this.motor.moveToPosition(1, this.motor.getCurrentPosition());
                return CONTIUE;
            }

            if (this.motor.getConfig().debug) {
                this.motor.getRobot().telemetry.addData("Running to Position: ",  " %7d", position);
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
