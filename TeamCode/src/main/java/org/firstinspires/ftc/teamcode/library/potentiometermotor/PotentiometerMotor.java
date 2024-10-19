package org.firstinspires.ftc.teamcode.library.potentiometermotor;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.dcmotor.DcMotorComponent;
import org.firstinspires.ftc.teamcode.library.potentiometer.Potentiometer;

/**
 *
 */
public class PotentiometerMotor extends DcMotorComponent {

    /**
     */
    public Potentiometer potentiometer;

    /**
     */
    private double ticsPerVolt;

    /**
     */
    private boolean holding;

    /**
     * @param config
     */
    public PotentiometerMotor(PotentiometerMotorConfig config) {
        super(config);

        this.potentiometer = new Potentiometer(this.getConfig().potentiometerConfig);
    }

    /**
     *
     */
    @Override
    public void init() {
        super.init();

        this.potentiometer.init();

        this.ticsPerVolt = (this.getConfig().maxTics - this.getConfig().minTics) / (this.getConfig().maxVolts - this.getConfig().minVolts);
    }

    /**
     *
     * @return
     */
    public PotentiometerMotorConfig getConfig () {
        return (PotentiometerMotorConfig)super.getConfig();
    }

    /**
     *
     * @return
     */
    public double getVoltage() {
        return this.potentiometer.getVoltage();
    }

//    /**
//     *
//     * @param position
//     */
//    public void gotoPosition (int position) {
//        this.gotoPosition(position, 1);
//    }

    /**
     *
     * @param voltage
     */
    public Action gotoVoltageAction (double voltage) {
        return this.gotoVoltageAction(voltage, 1);
    }

    /**
     *
     * @param position
     */
    public Action gotoPositionAction (int position) {
        return this.gotoPositionAction(position, 1);
    }

//    /**
//     *
//     * @param position
//     * @param power
//     */
//    public void gotoPosition (int position, double power) {
//        this.addCommand(new EncodedMotorGoToPositionCommand(this, position, power));
//    }

    /**
     *
     * @param voltage
     * @param power
     * @return
     */
    public Action gotoVoltageAction (double voltage, double power) {
        return new PotentiometerMotorGoToVoltageAction(this, voltage, power);
    }

    /**
     *
     * @param position
     * @param power
     * @return
     */
    public Action gotoPositionAction (int position, double power) {
        return new PotentiometerMotorGoToPositionAction(this, position, power);
    }

//    /**
//     *
//     * @param position
//     * @param power
//     * @param handler
//     */
//    public void gotoPosition (int position, double power, CommandCallbackHandler handler) {
//        EncodedMotorGoToPositionCommand command = new EncodedMotorGoToPositionCommand(this, position, power);
//        command.addCallbackHandler(handler);
//        this.addCommand(command);
//    }

    /**
     *
     * @return
     */
    public boolean isHolding() {
        return this.holding;
    }


    /**
     *
     * @param power
     */
    public void move (double power) {
        if (power == 0) {
            this.moveToVoltage(power, this.potentiometer.getVoltage());
        }
         else if (power > 0) {
            this.moveToVoltage(power, this.getConfig().maxVolts);
        }
        else if (power < 0) {
            this.moveToVoltage(power, this.getConfig().minVolts);
        }
    }

    /**
     *
     * @param power
     * @param targetVoltage
     */
    public void moveToVoltage (double power, double targetVoltage) {
        if (power == 0 && this.holding) {
            return;
        }

        if (targetVoltage < this.getConfig().minVolts) {
            targetVoltage = this.getConfig().minVolts;
        }
        else if (targetVoltage > this.getConfig().maxVolts) {
            targetVoltage = this.getConfig().maxVolts;
        }

        double currentPosition = this.ticsPerVolt * this.getVoltage();
        double targetPosition = this.ticsPerVolt * targetVoltage;

        double targetTics = -1 * (currentPosition - targetPosition);

        if (power == 0) {
            targetTics = 0;

            if (isBrakeOn()) {
                holding = true;
                power = 1;
            }
            else {
                holding = true;
                this.setPower(0);
                return;
            }
        }
        else {
            holding = false;
        }

        double target = this.getCurrentPosition() + targetTics;

        this.setTargetPosition((int)target);
        this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.setPower(power);
    }

    /**
     *
     * @param power
     * @param targetPosition
     */
    public void moveToPosition (double power, int targetPosition) {
        if (power == 0 && this.holding) {
            return;
        }

        if (power == 0) {
            targetPosition = this.getCurrentPosition();

            if (isBrakeOn()) {
                holding = true;
                power = 1;
            }
            else {
                holding = true;
                this.setPower(0);
                return;
            }
        }
        else {
            holding = false;
        }

        this.setTargetPosition(targetPosition);
        this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.setPower(power);
    }

    /**
     */
    public void resetEncoder() {
        this.getMotor().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
