package org.firstinspires.ftc.library.component.event.gp2_dpad_left_down;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class Gp2_Dpad_Left_DownEvent extends Event<Gp2_Dpad_Left_DownHandler> {

    /**
     *
     */
    public final static EventType<Gp2_Dpad_Left_DownHandler> TYPE = new EventType<Gp2_Dpad_Left_DownHandler>();

    /**
     *
     */
    public Gp2_Dpad_Left_DownEvent() {

    }


    @Override
    public EventType<Gp2_Dpad_Left_DownHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp2_Dpad_Left_DownHandler handler) {handler.onGp2_Dpad_Left_Down(this);}
}

