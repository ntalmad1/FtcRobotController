package org.firstinspires.ftc.teamcode.library.servo;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
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
     * @param servoPos
     * @return
     */
    public AbstractAction gotoPositionAction(ServoPos servoPos) {
        if (servoPos == null) {
            return this.gotoPositionAction(this.getPosition(), 1);
        }

        if (servoPos.getPos() == null) {
            return this.gotoPositionAction(this.getPosition(), servoPos.getIncrement());
        }

        return this.gotoPositionAction(servoPos.getPos(), servoPos.getIncrement());
    }

    /**
     *
     * @param targetPosition
     * @param maxIncrement
     * @return
     */
    public AbstractAction gotoPositionAction (double targetPosition, double maxIncrement) {
        double startPosition = this.servo.getPosition();
        return new ServoGoToPositionAction(this, maxIncrement, startPosition, targetPosition);
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

        if (Control.Gp2_LeftStickX.equals(config.controllerInputMethod)) {
            this.addGp2_LeftStick_X_Handler(event -> {
                double position = event.getPosition();
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        } else if (Control.Gp2_LeftStickY.equals(config.controllerInputMethod)) {
            this.addGp2_LeftStick_Y_Handler(event -> {
                double position = event.getPosition();
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        } else if (Control.Gp2_RightStickX.equals(config.controllerInputMethod)) {
            this.addGp2_RightStick_X_Handler(event -> {
                double position = event.getPosition();
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        } else if (Control.Gp1_RightStickX.equals(config.controllerInputMethod)) {
            this.addGp1_RightStick_X_Handler(event -> {
                double position = event.getPosition();
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        } else if (Control.Gp2_RightStickY.equals(config.controllerInputMethod)) {
            this.addGp2_RightStick_Y_Handler(event -> {
                double position = event.getPosition();
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        }

        if (Control.Gp2_Dpad_Up.equals(config.controllerInputMethod)
                || (config.controllerInputMethod2 != null && config.controllerInputMethod2.equals(Control.Gp2_Dpad_Up))) {
            this.addGp2_Dpad_Up_DownHandler(event -> {
                double position = 1;
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        }

        if (Control.Gp2_Dpad_Right.equals(config.controllerInputMethod)
                || (config.controllerInputMethod2 != null && config.controllerInputMethod2.equals(Control.Gp2_Dpad_Right))) {
            this.addGp2_Dpad_Right_DownHandler(event -> {
                double position = 1;
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        }

        if (Control.Gp2_Dpad_Down.equals(config.controllerInputMethod)
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

        if (Control.Gp2_Dpad_Left.equals(config.controllerInputMethod)
                || (config.controllerInputMethod2 != null && config.controllerInputMethod2.equals(Control.Gp2_Dpad_Left))) {
            this.addGp2_Dpad_Left_DownHandler(event -> {
                double position = -1;
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        }

        if (!config.lazyInit) {
            this.setPosition(this.config.homePosition);
        }
    }

    /**
     *
     * @param control
     */
    public void addControl(Control control) {
        if (Control.Gp2_Dpad_Left.equals(control)
            || Control.Gp2_Dpad_LeftRight.equals(control)) {
            this.addGp2_Dpad_Left_DownHandler(event -> {
                double position = -1;
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        }

        if (Control.Gp2_Dpad_Right.equals(control)
            || Control.Gp2_Dpad_LeftRight.equals(control)) {
            this.addGp2_Dpad_Right_DownHandler(event -> {
                double position = 1;
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        }

        if (Control.Gp1_Dpad_Left.equals(control)
                || Control.Gp1_Dpad_LeftRight.equals(control)) {
            this.addGp1_Dpad_Left_DownHandler(event -> {
                double position = -1;
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        }

        if (Control.Gp1_Dpad_Right.equals(control)
                || Control.Gp1_Dpad_LeftRight.equals(control)) {
            this.addGp1_Dpad_Right_DownHandler(event -> {
                double position = 1;
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        }

        if (Control.Gp2_LeftStickX.equals(config.controllerInputMethod)) {
            this.addGp2_LeftStick_X_Handler(event -> {
                double position = event.getPosition();
                if (ServoComponent.this.config.invertInput) {
                    position = -position;
                }
                ServoComponent.this.move(position, ServoComponent.this.maxIncrement, ServoComponent.this.config.minPosition, ServoComponent.this.config.maxPosition);
            });
        }
    }

    /**
     *
     * @return
     */
    public boolean move (double input) {
        return this.move(
                input,
                this.getConfig().maxIncrement,
                this.getConfig().minPosition,
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

            this.setPosition(newPos);
        }
        else if (input < 0) {
            double newPos = servo.getPosition() + (maxIncrement * input);

            if (newPos < minPosition) {
                newPos = minPosition;
                isMin = true;
            }

            this.setPosition(newPos);
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
     * @param position the position to set the servo to
     */
    public void setPosition(double position) {
        this.servo.setPosition(position);
        if (this.config.isDualServo) {
            this.secondaryServo.setPosition(1 - this.config.secondaryServoOffset - position);
        }
    }
}
