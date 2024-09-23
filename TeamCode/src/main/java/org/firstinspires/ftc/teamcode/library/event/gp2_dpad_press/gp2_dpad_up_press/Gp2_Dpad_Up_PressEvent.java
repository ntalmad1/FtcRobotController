package org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_up_press;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_Dpad_Up_PressEvent extends Event<Gp2_Dpad_Up_PressHandler> {

    /**
     *
     */
    public final static EventType<Gp2_Dpad_Up_PressHandler> TYPE = new EventType<Gp2_Dpad_Up_PressHandler>();

    /**
     *
     */
    public Gp2_Dpad_Up_PressEvent() {

    }


    @Override
    public EventType<Gp2_Dpad_Up_PressHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp2_Dpad_Up_PressHandler handler) {handler.onGp2_Dpad_Up_Press(this);}
}
