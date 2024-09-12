package org.firstinspires.ftc.teamcode.library.component.command;

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
