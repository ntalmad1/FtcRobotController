package org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_left_press;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_Dpad_Left_PressEvent extends Event<Gp2_Dpad_Left_PressHandler> {

    /**
     *
     */
    public final static EventType<Gp2_Dpad_Left_PressHandler> TYPE = new EventType<Gp2_Dpad_Left_PressHandler>();

    /**
     *
     */
    public Gp2_Dpad_Left_PressEvent() {

    }


    @Override
    public EventType<Gp2_Dpad_Left_PressHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp2_Dpad_Left_PressHandler handler) {handler.onGp2_Dpad_Left_Press(this);}
}
