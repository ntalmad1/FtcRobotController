package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.archive.competition.config.ArmCompConfig;
import org.firstinspires.ftc.teamcode.archive.competition.config.PixelCatcherCompConfig;
import org.firstinspires.ftc.teamcode.archive.competition.config.WinchCompConfig;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.boom.arm.Arm;
import org.firstinspires.ftc.teamcode.library.boom.arm.ArmConfig;
import org.firstinspires.ftc.teamcode.library.command.ICommand;
import org.firstinspires.ftc.teamcode.library.command.OneTimeCommand;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_x_press.Gp1_X_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_x_press.Gp1_X_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_y_press.Gp1_Y_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_y_press.Gp1_Y_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down_down.Gp2_Dpad_Down_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down_down.Gp2_Dpad_Down_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_up_down.Gp2_Dpad_Up_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_up_down.Gp2_Dpad_Up_DownHandler;
import org.firstinspires.ftc.teamcode.library.pixelcatcher.PixelCatcher;
import org.firstinspires.ftc.teamcode.library.winch.ConstantOutWinchCommand;
import org.firstinspires.ftc.teamcode.library.winch.Winch;

@TeleOp(name="Arm Test", group="Linear OpMode")
@Disabled
public class ArmTest extends IsaacBot {

    //private Boom topBoom;
    private Arm arm;

    private Winch winch;

    private RevTouchSensor trolleySensor;

    private PixelCatcher pixelCatcher;

    public ArmTest(){
        super();

        ArmConfig armConfig = new ArmCompConfig(this);
        armConfig.debug = false;
        this.arm = new Arm(armConfig);
        this.winch = new Winch(new WinchCompConfig(this));
        this.pixelCatcher = new PixelCatcher(new PixelCatcherCompConfig(this));
    }


    @Override
    public void runOpMode() throws InterruptedException {
        this.arm.init();
        this.winch.init();
        this.pixelCatcher.init();
        this.trolleySensor = this.hardwareMap.get(RevTouchSensor.class, "trolleySensor");

        this.addGp2_Dpad_Down_DownHandler(new Gp2_Dpad_Down_DownHandler() {
            @Override
            public void onGp2_Dpad_Down_Down(Gp2_Dpad_Down_DownEvent event) {
                double increment = ArmTest.this.arm.getBottomBoomIncrement();
                increment = increment - 0.005;
                ArmTest.this.arm.setBottomBoomIncrement(increment);
            }
        });

        this.addGp2_Dpad_Up_DownHandler(new Gp2_Dpad_Up_DownHandler() {
            @Override
            public void onGp2_Dpad_Up_Down(Gp2_Dpad_Up_DownEvent event) {
                double increment = ArmTest.this.arm.getBottomBoomIncrement();
                increment = increment + 0.005;
                ArmTest.this.arm.setBottomBoomIncrement(increment);
            }
        });

        this.addGp1_X_PressHandler(new Gp1_X_PressHandler() {
            @Override
            public void onGp1_X_Press(Gp1_X_PressEvent event) {

                final ICommand constantOutCommand = new ConstantOutWinchCommand(ArmTest.this.winch, ArmTest.this.trolleySensor, 1, 500);

                ArmTest.this.arm.addCommand(new OneTimeCommand() {
                    @Override
                    public void runOnce(ICommand command) {
                        ArmTest.this.telemetry.log().add("toggling");
                        if (ArmTest.this.pixelCatcher.getWinchPosition().equals(PixelCatcher.WinchPosition.DOWN)) {
                            ArmTest.this.pixelCatcher.toggleWinch();
                        }
                        command.markAsCompleted();
                    }
                });

                ArmTest.this.arm.addCommand(constantOutCommand);

                ArmTest.this.arm.wait(250, new CommandCallbackAdapter(){
                    @Override
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        constantOutCommand.markAsCompleted();
                    }
                });
            }
        });

        this.addGp1_Y_PressHandler(new Gp1_Y_PressHandler() {
            @Override
            public void onGp1_Y_Press(Gp1_Y_PressEvent event) {
                ArmTest.this.arm.run();
            }
        });

        this.waitForStart();

        while (this.opModeIsActive()) {

            this.getEventBus().run();
            //this.arm.run();
            this.winch.run();
            this.pixelCatcher.run();

        }
    }
}
