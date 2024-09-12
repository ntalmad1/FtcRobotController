package org.firstinspires.ftc.teamcode.library.claw;

import org.firstinspires.ftc.teamcode.library.command.AbstractCommand;

/**
 *
 */
public class OpenClawCommand extends AbstractCommand {

    /**
     */
    private Claw claw;

    /**
     *
     */
    private Claw.Side side;

    /**
     *
     * @param claw
     */
    public OpenClawCommand(Claw claw, Claw.Side side) {
        this.claw = claw;
        this.side = side;
    }

    /**
     *
     */
    public void run () {
        if (this.isCompleted()) {
            return;
        }

        if ((side.equals(Claw.Side.LEFT) && !this.claw.isLeftClosed())
          || side.equals(Claw.Side.RIGHT) && !this.claw.isRightClosed()) {
            this.markAsCompleted();
            return;
        }

        if (side.equals(Claw.Side.LEFT)) {
            this.claw.openLeft();
            this.markAsCompleted();
        }
        else if (side.equals(Claw.Side.RIGHT)) {
            this.claw.openRight();
            this.markAsCompleted();
        }
    }
}
