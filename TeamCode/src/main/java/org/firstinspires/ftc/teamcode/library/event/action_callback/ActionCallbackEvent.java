package org.firstinspires.ftc.teamcode.library.event.action_callback;

import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.library.command.ICommand;
import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public abstract class ActionCallbackEvent extends Event<ActionCallbackHandler> {

    /**
     */
    public final static EventType<ActionCallbackHandler> TYPE = new EventType<ActionCallbackHandler>();

    /**
     *
     */
    public enum Type {
        /**
         */
        SUCCESS,

        /**
         */
        FAILURE,

        /**
         */
        AFTER
    }

    /**
     */
    private Action action;

    /**
     */
    private Type callbackType;

    /**
     */
    private Exception error;

    /**
     * Constructor
     *
     * @param commmandCallbackType
     * @param action
     */
    public ActionCallbackEvent(Type commmandCallbackType, Action action) {
        this.callbackType = commmandCallbackType;
        this.action = action;
    }

    /**
     *
     * @return
     */
    @Override
    public EventType<ActionCallbackHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @return
     */
    public Action getAction () {
        return this.action;
    }

    /**
     *
     * @return
     */
    public Exception getError () {
        return this.error;
    }


    /**
     *
     * @return
     */
    public boolean isSuccess () {
        return this.callbackType.equals(Type.SUCCESS);
    }

    /**
     *
     * @return
     */
    public boolean isFailure () {
        return this.callbackType.equals(Type.FAILURE);
    }

    /**
     *
     * @param e
     */
    protected void setError (Exception e) {
        this.error = e;
    }
}
