package org.firstinspires.ftc.teamcode.library.event.command_callback;

import org.firstinspires.ftc.teamcode.library.command.ICommand;

/**
 *
 */
public class CommandAfterEvent extends CommandCallbackEvent {

    /**
     *
     * @param command
     */
    public CommandAfterEvent(ICommand command) {
        super(Type.AFTER, command);
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle( CommandCallbackHandler handler) {
        handler.onAfter(this);
    }
}
