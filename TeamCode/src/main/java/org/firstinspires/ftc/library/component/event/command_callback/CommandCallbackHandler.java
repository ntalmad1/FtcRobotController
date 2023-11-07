package org.firstinspires.ftc.library.component.event.command_callback;

import org.firstinspires.ftc.library.component.event.EventHandler;

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

    /**
     *
     * @param afterEvent
     */
    void onAfter(CommandAfterEvent afterEvent);
}
