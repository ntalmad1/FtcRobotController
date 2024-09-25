package org.firstinspires.ftc.teamcode.library.rotator;

import org.firstinspires.ftc.teamcode.library.command.AbstractCommand;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandSuccessEvent;

/**
 *
 */
public class RotatorMoveToDegreesCommand extends AbstractCommand {

    /**
     */
    private Rotator boom;

    /**
     */
    private RotatorGoToDegreesCommand command;

    /**
     *
     * @param boom
     * @param degrees
     */
    public RotatorMoveToDegreesCommand(Rotator boom, double degrees, double power) {
        this.boom = boom;

        this.command = new RotatorGoToDegreesCommand(boom, degrees, power);
        command.addCallbackHandler(new CommandCallbackAdapter() {
            public void onSuccess(CommandSuccessEvent successEvent) {
                RotatorMoveToDegreesCommand.this.markAsCompleted();
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
