package org.firstinspires.ftc.teamcode.library.event.command_callback;

import org.firstinspires.ftc.teamcode.library.command.ICommand;

/**
 *
 */
public class CommandFailureEvent extends CommandCallbackEvent {

    /**
     *
     * @param command
     */
    public CommandFailureEvent(ICommand command, Exception error) {
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
