package org.firstinspires.ftc.library.component.event.gp2_dpad_right_down;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class Gp2_Dpad_Right_Down_DownEvent extends Event<Gp2_Dpad_Right_DownHandler> {

    /**
     *
     */
    public final static EventType<Gp2_Dpad_Right_DownHandler> TYPE = new EventType<Gp2_Dpad_Right_DownHandler>();

    /**
     *
     */
    public Gp2_Dpad_Right_Down_DownEvent() {

    }


    @Override
    public EventType<Gp2_Dpad_Right_DownHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp2_Dpad_Right_DownHandler handler) {handler.onGp2_Dpad_Right_Down(this);}
}
