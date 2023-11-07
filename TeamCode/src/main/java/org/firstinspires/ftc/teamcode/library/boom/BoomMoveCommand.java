package org.firstinspires.ftc.teamcode.library.boom;

import org.firstinspires.ftc.teamcode.library.component.command.AbstractCommand;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandSuccessEvent;

/**
 *
 */
public class BoomMoveCommand extends AbstractCommand {

    /**
     */
    private Boom boom;

    /**
     */
    private GoToDegreesCommand command;

    /**
     *
     * @param boom
     * @param degrees
     */
    public BoomMoveCommand(Boom boom, double degrees, double power) {
        this.boom = boom;

        this.command = new GoToDegreesCommand(boom, degrees, power);
        command.addCallbackHandler(new CommandCallbackAdapter() {
            public void onSuccess(CommandSuccessEvent successEvent) {
                BoomMoveCommand.this.markAsCompleted();
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
