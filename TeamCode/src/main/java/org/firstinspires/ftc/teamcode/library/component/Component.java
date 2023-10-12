package org.firstinspires.ftc.teamcode.library.component;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.component.command.Command;
import org.firstinspires.ftc.teamcode.library.component.command.CommandQueue;
import org.firstinspires.ftc.teamcode.library.component.command.WaitCommand;
import org.firstinspires.ftc.teamcode.library.component.event.EventBus;
import org.firstinspires.ftc.teamcode.library.component.event.HandlerManager;
import org.firstinspires.ftc.teamcode.library.component.event.HandlerRegistration;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_a_press.Gp2_A_PressEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_a_press.Gp2_A_PressHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_x.Gp2_LeftStickXEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_x.Gp2_LeftStickXHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_y.Gp2_LeftStickYEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_y.Gp2_LeftStickYHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_right_stick_x.Gp2_RightStickXEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_right_stick_x.Gp2_RightStickXHandler;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_right_stick_y.Gp2_RightStickYEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_right_stick_y.Gp2_RightStickYHandler;

/**
 *
 */
public abstract class Component {

    /**
     */
    private CommandQueue commandQueue;

    /**
     */
    protected IsaacBot robot;

    /**
     */
    public Telemetry telemetry;

    /**
     *
     * @param robot
     */
    public Component (IsaacBot robot)
    {
        this.robot = robot;

        this.telemetry = this.robot.telemetry;

        this.commandQueue = new CommandQueue(this);
    }

    /**
     *
     * @param command
     */
    public void addCommand (Command command) {
        this.commandQueue.add(command);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_A_PressHandler (Gp2_A_PressHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_A_PressEvent.TYPE, handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_LeftStickXHandler (Gp2_LeftStickXHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_LeftStickXEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_LeftStickYHandler (Gp2_LeftStickYHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_LeftStickYEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_RightStickXHandler (Gp2_RightStickXHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_RightStickXEvent.TYPE, handler);
    }

    public HandlerRegistration addGp2_RightStickYHandler (Gp2_RightStickYHandler handler) {
        return EventBus.getInstance().addHandler(Gp2_RightStickYEvent.TYPE, handler);
    }

    /**
     *
     */
    public void cancelAllCommands () {
        this.commandQueue.clear();
    }

    /**
     *
     */
    public void init (){}

    /**
     *
     */
    public void run (){
        this.commandQueue.run();
    }

    public void runCommand (Command command) {
        if (command.isCompleted()) {
            return;
        }
        command.run();
    }
}
