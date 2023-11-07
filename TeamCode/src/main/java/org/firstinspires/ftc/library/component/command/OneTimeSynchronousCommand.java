package org.firstinspires.ftc.library.component.command;

/**
 *
 */
public abstract class OneTimeSynchronousCommand extends AbstractSynchronousCommand {

    private boolean firstTime = true;

    public void run () {
        if (firstTime) {
            this.firstTime = false;
            this.runOnce(this);
        }
    }

    /**
     *
     */
    public abstract void runOnce (ICommand command);

}
