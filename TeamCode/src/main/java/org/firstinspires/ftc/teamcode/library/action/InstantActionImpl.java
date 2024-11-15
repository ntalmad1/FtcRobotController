package org.firstinspires.ftc.teamcode.library.action;

/**
 * Instant action that executes [f] immediately.
 */
public class InstantActionImpl extends AbstractAction {

    /**
     */
    private InstantFunction action;

    /**
     *
     */
    public InstantActionImpl(InstantFunction action) {
        this.action = action;
    }


    @Override
    public boolean run() {
        action.run();

        return STOP;
    }
}
