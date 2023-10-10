package org.firstinspires.ftc.teamcode.library.component.event.command_callback;

import org.firstinspires.ftc.teamcode.library.component.event.EventType;

public class CommandSuccessEvent extends CommandCallbackEvent {




    @Override
    public void handle(CommandCallbackHandler handler) {
        handler.onSuccess(this);
    }
}
