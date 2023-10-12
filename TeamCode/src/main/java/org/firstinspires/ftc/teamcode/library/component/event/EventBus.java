package org.firstinspires.ftc.teamcode.library.component.event;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_a_press.Gp2_A_PressEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_b_press.Gp2_B_PressEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_x.Gp2_LeftStickXEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_y.Gp2_LeftStickYEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_right_stick_x.Gp2_RightStickXEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_right_stick_y.Gp2_RightStickYEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_x_press.Gp2_X_PressEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_y_press.Gp2_Y_PressEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class EventBus extends HandlerManager {

    /**
     *
     */
    public static EventBus instance;

    /**
     *
     * @return
     */
    public static EventBus getInstance() {
        return instance;
    }

    /**
     *
     * @param robot
     */
    public static void init (IsaacBot robot) {
        instance = new EventBus();
    }

    /**
     *
     */
    private double gp2_leftStickX;
    private double gp2_leftStickY;
    private double gp2_rightStickX;
    private double gp2_rightStickY;

    /**
     *
     */
    private boolean gp2_a_down;
    private boolean gp2_b_down;
    private boolean gp2_x_down;
    private boolean gp2_y_down;

    /**
     * Hidden Constructor
     *
     */
    protected EventBus () {
        super();
    }

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

        //------------------------------------------------------------------------------------

        boolean current_gp2_a = this.robot.gamepad2.a;
        if (this.gp2_a_down && !current_gp2_a) {
            this.fireEvent(new Gp2_A_PressEvent());
        }
        this.gp2_a_down = current_gp2_a;

        boolean current_gp2_b = this.robot.gamepad2.b;
        if (this.gp2_b_down && !current_gp2_b) {
            this.fireEvent(new Gp2_B_PressEvent());
        }
        this.gp2_b_down = current_gp2_b;

        boolean current_gp2_x = this.robot.gamepad2.x;
        if (this.gp2_x_down && !current_gp2_x) {
            this.fireEvent(new Gp2_X_PressEvent());
        }
        this.gp2_x_down = current_gp2_x;

        boolean current_gp2_y = this.robot.gamepad2.y;
        if (this.gp2_y_down && !current_gp2_y) {
            this.fireEvent(new Gp2_Y_PressEvent());
        }
        this.gp2_y_down = current_gp2_y;
    }
}
