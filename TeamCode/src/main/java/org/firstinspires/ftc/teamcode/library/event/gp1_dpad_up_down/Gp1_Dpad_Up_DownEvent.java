package org.firstinspires.ftc.teamcode.library.event.gp1_dpad_up_down;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp1_Dpad_Up_DownEvent extends Event<Gp1_Dpad_Up_DownHandler> {

    /**
     *
     */
    public final static EventType<Gp1_Dpad_Up_DownHandler> TYPE = new EventType<Gp1_Dpad_Up_DownHandler>();

    /**
     *
     */
    public Gp1_Dpad_Up_DownEvent() {

    }


    @Override
    public EventType<Gp1_Dpad_Up_DownHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp1_Dpad_Up_DownHandler handler) {handler.onGp1_Dpad_Up_Down(this);}
}
