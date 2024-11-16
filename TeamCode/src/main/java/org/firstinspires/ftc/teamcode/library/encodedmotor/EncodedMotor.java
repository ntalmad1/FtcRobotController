package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.dcmotor.DcMotorComponent;
import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;

/**
 *
 */
public class EncodedMotor extends DcMotorComponent {

    /**
     */
    protected TouchSensor touchSensor;

    /**
     * Constructor
     *
     * @param config
     */
    public EncodedMotor(EncodedMotorConfig config) {
        super(config);
    }

    /**
     *
     * @return
     */
    public EncodedMotorConfig getConfig() {
        return (EncodedMotorConfig)super.getConfig();
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
     * @param position
     */
    public AbstractAction gotoPositionAction (int position) {
        return this.gotoPositionAction(position, 1);
    }

    /**
     *
     * @param position
     * @return
     */
    public AbstractAction viperSlidesGotoPositionAction (int position) {
        return this.viperSlidesGotoPositionAction(position, 1);
    }

    /**
     *
     * @param motorPos
     * @return
     */
    public AbstractAction gotoPositionAction(MotorPos motorPos) {
        if (motorPos == null) {
            return this.gotoPositionAction(this.getCurrentPosition(), 1);
        }

        if (motorPos.getPos() == null) {
            return this.gotoPositionAction(this.getCurrentPosition(), motorPos.getPower());
        }

        return this.gotoPositionAction(motorPos.getPos(), motorPos.getPower());
    }

    /**
     *
     * @param motorPos
     * @return
     */
    public AbstractAction viperSlidesGotoPositionAction(MotorPos motorPos) {
        if (motorPos == null) {
            return this.viperSlidesGotoPositionAction(this.getCurrentPosition(), 1);
        }

        if (motorPos.getPos() == null) {
            return this.viperSlidesGotoPositionAction(this.getCurrentPosition(), motorPos.getPower());
        }

        return this.viperSlidesGotoPositionAction(motorPos.getPos(), motorPos.getPower());
    }

    /**
     *
     * @param position
     * @param power
     * @return
     */
    public AbstractAction gotoPositionAction (int position, double power) {
        return new EncodedMotorGoToPositionAction(this, position, power, null);
    }

    /**
     *
     * @param position
     * @param power
     * @return
     */
    public AbstractAction viperSlidesGotoPositionAction (int position, double power) {
        return new ViperSlidsGoToPositionAction(this, position, power);
    }

    /**
     *
     * @param position
     * @param power
     * @return
     */
    public AbstractAction gotoPositionAction (int position, double power, Integer timeout) {
        return new EncodedMotorGoToPositionAction(this, position, power, timeout);
    }

    /**
     *
     */
    public void init () {
        super.init();

        this.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    /**
     * Called from event handler e.g. "left stick y"
     * @param power
     */
    public void move (double power) {
        if (this.getDirection().equals(DcMotorSimple.Direction.FORWARD)) {
            if (power > 0) {
                int newPosition = (int) (this.getCurrentPosition() + this.getConfig().scale * power);

                if (newPosition >= this.getConfig().maxTics) {
                    newPosition = this.getConfig().maxTics;
                }

                power = 1;

                this.setTargetPosition(newPosition);
                this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                this.setPower(power);

            } else if (power < 0) {
                int newPosition = (int) (this.getCurrentPosition() - Math.abs(this.getConfig().scale * power));

                if (newPosition <= this.getControllerMinTics()) {
                    newPosition = this.getControllerMinTics();
                }

                power = -1;

                this.setTargetPosition(newPosition);
                this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                this.setPower(power);
            } else {
                this.setPower(0);
                this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
        }
        else {
            // viper slides
            if (power < 0) {
                int newPosition = (int) (this.getCurrentPosition() + Math.abs(this.getConfig().scale * power));


                if (newPosition >= this.getConfig().maxTics) {
                    newPosition = this.getConfig().maxTics;
                }

                power = 1;

                this.setTargetPosition(newPosition);
                this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                this.setPower(power);

            } else if (power > 0) {
                int newPosition = (int) (this.getCurrentPosition() - this.getConfig().scale * power);

                if (newPosition <= this.getConfig().minTics) {
                    newPosition = this.getConfig().minTics;
                }

                power = -1;

                this.setTargetPosition(newPosition);
                this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                this.setPower(power);

            } else {
                this.setPower(0);
                this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
        }
    }

    /**
     *
     */
    public void resetEncoder () {
        this.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    boolean toggle;

    /**
     */
    public void run () {
        super.run();
    }

    /**
     *
     * @parm sensor
     */
    public void setTouchSensor(TouchSensor sensor) {
        this.touchSensor = sensor;
    }

    /**
     *
     * @return
     */
    private int getControllerMinTics() {
        if (this.getConfig().controllerMinTics != null) {
            return this.getConfig().controllerMinTics;
        }
        else {
            return this.getConfig().minTics;
        }
    }
}
