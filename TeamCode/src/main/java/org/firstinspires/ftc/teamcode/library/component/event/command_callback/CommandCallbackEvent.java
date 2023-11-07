package org.firstinspires.ftc.teamcode.library.component.event.command_callback;

import org.firstinspires.ftc.teamcode.library.component.command.Command;
import org.firstinspires.ftc.teamcode.library.component.event.Event;
import org.firstinspires.ftc.teamcode.library.component.event.EventType;

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
        FAILURE
    }

    /**
     */
    private Command command;

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
    public CommandCallbackEvent (Type commmandCallbackType, Command command) {
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
