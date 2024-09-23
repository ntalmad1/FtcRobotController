package org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_right_press;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp1_Dpad_Right_PressEvent extends Event<Gp1_Dpad_Right_PressHandler> {

    /**
     *
     */
    public final static EventType<Gp1_Dpad_Right_PressHandler> TYPE = new EventType<Gp1_Dpad_Right_PressHandler>();

    /**
     *
     */
    public Gp1_Dpad_Right_PressEvent() {

    }


    @Override
    public EventType<Gp1_Dpad_Right_PressHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp1_Dpad_Right_PressHandler handler) {handler.onGp1_Dpad_Right_Press(this);}
}
