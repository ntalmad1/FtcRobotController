package org.firstinspires.ftc.teamcode.library.potentiometermotor;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.library.potentiometer.Potentiometer;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

/**
 *
 */
public class PotentiometerMotor extends EncodedMotor {

    /**
     */
    public Potentiometer potentiometer;

    /**
     */
    private boolean holding;

    /**
     */
    private TouchSensor touchSensor;

    /**
     * Constructor
     *
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
    public TouchSensor getTouchSensor() {
        return this.touchSensor;
    }

    /**
     *
     * @return
     */
    public double getVoltage() {
        return this.potentiometer.getVoltage();
    }

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

        double currentPosition = this.getTicsByVolts(this.getVoltage());
        double targetPosition = this.getTicsByVolts(targetVoltage);

        int targetTics = -1 * (int)(currentPosition - targetPosition);

        if (power == 0) {
            targetTics = 0;
        }

        int target = this.getCurrentPosition() + targetTics;

        this.moveToPosition(power, target);
    }

    /**
     *
     * @param power
     * @param targetPosition
     */
    public void moveToPosition (double power, int targetPosition) {
        int numTics = targetPosition - this.getCurrentPosition();

        if (this.getVoltage() >= this.getConfig().maxVolts && numTics > 0) {
            targetPosition = this.getCurrentPosition();

        }
        else if (this.getVoltage() <= this.getConfig().minVolts && numTics < 0) {
            targetPosition = this.getCurrentPosition();
        }

        int currentPosTicsByVolts = this.getTicsByVolts(this.getVoltage());

        int maxTicsByVolts = this.getTicsByVolts(this.getConfig().maxVolts);

        if ((currentPosTicsByVolts + numTics) > maxTicsByVolts) {
            targetPosition = this.getCurrentPosition() + (maxTicsByVolts - currentPosTicsByVolts);
        }
//        else if (currentPosTicsByVolts + numTics < 0) {
//            targetPosition = this.getCurrentPosition() + (numTics - currentPosTicsByVolts);
//        }


        super.moveToPosition(power, targetPosition);
    }

    /**
     *
     * @param volts
     * @return
     */
    private int getTicsByVolts (double volts) {

        int tics = (int) (-1104.249 * Math.pow(volts, 2) + 5739.821 * volts + -2454.05);

        return tics;
    }

    /**
     *
     */
    public void run () {
        super.run();

        if ((this.getTargetPosition() < 0) && (this.getVoltage() <= Constants.VIPER_SLIDES_VOLTS_MIN)) {
            this.setPower(0);
        }
    }

    /**
     *
     * @parm sensor
     */
    public void setTouchSensor(TouchSensor sensor) {
        this.touchSensor = sensor;
    }
}
