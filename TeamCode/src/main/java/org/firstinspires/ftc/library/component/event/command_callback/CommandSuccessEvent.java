package org.firstinspires.ftc.library.component.event.command_callback;

import org.firstinspires.ftc.library.component.command.ICommand;

/**
 *
 */
public class CommandSuccessEvent extends CommandCallbackEvent {

    /**
     *
     * @param command
     */
    public CommandSuccessEvent (ICommand command) {
        super(Type.SUCCESS, command);
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle( CommandCallbackHandler handler) {
        handler.onSuccess(this);
    }
}
