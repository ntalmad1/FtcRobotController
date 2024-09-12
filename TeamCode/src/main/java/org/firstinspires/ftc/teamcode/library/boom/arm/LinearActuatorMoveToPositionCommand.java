package org.firstinspires.ftc.teamcode.library.boom.arm;

import org.firstinspires.ftc.teamcode.library.command.AbstractCommand;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.teamcode.library.motor.EncodedMotor;
import org.firstinspires.ftc.teamcode.library.motor.EncodedMotorGoToPositionCommand;

/**
 *
 */
public class LinearActuatorMoveToPositionCommand extends AbstractCommand {

    /**
     *
     */
    private final EncodedMotor motor;

    /**
     *
     */
    private final double power;

    /**
     *
     */
    private final int targetPosition;

    /**
     * Constructor
     *
     * @param motor
     * @param targetPosition
     * @param power
     */
    public LinearActuatorMoveToPositionCommand(EncodedMotor motor, int targetPosition, double power) {
        this.motor = motor;

        this.power = power;

        this.targetPosition = targetPosition;
    }

    /**
     *
     */
    public void init() {
        EncodedMotorGoToPositionCommand command = new EncodedMotorGoToPositionCommand(motor, targetPosition, power);

        command.addCallbackHandler(new CommandCallbackAdapter() {
            public void onSuccess(CommandSuccessEvent successEvent) {
                LinearActuatorMoveToPositionCommand.this.markAsCompleted();
            }
        });

        this.motor.addCommand(command);
        this.setInitialized(true);
    }

    /**
     *
     */
    public void run() {}
}
