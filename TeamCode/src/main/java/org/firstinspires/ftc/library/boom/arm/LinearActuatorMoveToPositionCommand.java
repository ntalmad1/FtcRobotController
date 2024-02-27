package org.firstinspires.ftc.library.boom.arm;

import org.firstinspires.ftc.library.boom.GoToPositionCommand;
import org.firstinspires.ftc.library.component.command.AbstractCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.motor.EncodedMotor;
import org.firstinspires.ftc.library.motor.EncodedMotorGoToPositionCommand;

/**
 *
 */
public class LinearActuatorMoveToPositionCommand extends AbstractCommand {

    /**
     */
    private EncodedMotor motor;

    /**
     */
    private EncodedMotorGoToPositionCommand command;

    /**
     */
    private double power;

    /**
     */
    private int targetPosition;

    /**
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

    public void init () {
        this.command = new EncodedMotorGoToPositionCommand(motor, targetPosition, power);
        command.addCallbackHandler(new CommandCallbackAdapter() {
            public void onSuccess(CommandSuccessEvent successEvent) {
                LinearActuatorMoveToPositionCommand.this.markAsCompleted();
            }
        });

        this.motor.addCommand(command);
        this.setInitialized(true);
    }

    public void run () {
        if (this.isCompleted()) {
            return;
        }
    }

}
