package org.firstinspires.ftc.teamcode.library.component;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.component.event.EventBus;
import org.firstinspires.ftc.teamcode.library.component.event.HandlerRegistration;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_x.Gp2_LeftStickXEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_x.Gp2_LeftStickXHandler;

/**
 *
 */
public abstract class Component {

    /**
     *
     */
    private EventBus eventBus;

    /**
     *
     */
    private IsaacBot robot;

    /**
     *
     */
    protected Telemetry telemetry;

    /**
     *
     * @param robot
     */
    public Component (IsaacBot robot)
    {
        this.robot = robot;

        this.telemetry = this.robot.telemetry;

        this.eventBus = new EventBus(robot);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_LeftStickXHandler (Gp2_LeftStickXHandler handler) {
        return this.eventBus.addHandler(Gp2_LeftStickXEvent.TYPE, handler);
    }

    /**
     *
     */
    public void init (){}

    /**
     *
     */
    public void run (){
        this.eventBus.run();
    }

    /**
     *
     * @return
     */
    protected EventBus getEventBus ()
    {
        return this.eventBus;
    }


}
