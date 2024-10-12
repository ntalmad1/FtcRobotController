package org.firstinspires.ftc.teamcode.library.event.gp1_right_bumper_up;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp1_Right_Bumper_UpEvent extends Event<Gp1_Right_Bumper_UpHandler> {

    /**
     *
     */
    public final static EventType<Gp1_Right_Bumper_UpHandler> TYPE = new EventType<Gp1_Right_Bumper_UpHandler>();

    /**
     *
     */
    public Gp1_Right_Bumper_UpEvent() {

    }

    @Override
    public EventType<Gp1_Right_Bumper_UpHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp1_Right_Bumper_UpHandler handler) {handler.onGp1_Right_Bumper_Up(this);}
}