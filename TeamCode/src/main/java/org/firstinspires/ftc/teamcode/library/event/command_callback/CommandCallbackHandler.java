package org.firstinspires.ftc.teamcode.library.event.command_callback;

import org.firstinspires.ftc.teamcode.library.event.EventHandler;

/**
 *
 */
public interface CommandCallbackHandler extends EventHandler {

    /**
     *
     * @param successEvent the success event
     */
    void onSuccess (CommandSuccessEvent successEvent);

    /**
     *
     * @param failureEvent the failure event
     */
    void onFailure (CommandFailureEvent failureEvent);

    /**
     *
     * @param afterEvent the after event
     */
    void onAfter(CommandAfterEvent afterEvent);
}
