package org.firstinspires.ftc.teamcode.library.action;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ActionQueue {

    /**
     */
    private List<AbstractAction> actions;

    /**
     * Constructor
     *
     */
    public ActionQueue() {
        this.actions = new ArrayList<>();
    }

    /**
     *
     */
    public void run() {

        List<AbstractAction> completedActions = new ArrayList<>();

        for (AbstractAction action : this.actions) {

            if (action.isCompleted()) {
                completedActions.add(action);
                continue;
            }

            boolean result = action.run(new TelemetryPacket());

            if (result == AbstractAction.STOP) { // the action completed
                completedActions.add(action);
            } else {
                if (action.isBlocking()) {
                    break;
                }
            }
        }

        for (AbstractAction completedAction : completedActions) {
            this.actions.remove(completedAction);
        }
    }

    /**
     *
     * @param action
     */
    public void add(AbstractAction action) {
        this.actions.add(action);
    }

    /**
     *
     */
    public void killAllActions() {
       for (AbstractAction action : this.actions) {
           action.markAsCompleted();
       }
    }
}
