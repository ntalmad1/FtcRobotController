package org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_left_down;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp1_Dpad_Left_DownEvent extends Event<Gp1_Dpad_Left_DownHandler> {

    /**
     *
     */
    public final static EventType<Gp1_Dpad_Left_DownHandler> TYPE = new EventType<Gp1_Dpad_Left_DownHandler>();

    /**
     *
     */
    public Gp1_Dpad_Left_DownEvent() {

    }


    @Override
    public EventType<Gp1_Dpad_Left_DownHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp1_Dpad_Left_DownHandler handler) {handler.onGp1_Dpad_Left_Down(this);}
}

