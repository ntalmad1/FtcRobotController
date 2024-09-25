package org.firstinspires.ftc.teamcode.library.encodedmotor;

import org.firstinspires.ftc.teamcode.library.command.AbstractCommand;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandSuccessEvent;

/**
 *
 */
public class EncodedMotorMoveToPositionCommand extends AbstractCommand {

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
    public EncodedMotorMoveToPositionCommand(EncodedMotor motor, int targetPosition, double power) {
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
                EncodedMotorMoveToPositionCommand.this.markAsCompleted();
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
