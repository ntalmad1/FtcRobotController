package org.firstinspires.ftc.teamcode.library.arm;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.Control;
import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.component.command.Command;
import org.firstinspires.ftc.teamcode.library.component.command.GoToDegreesCommand;
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
        super.runCommand(command);

        if (command instanceof GoToDegreesCommand) {
            GoToDegreesCommand goToDegreesCommand = (GoToDegreesCommand)command;

            this.telemetry.addData("degrees: ", "%2f", goToDegreesCommand.getDegrees());
            this.telemetry.addData("servo pos: ", "%2f", this.servo.getPosition());
            this.telemetry.addData("target pos: ", "%2f", goToDegreesCommand.getTargetPosition());

            if (!goToDegreesCommand.isInitialized()) {
                goToDegreesCommand.setStartPosition(this.servo.getPosition());
                goToDegreesCommand.setTargetPosition(this.calculateTargetPosition(goToDegreesCommand.getDegrees()));
                goToDegreesCommand.setInitialized(true);
            }
        }

        if (command instanceof GoToPositionCommand) {
            this.runGoToPositionCommand((GoToPositionCommand)command);
        }
    }

    /**
     *
     * @param degrees
     */
    public void gotoDegrees (double degrees)
    {
        double startPosition = this.servo.getPosition();
        double targetPosition = this.calculateTargetPosition(degrees);

        this.addCommand(new GoToPositionCommand(startPosition, targetPosition));
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

            servo.setPosition(newPos);
        }
        else if (input < 0) {
            double newPos = servo.getPosition() + (maxIncrement * input);

            if (newPos < minPosition) {
                newPos = minPosition;
                isMin = true;
            }

            servo.setPosition(newPos);
        }

        return isMin || isMax;
    }

    protected void runGoToPositionCommand (GoToPositionCommand goToCommand){

        if (goToCommand.getTargetPosition() == goToCommand.getStartPosition()) {
            goToCommand.markAsCompleted();
            return;
        }

        Direction direction = goToCommand.getTargetPosition() > goToCommand.getStartPosition() ? Direction.REVERSE : Direction.FORWARD;

        double currentPosition = this.servo.getPosition();

        if (direction.equals(Direction.FORWARD)) {
            telemetry.addLine("forwards");

            if (currentPosition <= goToCommand.getTargetPosition()) {
                goToCommand.markAsCompleted();
                return;
            }

            if (this.move(-1, this.config.maxIncrement, goToCommand.getTargetPosition(), this.config.maxPosition)) {
                goToCommand.markAsCompleted();
                return;
            }
        }
        else if (direction.equals(Direction.REVERSE)) {

            telemetry.addLine("reversing");

            if (currentPosition >= goToCommand.getTargetPosition()) {
                goToCommand.markAsCompleted();
                return;
            }

            if (this.move(1, this.config.maxIncrement, this.config.minPosition, goToCommand.getTargetPosition())) {
                goToCommand.markAsCompleted();
                return;
            }
        }
    }

    /**
     *
     * @param degrees
     * @return
     */
    private double calculateTargetPosition (double degrees)
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
