package org.firstinspires.ftc.teamcode.library.arm.boom;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.Control;
import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.component.command.Command;

/**
 *
 */
public class Boom extends Component {

    /**
     *
     */
    public enum Direction {

        /**
         *
         */
        FORWARD,

        /**
         *
         */
        REVERSE
    }

    /**
     *
     */
    private BoomConfiguration config;

    /**
     *
     */
    private Servo servo;

    /**
     *
     */
    private Servo secondaryServo;

    /**
     * @param config
     */
    public Boom(BoomConfiguration config) {
        super(config.robot);

        this.config = config;
    }

    /**
     *
     */
    public void init() {
        super.init();

        this.servo = this.robot.hardwareMap.get(Servo.class, this.config.servoName);
        this.servo.resetDeviceConfigurationForOpMode();
        this.servo.setDirection(this.config.direction);

        if (this.config.isDualServo) {
            this.secondaryServo = this.robot.hardwareMap.get(Servo.class, this.config.secondaryServoName);
            this.secondaryServo.resetDeviceConfigurationForOpMode();
            this.secondaryServo.setDirection(this.config.direction);
        }

        if (config.controllerInputMethod.equals(Control.Gp2_LeftStickX)) {
            this.addGp2_LeftStickXHandler(event -> {
                double position = event.getPosition();
                if (Boom.this.config.invertInput) {
                    position = -position;
                }
                Boom.this.move(position, Boom.this.config.maxIncrement, Boom.this.config.minPosition, Boom.this.config.maxPosition);
            });
        } else if (config.controllerInputMethod.equals(Control.Gp2_LeftStickY)) {
            this.addGp2_LeftStickYHandler(event -> {
                double position = event.getPosition();
                if (Boom.this.config.invertInput) {
                    position = -position;
                }
                Boom.this.move(position, Boom.this.config.maxIncrement, Boom.this.config.minPosition, Boom.this.config.maxPosition);
            });
        } else if (config.controllerInputMethod.equals(Control.Gp2_RightStickX)) {
            this.addGp2_RightStickXHandler(event -> {
                double position = event.getPosition();
                if (Boom.this.config.invertInput) {
                    position = -position;
                }
                Boom.this.move(position, Boom.this.config.maxIncrement, Boom.this.config.minPosition, Boom.this.config.maxPosition);
            });
        } else if (config.controllerInputMethod.equals(Control.Gp2_RightStickY)) {
            this.addGp2_RightStickYHandler(event -> {
                double position = event.getPosition();
                if (Boom.this.config.invertInput) {
                    position = -position;
                }
                Boom.this.move(position, Boom.this.config.maxIncrement, Boom.this.config.minPosition, Boom.this.config.maxPosition);
            });
        }

        this.setServoPosition(this.config.homePosition);
    }

    /**
     *
     */
    public void run() {
        super.run();
    }

    /**
     *
     * @return
     */
    public double getMaxIncrement() {
        return this.config.maxIncrement;
    }

    /**
     *
     * @return
     */
    public double getMaxPosition() {
        return this.config.maxPosition;
    }

    /**
     *
     * @return
     */
    public double getMinPosition() {
        return this.config.minPosition;
    }

    /**
     *
     * @return The position (0 - 1) of the primary servo
     */
    public double getPosition() {
        return this.servo.getPosition();
    }


    /**
     *
     * @param degrees
     */
    public void gotoDegrees (double degrees)
    {
        double startPosition = this.servo.getPosition();
        double targetPosition = this.calculateTargetPosition(degrees);

        this.addCommand(new GoToPositionCommand(this, startPosition, targetPosition));
    }

    /**
     *
     * @param input
     */
    public boolean move (double input, double maxIncrement, double minPosition, double maxPosition)
    {
        boolean isMin = false;
        boolean isMax = false;

        if (input > 0) {
            double newPos = servo.getPosition() + (maxIncrement * input);

            if (newPos > maxPosition) {
                newPos = maxPosition;
                isMax = true;
            }

            this.setServoPosition(newPos);
        }
        else if (input < 0) {
            double newPos = servo.getPosition() + (maxIncrement * input);

            if (newPos < minPosition) {
                newPos = minPosition;
                isMin = true;
            }

            this.setServoPosition(newPos);
        }

        return isMin || isMax;
    }

    /**
     *
     * @param position
     */
    private void setServoPosition (double position) {
        this.servo.setPosition(position);
        if (this.config.isDualServo) {
            this.secondaryServo.setPosition(1 - position);
        }
    }

    /**
     *
     * @param degrees
     * @return
     */
    double calculateTargetPosition(double degrees)
    {
        double degreesInPosition = Math.abs(degrees * this.config.degree);

        double targetPosition = this.config.zeroDegreePosition;
        if (degrees < 0) {
            targetPosition = targetPosition + degreesInPosition;
        }
        else if (degrees > 0) {
            targetPosition = targetPosition - degreesInPosition;
        }

        return targetPosition;
    }
}
