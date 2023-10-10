package org.firstinspires.ftc.teamcode.library.component.event;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_x.Gp2_LeftStickXEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_y.Gp2_LeftStickYEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_right_stick_x.Gp2_RightStickXEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_right_stick_y.Gp2_RightStickYEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class EventBus {

    /**
     *
     */
    private Map<EventType, List<EventHandler>> handlerMap;

    /**
     *
     */
    private IsaacBot robot;

    /**
     *
     * @param robot
     */
    public EventBus (IsaacBot robot) {
        this.robot = robot;

        this.handlerMap = new HashMap<EventType, List<EventHandler>>();
    }

    /**
     *
     * @param eventType
     * @param handler
     * @return
     * @param <T>
     */
    public <T extends EventHandler> HandlerRegistration addHandler (EventType<T> eventType, T handler)
    {
        if (!this.handlerMap.containsKey(eventType))
        {
            this.handlerMap.put((EventType)eventType, (List<EventHandler>) new ArrayList<T>());
        }

        this.handlerMap.get(eventType).add(handler);

        return new HandlerRegistration(handler);
    }

    /**
     *
     * @param event
     * @param <T>
     */
    public <T extends EventHandler> void fireEvent (Event<T> event) {

        if (!this.handlerMap.containsKey(event.getType())) {
            return;
        }

        List<T> handlers = (List<T>)this.handlerMap.get(event.getType());

        for (T handler : handlers) {
            event.handle(handler);
        }
    }

    private double gp2_leftStickX;
    private double gp2_leftStickY;
    private double gp2_rightStickX;
    private double gp2_rightStickY;

    /**
     *
     */
    public void run ()
    {
        double current_gp2_leftStickX = this.robot.gamepad2.left_stick_x;
        if (current_gp2_leftStickX > 0
         || current_gp2_leftStickX < 0
         || (current_gp2_leftStickX == 0 && gp2_leftStickX != 0)) {

            Gp2_LeftStickXEvent event = new Gp2_LeftStickXEvent(current_gp2_leftStickX);
            this.fireEvent(event);
            gp2_leftStickX = current_gp2_leftStickX;
        }

        double current_gp2_leftStickY = this.robot.gamepad2.left_stick_y;
        if (current_gp2_leftStickY > 0
                || current_gp2_leftStickY < 0
                || (current_gp2_leftStickY == 0 && gp2_leftStickY != 0)) {

            Gp2_LeftStickYEvent event = new Gp2_LeftStickYEvent(current_gp2_leftStickY);
            this.fireEvent(event);
            gp2_leftStickY = current_gp2_leftStickY;
        }

        double current_gp2_rightStickX = this.robot.gamepad2.right_stick_x;
        if (current_gp2_rightStickX > 0
                || current_gp2_rightStickX < 0
                || (current_gp2_rightStickX == 0 && gp2_rightStickX != 0)) {

            Gp2_RightStickXEvent event = new Gp2_RightStickXEvent(current_gp2_rightStickX);
            this.fireEvent(event);
            gp2_rightStickX = current_gp2_rightStickX;
        }

        double current_gp2_rightStickY = this.robot.gamepad2.right_stick_y;
        if (current_gp2_rightStickY > 0
                || current_gp2_rightStickY < 0
                || (current_gp2_rightStickY == 0 && gp2_rightStickY != 0)) {

            Gp2_RightStickYEvent event = new Gp2_RightStickYEvent(current_gp2_rightStickY);
            this.fireEvent(event);
            gp2_rightStickY = current_gp2_rightStickY;
        }


    }
}
