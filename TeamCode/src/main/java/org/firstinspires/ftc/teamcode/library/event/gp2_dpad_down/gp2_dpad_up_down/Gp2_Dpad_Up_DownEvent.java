package org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_up_down;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_Dpad_Up_DownEvent extends Event<Gp2_Dpad_Up_DownHandler> {

    /**
     *
     */
    public final static EventType<Gp2_Dpad_Up_DownHandler> TYPE = new EventType<Gp2_Dpad_Up_DownHandler>();

    /**
     *
     */
    public Gp2_Dpad_Up_DownEvent() {

    }


    @Override
    public EventType<Gp2_Dpad_Up_DownHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp2_Dpad_Up_DownHandler handler) {handler.onGp2_Dpad_Up_Down(this);}
}
