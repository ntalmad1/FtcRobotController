package org.firstinspires.ftc.teamcode.library.action;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class ParallelActionImpl extends AbstractAction {

    /**
     */
    private List<AbstractAction> actions;

    /**
     * Constructor
     *
     */
    public ParallelActionImpl(AbstractAction... actions) {
        super();
        this.setBlocking(false);
        this.actions = Arrays.asList(actions);
    }

    @Override
    public boolean run() {

        int completedCount = 0;

        for (AbstractAction action : this.actions) {

            if (action.isCompleted()) {
                completedCount++;
                continue;
            }

            boolean result = action.run(this.getTelemetryPacket());

            if (result == AbstractAction.STOP) { // the action completed
                completedCount++;
            }
        }

        if (completedCount == this.actions.size()) {
            return STOP;
        }

        return CONTIUE;
    }
}
