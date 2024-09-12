package org.firstinspires.ftc.teamcode.library.servo;

import org.firstinspires.ftc.library.component.command.AbstractCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;

/**
 *
 */
public class ServoMoveToPositionCommand extends AbstractCommand {

    /**
     */
    private ServoComponent boom;

    /**
     */
    private ServoGoToPositionCommand command;

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
    public ServoMoveToPositionCommand(ServoComponent boom, double targetPosition, double power) {
        this.boom = boom;

        this.power = power;

        this.targetPosition = targetPosition;
    }

    public void init () {
        this.command = new ServoGoToPositionCommand(boom, power, this.boom.getPosition(), targetPosition);
        command.addCallbackHandler(new CommandCallbackAdapter() {
            public void onSuccess(CommandSuccessEvent successEvent) {
                ServoMoveToPositionCommand.this.markAsCompleted();
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
