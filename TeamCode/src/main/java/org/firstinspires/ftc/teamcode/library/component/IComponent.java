package org.firstinspires.ftc.teamcode.library.component;

import org.firstinspires.ftc.teamcode.library.event.HandlerRegistration;
import org.firstinspires.ftc.teamcode.library.event.gp2_a_press.Gp2_A_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_b_press.Gp2_B_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_down_down.Gp2_Dpad_Down_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_left_down.Gp2_Dpad_Left_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_right_down.Gp2_Dpad_Right_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_up_down.Gp2_Dpad_Up_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_press.Gp2_Left_Bumper_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_stick_x.Gp2_LeftStickXHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_stick_y.Gp2_LeftStickYHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_bumper_press.Gp2_Right_Bumper_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_stick_x.Gp2_RightStickXHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_stick_y.Gp2_RightStickYHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_x_press.Gp2_X_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_y_press.Gp2_Y_PressHandler;

/**
 *
 */
public interface IComponent {

    HandlerRegistration addGp2_A_PressHandler (Gp2_A_PressHandler handler);

    HandlerRegistration addGp2_B_PressHandler (Gp2_B_PressHandler handler);

    HandlerRegistration addGp2_X_PressHandler (Gp2_X_PressHandler handler);

    HandlerRegistration addGp2_Y_PressHandler (Gp2_Y_PressHandler handler);

    HandlerRegistration addGp2_Left_Bumper_PressHandler (Gp2_Left_Bumper_PressHandler handler);

    HandlerRegistration addGp2_Right_Bumper_PressHandler (Gp2_Right_Bumper_PressHandler handler);

    HandlerRegistration addGp2_Dpad_Left_DownHandler (Gp2_Dpad_Left_DownHandler handler);

    HandlerRegistration addGp2_Dpad_Right_DownHandler (Gp2_Dpad_Right_DownHandler handler);

    HandlerRegistration addGp2_Dpad_Up_DownHandler (Gp2_Dpad_Up_DownHandler handler);

    HandlerRegistration addGp2_Dpad_Down_DownHandler (Gp2_Dpad_Down_DownHandler handler);

    HandlerRegistration addGp2_LeftStickXHandler (Gp2_LeftStickXHandler handler);

    HandlerRegistration addGp2_LeftStickYHandler (Gp2_LeftStickYHandler handler);

    HandlerRegistration addGp2_RightStickXHandler (Gp2_RightStickXHandler handler);

    HandlerRegistration addGp2_RightStickYHandler (Gp2_RightStickYHandler handler);
}
