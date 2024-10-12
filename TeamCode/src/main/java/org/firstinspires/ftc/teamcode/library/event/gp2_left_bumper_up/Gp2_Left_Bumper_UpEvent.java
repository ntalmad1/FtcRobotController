package org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_up;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_Left_Bumper_UpEvent extends Event<Gp2_Left_Bumper_UpHandler> {

    /**
     *
     */
    public final static EventType<Gp2_Left_Bumper_UpHandler> TYPE = new EventType<Gp2_Left_Bumper_UpHandler>();

    /**
     *
     */
    public Gp2_Left_Bumper_UpEvent() {

    }

    @Override
    public EventType<Gp2_Left_Bumper_UpHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp2_Left_Bumper_UpHandler handler) {handler.onGp2_Left_Bumper_Down(this);}
}