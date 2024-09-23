package org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_down_press;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_Dpad_Down_PressEvent extends Event<Gp2_Dpad_Down_PressHandler> {

    /**
     *
     */
    public final static EventType<Gp2_Dpad_Down_PressHandler> TYPE = new EventType<Gp2_Dpad_Down_PressHandler>();

    /**
     *
     */
    public Gp2_Dpad_Down_PressEvent() {

    }


    @Override
    public EventType<Gp2_Dpad_Down_PressHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp2_Dpad_Down_PressHandler handler) {handler.onGp2_Dpad_Down_Press(this);}
}
