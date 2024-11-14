package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.dcmotor.DcMotorComponent;
import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;

/**
 *
 */
public class EncodedMotor extends DcMotorComponent {

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
     * @param position
     */
    public AbstractAction gotoPositionAction (int position) {
        return this.gotoPositionAction(position, 1);
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

            if (newPosition <= this.getConfig().minTics) {
                newPosition = this.getConfig().minTics;
            }

            power = -1;

            this.setTargetPosition(newPosition);
            this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.setPower(power);
        }
        else {
            this.setPower(0);
            this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    /**
     *
     */
    public void resetEncoder () {
        this.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     */
    public void run () {
        super.run();
    }
}
