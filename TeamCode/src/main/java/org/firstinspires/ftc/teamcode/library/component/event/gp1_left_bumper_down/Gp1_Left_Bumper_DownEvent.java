package org.firstinspires.ftc.teamcode.library.component.event.gp1_left_bumper_down;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class Gp1_Left_Bumper_DownEvent extends Event<Gp1_Left_Bumper_DownHandler> {

    /**
     *
     */
    public final static EventType<Gp1_Left_Bumper_DownHandler> TYPE = new EventType<Gp1_Left_Bumper_DownHandler>();

    /**
     *
     */
    public Gp1_Left_Bumper_DownEvent() {

    }


    @Override
    public EventType<Gp1_Left_Bumper_DownHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp1_Left_Bumper_DownHandler handler) {handler.onGp1_Left_Bumper_Down(this);}
}
