package org.firstinspires.ftc.teamcode.library.event.action_callback;

import com.acmerobotics.roadrunner.Action;

/**
 *
 */
public class ActionCallbackAdapter implements ActionCallbackHandler {

    /**
     */
    protected Action action;

    /**
     * Constructor
     *
     */
    public ActionCallbackAdapter() {

    }

    /**
     *
     * @param action
     */
    public ActionCallbackAdapter(Action action) {
        this.action = action;
    }

    /**
     *
     * @param successEvent the success event
     */
    @Override
    public void onSuccess(ActionSuccessEvent successEvent) {

    }

    /**
     *
     * @param failureEvent the failure event
     */
    @Override
    public void onFailure(ActionFailureEvent failureEvent) {

    }

    /**
     *
     * @param afterEvent the after event
     */
    @Override
    public void onAfter(ActionAfterEvent afterEvent) {

    }
}
