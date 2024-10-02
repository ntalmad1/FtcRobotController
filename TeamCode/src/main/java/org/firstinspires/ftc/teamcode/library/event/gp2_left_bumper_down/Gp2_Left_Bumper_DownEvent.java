package org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_down;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_Left_Bumper_DownEvent extends Event<Gp2_Left_Bumper_DownHandler> {

    /**
     *
     */
    public final static EventType<Gp2_Left_Bumper_DownHandler> TYPE = new EventType<Gp2_Left_Bumper_DownHandler>();

    /**
     *
     */
    public Gp2_Left_Bumper_DownEvent() {

    }

    @Override
    public EventType<Gp2_Left_Bumper_DownHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp2_Left_Bumper_DownHandler handler) {handler.onGp2_Left_Bumper_Down(this);}
}
