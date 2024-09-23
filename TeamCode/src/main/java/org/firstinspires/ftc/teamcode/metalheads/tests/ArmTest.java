package org.firstinspires.ftc.teamcode.metalheads.tests;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.event.gp1_a_press.Gp1_A_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_a_press.Gp1_A_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_bumper_down.Gp1_Left_Bumper_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_bumper_down.Gp1_Left_Bumper_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger_down.Gp1_Left_Trigger_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_trigger_down.Gp1_Left_Trigger_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_bumper_down.Gp1_Right_Bumper_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_bumper_down.Gp1_Right_Bumper_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger_down.Gp1_Right_Trigger_DownEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger_down.Gp1_Right_Trigger_DownHandler;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="ArmTest", group="Tests")
// @Disabled
public class ArmTest extends IsaacBot {

    //config
    private int viperMax = 2950;
    private int viperMin = 10;

    private DcMotor viper;

    private CRServo intake;

    private int viperOffset;
    private int viperPosition;

    /**
     *
     */
    public void initBot() {
        super.initBot();

        viper = hardwareMap.get(DcMotor.class, "viperSlide");
        viperOffset = viper.getCurrentPosition();


        intake = hardwareMap.get(CRServo.class, "intakeMain");

        /**
         *
         */
        this.addGp1_A_PressHandler(new Gp1_A_PressHandler() {
            public void onGp1_A_Press(Gp1_A_PressEvent event) {

                telemetry.addLine("Gp1 A Pressed");
                telemetry.update();

            }
        });

        this.robotComponent.addGp1_Left_Bumper_DownHandler(new Gp1_Left_Bumper_DownHandler() {
            @Override
            public void onGp1_Left_Bumper_Down(Gp1_Left_Bumper_DownEvent event) {

                telemetry.addLine("Gp1 Left_Bumper Pressed");
                telemetry.update();

            }
        });

        this.robotComponent.addGp1_Right_Bumper_DownHandler(new Gp1_Right_Bumper_DownHandler() {
            @Override
            public void onGp1_Right_Bumper_Down(Gp1_Right_Bumper_DownEvent event) {

                telemetry.addLine("Gp1 Right_Bumper Pressed");
                telemetry.update();

            }
        });

        this.robotComponent.addGp1_Left_Trigger_DownHandler(new Gp1_Left_Trigger_DownHandler() {
            @Override
            public void onGp1_Left_Trigger_Down(Gp1_Left_Trigger_DownEvent event) {

                telemetry.addLine("Gp1 Left_Trigger Pressed");
                telemetry.update();

                intake.setPower(1);

            }
        });

        this.robotComponent.addGp1_Right_Trigger_DownHandler(new Gp1_Right_Trigger_DownHandler() {
            @Override
            public void onGp1_Right_Trigger_Down(Gp1_Right_Trigger_DownEvent event) {

                telemetry.addLine("Gp1 Right_Trigger Pressed");
                telemetry.update();

                intake.setPower(-1);

            }
        });

    }


    public void run() {
        super.run();

        double leftY = -1 * (gamepad1.left_stick_y);
        double rightX = gamepad1.right_stick_x;
        viperPosition = (viper.getCurrentPosition() - viperOffset);

        /**
         *
         */
        if ((gamepad1.left_trigger <= 0.1) && (gamepad1.right_trigger <= 0.1)) {
            intake.setPower(0.0);
        }

        /**
         *
         */
        if ((leftY > 0) && (viperPosition <= viperMax)) {
            viper.setPower(leftY);
        } else if ((leftY < 0) && (viperPosition >= viperMin)) {
            viper.setPower(leftY);
        } else viper.setPower(0.0);


    /**
     *
     */
        telemetry.addData("Current Position","%7d",viperPosition);
        telemetry.update();

    }
}