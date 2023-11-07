package org.firstinspires.ftc.library.boom;

import org.firstinspires.ftc.library.component.command.AbstractCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;

/**
 *
 */
public class BoomMoveToDegreesCommand extends AbstractCommand {

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
    public BoomMoveToDegreesCommand(Boom boom, double degrees, double power) {
        this.boom = boom;

        this.command = new GoToDegreesCommand(boom, degrees, power);
        command.addCallbackHandler(new CommandCallbackAdapter() {
            public void onSuccess(CommandSuccessEvent successEvent) {
                BoomMoveToDegreesCommand.this.markAsCompleted();
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
