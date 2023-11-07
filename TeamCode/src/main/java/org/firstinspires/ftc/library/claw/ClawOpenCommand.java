package org.firstinspires.ftc.library.claw;

import org.firstinspires.ftc.library.component.command.AbstractCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;

/**
 *
 */
public class ClawOpenCommand extends AbstractCommand {

    /**
     */
    private Claw claw;

    /**
     */
    private OpenClawCommand command;

    /**
     * Constructor
     *
     * @param claw
     * @param side
     */
    public ClawOpenCommand(Claw claw, Claw.Side side) {
        this.claw = claw;

        this.command = new OpenClawCommand(claw, side);
        command.addCallbackHandler(new CommandCallbackAdapter() {
            public void onSuccess(CommandSuccessEvent successEvent) {
                ClawOpenCommand.this.markAsCompleted();
            }
        });
    }

    public void init () {
        this.claw.addCommand(command);
    }

    public void run () {
        if (this.isCompleted()) {
            return;
        }
    }

}
