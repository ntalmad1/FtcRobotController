package org.firstinspires.ftc.teamcode.library.component.event.command_callback;

import org.firstinspires.ftc.teamcode.library.component.command.Command;

/**
 *
 */
public class CommandSuccessEvent extends CommandCallbackEvent {

    /**
     *
     * @param command
     */
    public CommandSuccessEvent (Command command) {
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
