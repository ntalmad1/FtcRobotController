package org.firstinspires.ftc.teamcode.library.component.command;

/**
 *
 */
public class AbstractCommand implements Command {

    /**
     */
    protected boolean completed;

    /**
     */
    private boolean initialized = false;

    /**
     *
     * @return
     */
    @Override
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     *
     */
    public void markAsCompleted () {
        this.completed = true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isInitialized () {
        return this.initialized;
    }

    /**
     *
     * @param initialized
     */
    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    /**
     *
     */
    public void init () {};
}
