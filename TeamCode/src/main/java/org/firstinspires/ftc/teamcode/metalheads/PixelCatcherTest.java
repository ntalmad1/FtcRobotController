package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.boom.arm.Arm;
import org.firstinspires.ftc.library.boom.arm.ArmConfig;
import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.OneTimeCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.component.event.g2_x_press.Gp1_X_PressEvent;
import org.firstinspires.ftc.library.component.event.g2_x_press.Gp1_X_PressHandler;
import org.firstinspires.ftc.library.component.event.g2_y_press.Gp1_Y_PressEvent;
import org.firstinspires.ftc.library.component.event.g2_y_press.Gp1_Y_PressHandler;
import org.firstinspires.ftc.library.component.event.gp2_dpad_down_down.Gp2_Dpad_Down_DownEvent;
import org.firstinspires.ftc.library.component.event.gp2_dpad_down_down.Gp2_Dpad_Down_DownHandler;
import org.firstinspires.ftc.library.component.event.gp2_dpad_up_down.Gp2_Dpad_Up_DownEvent;
import org.firstinspires.ftc.library.component.event.gp2_dpad_up_down.Gp2_Dpad_Up_DownHandler;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcher;
import org.firstinspires.ftc.library.winch.ConstantOutWinchCommand;
import org.firstinspires.ftc.library.winch.Winch;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.ArmCompConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.PixelCatcherCompConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.WinchCompConfig;

@TeleOp(name="PixelCatcherTest", group="Linear OpMode")
//@Disabled
public class PixelCatcherTest extends IsaacBot {

    private PixelCatcher pixelCatcher;

    public PixelCatcherTest(){
        super();
        this.pixelCatcher = new PixelCatcher(new PixelCatcherCompConfig(this));
    }

    @Override
    public void runOpMode() throws InterruptedException {
        this.pixelCatcher.init();

        this.waitForStart();

        while (this.opModeIsActive()) {

            this.getEventBus().run();
            this.pixelCatcher.run();

        }
    }
}
