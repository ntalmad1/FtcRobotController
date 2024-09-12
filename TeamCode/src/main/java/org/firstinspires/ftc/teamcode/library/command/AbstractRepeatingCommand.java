package org.firstinspires.ftc.teamcode.library.command;

/**
 *
 */
public abstract class AbstractRepeatingCommand extends AbstractCommand {

    /**
     * Constructor
     *
     */
    public AbstractRepeatingCommand() {
        super();
        this.setRepeating(true);
    }

}
