package org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down_down;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_Dpad_Down_DownEvent extends Event<Gp2_Dpad_Down_DownHandler> {

    /**
     *
     */
    public final static EventType<Gp2_Dpad_Down_DownHandler> TYPE = new EventType<Gp2_Dpad_Down_DownHandler>();

    /**
     *
     */
    public Gp2_Dpad_Down_DownEvent() {

    }


    @Override
    public EventType<Gp2_Dpad_Down_DownHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp2_Dpad_Down_DownHandler handler) {handler.onGp2_Dpad_Down_Down(this);}
}
