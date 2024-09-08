package org.firstinspires.ftc.library.servo;

import org.firstinspires.ftc.library.component.command.AbstractCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;

/**
 *
 */
public class ServoMoveToDegreesCommand extends AbstractCommand {

    /**
     */
    private ServoComponent boom;

    /**
     */
    private ServoGoToDegreesCommand command;

    /**
     *
     * @param boom
     * @param degrees
     */
    public ServoMoveToDegreesCommand(ServoComponent boom, double degrees, double power) {
        this.boom = boom;

        this.command = new ServoGoToDegreesCommand(boom, degrees, power);
        command.addCallbackHandler(new CommandCallbackAdapter() {
            public void onSuccess(CommandSuccessEvent successEvent) {
                ServoMoveToDegreesCommand.this.markAsCompleted();
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
