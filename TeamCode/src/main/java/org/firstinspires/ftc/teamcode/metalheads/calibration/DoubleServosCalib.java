package org.firstinspires.ftc.teamcode.metalheads.calibration;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_up_press.Gp1_Dpad_Up_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_up_press.Gp1_Dpad_Up_PressHandler;
import org.firstinspires.ftc.teamcode.library.rotator.Rotator;
import org.firstinspires.ftc.teamcode.library.rotator.RotatorConfig;
import org.firstinspires.ftc.teamcode.library.utility.Control;

/**
 * Tool to 'zero' the servos for the linear actuator arms. Also to
 * get the servo positions for set positions and limits.
 *
 * left start 0
 * right start 0.979
 *
 * left hook position 0.140
 *
 * left max 0.336
 * right max
 *
 */
@TeleOp(name="DoubleServosCalib", group="Calibration")
//@Disabled
public class DoubleServosCalib extends IsaacBot {

    private Rotator doubleServos;

    private RotatorConfig config;

    private double gamePadIncrement = 0.006;


    /**
     * Constructor
     */
    public DoubleServosCalib(){
        super();

        config = new RotatorConfig(this);

        config.servoName = "leftBoom";

        config.maxIncrement = 0.006;

        config.minPosition = 0;
        config.maxPosition = 0.9;

        config.homePosition = 0.5;
        config.zeroDegreePosition = 0.5;

        config.controllerInputMethod = Control.Gp1_LeftStickX;

        config.isDualServo = true;

        config.secondaryServoName = "rightBoom";
    }

    /**
     *
     */
    @Override
    public void initBot() {
        super.initBot();

        this.doubleServos = new Rotator(config);
        this.doubleServos.init();

        this.addGp1_Dpad_Down_PressHandler(new Gp1_Dpad_Down_PressHandler() {
            public void onGp1_Dpad_Down_Press(Gp1_Dpad_Down_PressEvent event) {
                double newPos = doubleServos.getPosition() - gamePadIncrement;

                if (newPos < config.minPosition) newPos = config.minPosition;
                if (newPos > config.maxPosition) newPos = config.maxPosition;

                doubleServos.setServoPosition(newPos);
            }
        });

        this.addGp1_Dpad_Up_PressHandler(new Gp1_Dpad_Up_PressHandler() {
            public void onGp1_Dpad_Up_Press(Gp1_Dpad_Up_PressEvent event) {
                double newPos = doubleServos.getPosition() + gamePadIncrement;

                if (newPos < config.minPosition) newPos = config.minPosition;
                if (newPos > config.maxPosition) newPos = config.maxPosition;

                doubleServos.setServoPosition(newPos);
            }
        });

        this.addGp1_A_PressHandler(event -> {

            if (doubleServos.getPosition() >= 0.5) {
                doubleServos.setServoPosition(config.minPosition);
            }
            else {
                doubleServos.setServoPosition(config.maxPosition);
            }

        });
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        doubleServos.run();

        telemetry.addData("Double Servos Pos:", "%.3f", doubleServos.getPosition());
        telemetry.update();
    }
}
