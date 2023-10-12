package org.firstinspires.ftc.teamcode.library.component.event.command_callback;

import org.firstinspires.ftc.teamcode.library.component.event.EventHandler;

/**
 *
 */
public interface CommandCallbackHandler extends EventHandler {

    /**
     *
     * @param successEvent
     */
    void onSuccess (CommandSuccessEvent successEvent);

    /**
     *
     * @param failureEvent
     */
    void onFailure (CommandFailureEvent failureEvent);

}
