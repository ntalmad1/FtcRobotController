package org.firstinspires.ftc.teamcode.library.component.command;

/**
 *
 */
public abstract class OneTimeCommand extends AbstractCommand {

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
