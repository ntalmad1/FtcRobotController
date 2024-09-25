package org.firstinspires.ftc.teamcode.library.rotator;

import org.firstinspires.ftc.teamcode.library.command.AbstractCommand;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandSuccessEvent;

/**
 *
 */
public class RotatorMoveToPositionCommand extends AbstractCommand {

    /**
     */
    private Rotator boom;

    /**
     */
    private RotatorGoToPositionCommand command;

    /**
     */
    private double power;

    /**
     */
    private double targetPosition;

    /**
     *
     * @param boom
     * @param targetPosition
     * @param power
     */
    public RotatorMoveToPositionCommand(Rotator boom, double targetPosition, double power) {
        this.boom = boom;

        this.power = power;

        this.targetPosition = targetPosition;
    }

    public void init () {
        this.command = new RotatorGoToPositionCommand(boom, power, this.boom.getPosition(), targetPosition);
        command.addCallbackHandler(new CommandCallbackAdapter() {
            public void onSuccess(CommandSuccessEvent successEvent) {
                RotatorMoveToPositionCommand.this.markAsCompleted();
            }
        });

        this.boom.addCommand(command);

        this.setInitialized(true);
    }

    public void run () {
        if (this.isCompleted()) {
            return;
        }
    }

}
