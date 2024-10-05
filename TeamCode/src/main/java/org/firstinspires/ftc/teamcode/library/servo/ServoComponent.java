package org.firstinspires.ftc.teamcode.library.servo;

import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.utility.Control;

/**
 *
 */
public class ServoComponent extends Component {

    /**
     *
     */
    private final ServoComponentConfig config;

    /**
     * Reverse forwards vs backwards for calculating position in degrees
     */
    private boolean inverted = false;

    /**
     *
     */
    private double maxIncrement;

    /**
     *
     */
    private com.qualcomm.robotcore.hardware.Servo servo;

    /**
     *
     */
    private com.qualcomm.robotcore.hardware.Servo secondaryServo;


    /**
     * Constructor
     *
     * @param config The configuration values of the boom
     */
    public ServoComponent(ServoComponentConfig config) {
        super(config.robot);

        this.config = config;

        this.maxIncrement = config.maxIncrement;
    }

    /**
     *
     * @return
     */
    public ServoComponentConfig getConfig () {
        return this.config;
    }

    /**
     *
     * @return The max amount to set the servo position to each cycle
     */
    public double getMaxIncrement() {
        return this.maxIncrement;
    }

    /**
     *
     * @return The max position for the servo
     */
    public double getMaxPosition() {
        return this.config.maxPosition;
    }

    /**
     *
     * @return The min position for the servo
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
     * @return The position of the boom in degrees, degrees from the 0 degree position
     * forwards 0 +
     * backwards 0 -
     */
    public double getPositionDegrees () {
        double servoPosition = this.servo.getPosition();

        double offset = this.config.zeroDegreePosition - servoPosition;

        double degrees = (offset / this.config.degree) / this.config.gearRatio;

        if (this.inverted) {
            degrees = degrees * (double)-1;
        }

        return degrees;
    }

    /**
     *
     * @param degrees Move the boom to degrees, degrees from the 0 degree position
     * forwards 0 +
     * backwards 0 -
     */
    public void gotoDegrees (double degrees)
    {
        double startPosition = this.servo.getPosition();
        double targetPosition = this.calculateTargetPosition(degrees);

        this.addCommand(new ServoGoToPositionCommand(this, this.getMaxIncrement(), startPosition, targetPosition));
    }

    /**
     *
     * @param targetPosition Move the boom to servo position
     */
    public void gotoPosition (double targetPosition)
    {
        this.gotoPosition(targetPosition, this.getMaxIncrement());
    }

    public void gotoPosition (double targetPosition, double maxIncrement) {
        double startPosition = this.servo.getPosition();
        this.addCommand(new ServoGoToPositionCommand(this, maxIncrement, startPosition, targetPosition));
    }

    /**
     *
     */
    public void init() {
        super.init();

        this.servo = this.robot.hardwareMap.get(com.qualcomm.robotcore.hardware.Servo.class, this.config.servoName);
        this.servo.resetDeviceConfigurationForOpMode();
        this.servo.setDirection(this.config.direction);

        if (this.config.isDualServo) {
            this.secondaryServo = this.robot.hardwareMap.get(com.qualcomm.robotcore.hardware.Servo.class, this.config.secondaryServoName);
            this.secondaryServo.resetDeviceConfigurationForOpMode();
            this.secondaryServo.setDirection(this.config.direction);
        }

        if (config.controllerInputMethod.equals(Control.Gp2_LeftStickX)) {
            this.addGp2_LeftStick_X_Handler(event -> {
                double position = event.getPosition();
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        } else if (config.controllerInputMethod.equals(Control.Gp2_LeftStickY)) {
            this.addGp2_LeftStick_Y_Handler(event -> {
                double position = event.getPosition();
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        } else if (config.controllerInputMethod.equals(Control.Gp2_RightStickX)) {
            this.addGp2_RightStick_X_Handler(event -> {
                double position = event.getPosition();
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        } else if (config.controllerInputMethod.equals(Control.Gp1_RightStickX)) {
            this.addGp1_RightStick_X_Handler(event -> {
                double position = event.getPosition();
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        } else if (config.controllerInputMethod.equals(Control.Gp2_RightStickY)) {
            this.addGp2_RightStick_Y_Handler(event -> {
                double position = event.getPosition();
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        }

        if (config.controllerInputMethod.equals(Control.Gp2_Dpad_Up)
                || (config.controllerInputMethod2 != null && config.controllerInputMethod2.equals(Control.Gp2_Dpad_Up))) {
            this.addGp2_Dpad_Up_DownHandler(event -> {
                double position = 1;
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        }

        if (config.controllerInputMethod.equals(Control.Gp2_Dpad_Right)
                || (config.controllerInputMethod2 != null && config.controllerInputMethod2.equals(Control.Gp2_Dpad_Right))) {
            this.addGp2_Dpad_Right_DownHandler(event -> {
                double position = 1;
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        }

        if (config.controllerInputMethod.equals(Control.Gp2_Dpad_Down)
                || (config.controllerInputMethod2 != null && config.controllerInputMethod2.equals(Control.Gp2_Dpad_Down))) {
            this.addGp2_Dpad_Down_DownHandler(event -> {
                double position = -1;
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);

                this.telemetry.addLine("Dpad Down...");
                this.telemetry.update();

            });
        }

        if (config.controllerInputMethod.equals(Control.Gp2_Dpad_Left)
                || (config.controllerInputMethod2 != null && config.controllerInputMethod2.equals(Control.Gp2_Dpad_Left))) {
            this.addGp2_Dpad_Left_DownHandler(event -> {
                double position = -1;
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        }

        // this.setServoPosition(this.config.homePosition);
    }

    /**
     *
     * @return
     */
    public boolean move (double input) {
        return this.move(
                input,
                this.getConfig().maxIncrement,
                this.getConfig().maxIncrement,
                this.getConfig().maxPosition);
    }

    /**
     *
     * @param input the value of the game pad stick
     * @param maxIncrement the amount to increment or decrement the servo position by each cycle
     * @param minPosition the min position of the servo
     * @param maxPosition the max position of the servo
     * @return true if the boom is at its min or max position
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
     */
    public void run() {
        super.run();

        if (this.config.debug) {
            this.telemetry.addData("servo position: ", "%2f", this.servo.getPosition());
            this.telemetry.addData( "servo position degrees: ", "%2f", this.getPositionDegrees());
            this.telemetry.addLine("servo direction: " + this.servo.getDirection().toString());

            if (this.config.isDualServo) {
                this.telemetry.addData("secondary servo position: ", "%2f", this.secondaryServo.getPosition());
                this.telemetry.addLine("secondary servo direction: " + this.servo.getDirection().toString());
            }

            this.telemetry.update();
        }
    }

    /**
     *
     * @param inverted True to invert forwards vs backwards for calculation position in degrees
     */
    public void setInverted(boolean inverted)
    {
        this.inverted = inverted;
    }

    /**
     *
     * @param position
     */
    public void setPosition(double position) {
        this.setServoPosition(position);
    }

    /**
     *
     * @param position the position to set the servo to
     */
    private void setServoPosition(double position) {
        this.servo.setPosition(position);
        if (this.config.isDualServo) {
            this.secondaryServo.setPosition(1 - this.config.secondaryServoOffset - position);
        }
    }

    /**
     *
     * @param degrees Convert degrees to servo position
     * @return the servo position
     */
    protected double calculateTargetPosition(double degrees)
    {
        if (this.inverted) {
            degrees = degrees * (double)-1;
        }

        degrees = degrees * this.config.gearRatio;

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

    public void setMaxIncrement(double increment) {
    }
}
