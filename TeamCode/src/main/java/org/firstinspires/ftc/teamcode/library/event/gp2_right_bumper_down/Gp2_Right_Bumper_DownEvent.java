package org.firstinspires.ftc.teamcode.library.event.gp2_right_bumper_down;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_Right_Bumper_DownEvent extends Event<Gp2_Right_Bumper_DownHandler> {

    /**
     *
     */
    public final static EventType<Gp2_Right_Bumper_DownHandler> TYPE = new EventType<Gp2_Right_Bumper_DownHandler>();

    /**
     *
     */
    public Gp2_Right_Bumper_DownEvent() {

    }

    @Override
    public EventType<Gp2_Right_Bumper_DownHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp2_Right_Bumper_DownHandler handler) {handler.onGp2_Right_Bumper_Down(this);}
}
