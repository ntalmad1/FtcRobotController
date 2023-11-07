package org.firstinspires.ftc.library.component.event.command_callback;

import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public abstract class CommandCallbackEvent extends Event<CommandCallbackHandler> {

    /**
     */
    public final static EventType<CommandCallbackHandler> TYPE = new EventType<CommandCallbackHandler>();

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
    private ICommand command;

    /**
     */
    private Type commandCallbackType;

    /**
     */
    private Exception error;

    /**
     * Constructor
     *
     * @param commmandCallbackType
     * @param command
     */
    public CommandCallbackEvent (Type commmandCallbackType, ICommand command) {
        this.commandCallbackType = commmandCallbackType;
        this.command = command;
    }

    /**
     *
     * @return
     */
    @Override
    public EventType<CommandCallbackHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @return
     */
    public boolean isSuccess () {
        return this.commandCallbackType.equals(Type.SUCCESS);
    }

    /**
     *
     * @return
     */
    public boolean isFailure () {
        return this.commandCallbackType.equals(Type.FAILURE);
    }

    /**
     *
     * @param e
     */
    protected void setError (Exception e) {
        this.error = e;
    }
}
