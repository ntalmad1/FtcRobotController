package org.firstinspires.ftc.library.component.command;

/**
 *
 */
public abstract class AbstractSynchronousCommand extends AbstractCommand {

    /**
     * Constructor
     */
    public AbstractSynchronousCommand() {
        super();
        this.setSynchronous(true);
    }
}
