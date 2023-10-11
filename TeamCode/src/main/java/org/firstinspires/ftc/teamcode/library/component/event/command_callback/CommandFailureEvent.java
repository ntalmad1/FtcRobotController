package org.firstinspires.ftc.teamcode.library.component.event.command_callback;

import org.firstinspires.ftc.teamcode.library.component.command.Command;

/**
 *
 */
public class CommandFailureEvent extends CommandCallbackEvent {

    /**
     *
     * @param command
     */
    public CommandFailureEvent(Command command, Exception error) {
        super(Type.FAILURE, command);
        this.setError(error);
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle( CommandCallbackHandler handler) {
        handler.onFailure(this);
    }
}
