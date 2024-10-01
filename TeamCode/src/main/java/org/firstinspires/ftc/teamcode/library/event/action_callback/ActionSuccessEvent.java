package org.firstinspires.ftc.teamcode.library.event.action_callback;

import com.acmerobotics.roadrunner.Action;

/**
 *
 */
public class ActionSuccessEvent extends ActionCallbackEvent {

    /**
     * Constructor
     *
     * @param action
     */
    public ActionSuccessEvent(Action action) {
        super(Type.SUCCESS, action);
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle( ActionCallbackHandler handler) {
        handler.onSuccess(this);
    }
}
