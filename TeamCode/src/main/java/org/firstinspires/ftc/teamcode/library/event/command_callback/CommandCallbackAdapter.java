package org.firstinspires.ftc.teamcode.library.event.command_callback;

import org.firstinspires.ftc.teamcode.library.command.ICommand;

/**
 *
 */
public class CommandCallbackAdapter implements CommandCallbackHandler {

    protected ICommand command;

    public CommandCallbackAdapter () {

    }

    public CommandCallbackAdapter (ICommand command) {
        this.command = command;
    }

    @Override
    public void onSuccess(CommandSuccessEvent successEvent) {

    }

    @Override
    public void onFailure(CommandFailureEvent failureEvent) {

    }

    @Override
    public void onAfter(CommandAfterEvent afterEvent) {

    }
}
