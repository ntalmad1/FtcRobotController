package org.firstinspires.ftc.library.component.event.command_callback;

import org.firstinspires.ftc.library.component.command.ICommand;

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
