package org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_right_down;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_Dpad_Right_DownEvent extends Event<Gp2_Dpad_Right_DownHandler> {

    /**
     *
     */
    public final static EventType<Gp2_Dpad_Right_DownHandler> TYPE = new EventType<Gp2_Dpad_Right_DownHandler>();

    /**
     *
     */
    public Gp2_Dpad_Right_DownEvent() {

    }


    @Override
    public EventType<Gp2_Dpad_Right_DownHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp2_Dpad_Right_DownHandler handler) {handler.onGp2_Dpad_Right_Down(this);}
}
