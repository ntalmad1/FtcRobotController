package org.firstinspires.ftc.teamcode.library.arm;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.Control;
import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.component.command.Command;
import org.firstinspires.ftc.teamcode.library.component.command.GoToPositionCommand;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_x.Gp2_LeftStickXEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_x.Gp2_LeftStickXHandler;

/**
 *
 */
public class Boom extends Component
{
    /**
     *
     */
    public enum Direction {

        /**
         */
        FORWARD,

        /**
         */
        REVERSE
    }

    /**
     */
    private BoomConfiguration config;

    /**
     *
     */
    private Servo servo;

    /**
     *
     * @param config
     */
    public Boom(BoomConfiguration config) {
        super(config.robot);

        this.config = config;
    }

    /**
     *
     */
    public void init ()
    {
        super.init();

        this.servo = this.robot.hardwareMap.get(Servo.class, this.config.deviceName);
        this.servo.resetDeviceConfigurationForOpMode();
        this.servo.setDirection(this.config.direction);

        if (config.controllerInputMethod.equals(Control.Gp2_LeftStickX))
        {
            this.addGp2_LeftStickXHandler(new Gp2_LeftStickXHandler() {
                public void onGp2_LeftStickX(Gp2_LeftStickXEvent event) {
                    Boom.this.move(event.getPosition(), Boom.this.config.maxIncrement, Boom.this.config.minPosition, Boom.this.config.maxPosition);
                }
            });
        }

        this.servo.setPosition(this.config.homePosition);
    }

    /**
     *
     */
    public void run ()
    {
        super.run();
    }

    /**
     *
     * @param command
     */
    public void runCommand (Command command)
    {
        if (command instanceof GoToPositionCommand)
        {
            GoToPositionCommand goToCommand = (GoToPositionCommand)command;

            if (goToCommand.getTargetPosition() == goToCommand.getStartPosition()) {
                goToCommand.markAsCompleted();
                return;
            }

            Direction direction = goToCommand.getTargetPosition() > goToCommand.getStartPosition() ? Direction.REVERSE : Direction.FORWARD;

            double currentPosition = this.servo.getPosition();

            if (direction.equals(Direction.FORWARD)) {
                if (goToCommand.getTargetPosition() <= currentPosition) {
                    goToCommand.markAsCompleted();
                    return;
                }

                this.move(-1, this.config.maxIncrement, goToCommand.getTargetPosition(), this.config.maxPosition);
            }
            else if (direction.equals(Direction.REVERSE)) {
                if (goToCommand.getTargetPosition() >= currentPosition) {
                    goToCommand.markAsCompleted();
                    return;
                }

                this.move(1, this.config.maxIncrement, this.config.minPosition, goToCommand.getTargetPosition());
            }
        }
    }



    /**
     *
     * @param degrees
     */
    public void gotoDegrees (double degrees)
    {
        double position = Math.abs(degrees * this.config.degree);

        double adjustedPosition = this.config.zeroDegreePosition;

        if (degrees < 0) {
            adjustedPosition = adjustedPosition + position;
        }
        else if (degrees > 0) {
            adjustedPosition = adjustedPosition - position;
        }

        this.addCommand(new GoToPositionCommand(this.servo.getPosition(), adjustedPosition));
    }

    /**
     *
     * @param input
     */
    public void move (double input, double maxIncrement, double minPosition, double maxPosition)
    {
        if (input > 0) {
            double newPos = servo.getPosition() + (maxIncrement * input);

            if (newPos > maxPosition) {
                newPos = maxPosition;
            }

            servo.setPosition(newPos);
        }
        else if (input < 0) {
            double newPos = servo.getPosition() + (maxIncrement * input);

            if (newPos < minPosition) {
                newPos = minPosition;
            }

            servo.setPosition(newPos);
        }
    }
}
