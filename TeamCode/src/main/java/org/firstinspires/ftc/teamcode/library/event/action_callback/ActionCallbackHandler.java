package org.firstinspires.ftc.teamcode.library.event.action_callback;

import org.firstinspires.ftc.teamcode.library.event.EventHandler;

/**
 *
 */
public interface ActionCallbackHandler extends EventHandler {

    /**
     *
     * @param successEvent the success event
     */
    void onSuccess (ActionSuccessEvent successEvent);

    /**
     *
     * @param failureEvent the failure event
     */
    void onFailure (ActionFailureEvent failureEvent);

    /**
     *
     * @param afterEvent the after event
     */
    void onAfter(ActionAfterEvent afterEvent);
}
