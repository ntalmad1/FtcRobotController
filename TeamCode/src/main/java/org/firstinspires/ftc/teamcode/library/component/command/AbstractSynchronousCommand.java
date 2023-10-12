package org.firstinspires.ftc.teamcode.library.component.command;

/**
 *
 */
public abstract class AbstractSynchronousCommand extends AbstractCommand {

    /**
     *
     */
    public AbstractSynchronousCommand() {
        super();
        this.setSynchronous(true);
    }
}
