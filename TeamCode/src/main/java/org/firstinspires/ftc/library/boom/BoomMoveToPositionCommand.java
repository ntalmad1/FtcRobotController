package org.firstinspires.ftc.library.boom;

import org.firstinspires.ftc.library.component.command.AbstractCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;

/**
 *
 */
public class BoomMoveToPositionCommand extends AbstractCommand {

    /**
     */
    private Boom boom;

    /**
     */
    private GoToPositionCommand command;

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
    public BoomMoveToPositionCommand(Boom boom, double targetPosition, double power) {
        this.boom = boom;

        this.power = power;

        this.targetPosition = targetPosition;


    }

    public void init () {
        this.command = new GoToPositionCommand(boom, power, this.boom.getPosition(), targetPosition);
        command.addCallbackHandler(new CommandCallbackAdapter() {
            public void onSuccess(CommandSuccessEvent successEvent) {
                BoomMoveToPositionCommand.this.markAsCompleted();
            }
        });

        this.boom.addCommand(command);
    }

    public void run () {
        if (this.isCompleted()) {
            return;
        }
    }

}
