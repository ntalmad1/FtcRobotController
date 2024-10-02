package org.firstinspires.ftc.teamcode.library.event.action_callback;

import com.acmerobotics.roadrunner.Action;

/**
 *
 */
public class ActionFailureEvent extends ActionCallbackEvent {

    /**
     * Constructor
     *
     * @param action
     */
    public ActionFailureEvent(Action action, Exception error) {
        super(Type.FAILURE, action);
        this.setError(error);
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle( ActionCallbackHandler handler) {
        handler.onFailure(this);
    }
}
