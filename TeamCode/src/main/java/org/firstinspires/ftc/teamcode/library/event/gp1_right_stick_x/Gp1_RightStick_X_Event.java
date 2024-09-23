package org.firstinspires.ftc.teamcode.library.event.gp1_right_stick_x;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_stick_x.Gp2_RightStick_X_Handler;

/**
 *
 */
public class Gp1_RightStick_X_Event extends Event<Gp1_RightStick_X_Handler> {

    public final static EventType<Gp1_RightStick_X_Handler> TYPE = new EventType<Gp1_RightStick_X_Handler>();

    /**
     *
     */
    private double position;

    /**
     *
     * @param pos
     */
    public Gp1_RightStick_X_Event(double pos) {
        this.position = pos;
    }

    /**
     *
     * @return
     */
    public double getPosition () {
        return this.position;
    }

    @Override
    public EventType<Gp1_RightStick_X_Handler> getType() {
        return TYPE;
    }

    @Override
    public void handle(Gp1_RightStick_X_Handler handler) {
        handler.onGp1_RightStick_X(this);
    }
}
