package org.firstinspires.ftc.teamcode.library.component.event.gp1_dpad_down_down;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class Gp1_Dpad_Down_DownEvent extends Event<Gp1_Dpad_Down_DownHandler> {

    /**
     *
     */
    public final static EventType<Gp1_Dpad_Down_DownHandler> TYPE = new EventType<Gp1_Dpad_Down_DownHandler>();

    /**
     *
     */
    public Gp1_Dpad_Down_DownEvent() {

    }


    @Override
    public EventType<Gp1_Dpad_Down_DownHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp1_Dpad_Down_DownHandler handler) {handler.onGp1_Dpad_Down_Down(this);}
}
