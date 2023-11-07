package org.firstinspires.ftc.teamcode.library.claw;

import org.firstinspires.ftc.teamcode.library.component.command.AbstractCommand;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandSuccessEvent;

/**
 *
 */
public class ClawCloseCommand extends AbstractCommand {

    /**
     */
    private Claw claw;

    /**
     */
    private CloseClawCommand command;

    /**
     * Constructor
     *
     * @param claw
     * @param side
     */
    public ClawCloseCommand(Claw claw, Claw.Side side) {
        this.claw = claw;

        this.command = new CloseClawCommand(claw, side);
        command.addCallbackHandler(new CommandCallbackAdapter() {
            public void onSuccess(CommandSuccessEvent successEvent) {
                ClawCloseCommand.this.markAsCompleted();
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
