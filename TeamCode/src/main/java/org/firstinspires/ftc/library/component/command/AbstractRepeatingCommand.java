package org.firstinspires.ftc.library.component.command;

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
