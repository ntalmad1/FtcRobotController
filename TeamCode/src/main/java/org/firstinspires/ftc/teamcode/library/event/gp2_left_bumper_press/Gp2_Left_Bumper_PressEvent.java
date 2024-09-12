package org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_press;


import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_Left_Bumper_PressEvent extends Event<Gp2_Left_Bumper_PressHandler> {

    /**
     *
     */
    public final static EventType<Gp2_Left_Bumper_PressHandler> TYPE = new EventType<Gp2_Left_Bumper_PressHandler>();

    /**
     *
     */
    public Gp2_Left_Bumper_PressEvent() {

    }


    @Override
    public EventType<Gp2_Left_Bumper_PressHandler> getType() {return TYPE;}

    @Override
    public void handle(Gp2_Left_Bumper_PressHandler handler) {handler.onGp2_Left_Bumper_Press(this);}
}
