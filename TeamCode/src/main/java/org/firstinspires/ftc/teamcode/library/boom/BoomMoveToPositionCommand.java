package org.firstinspires.ftc.teamcode.library.boom;

import org.firstinspires.ftc.teamcode.library.component.command.AbstractCommand;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandSuccessEvent;

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
     *
     * @param boom
     * @param startPosition
     * @param targetPosition
     * @param power
     */
    public BoomMoveToPositionCommand(Boom boom, double startPosition, double targetPosition, double power) {
        this.boom = boom;

        this.command = new GoToPositionCommand(boom, power, startPosition, targetPosition);
        command.addCallbackHandler(new CommandCallbackAdapter() {
            public void onSuccess(CommandSuccessEvent successEvent) {
                BoomMoveToPositionCommand.this.markAsCompleted();
            }
        });
    }

    public void init () {
        this.boom.addCommand(command);
    }

    public void run () {
        if (this.isCompleted()) {
            return;
        }
    }

}
