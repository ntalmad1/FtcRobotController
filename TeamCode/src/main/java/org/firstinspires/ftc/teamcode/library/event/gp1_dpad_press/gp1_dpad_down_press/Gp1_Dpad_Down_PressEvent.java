package org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp1_Dpad_Down_PressEvent extends Event<Gp1_Dpad_Down_PressHandler> {

    /**
     *
     */
    public final static EventType<Gp1_Dpad_Down_PressHandler> TYPE = new EventType<Gp1_Dpad_Down_PressHandler>();

    /**
     *
     */
    public Gp1_Dpad_Down_PressEvent() {

    }


    @Override
    public EventType<Gp1_Dpad_Down_PressHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp1_Dpad_Down_PressHandler handler) {handler.onGp1_Dpad_Down_Press(this);}
}
