package org.firstinspires.ftc.library.winch;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.component.Component;
import org.firstinspires.ftc.library.component.event.gp2_left_trigger.Gp2_Left_Trigger_Event;
import org.firstinspires.ftc.library.component.event.gp2_left_trigger.Gp2_Left_Trigger_Handler;
import org.firstinspires.ftc.library.component.event.gp2_left_trigger_down.Gp2_Left_Trigger_DownEvent;
import org.firstinspires.ftc.library.component.event.gp2_left_trigger_down.Gp2_Left_Trigger_DownHandler;
import org.firstinspires.ftc.library.component.event.gp2_right_trigger.Gp2_Right_Trigger_Event;
import org.firstinspires.ftc.library.component.event.gp2_right_trigger.Gp2_Right_Trigger_Handler;
import org.firstinspires.ftc.library.component.event.gp2_right_trigger_down.Gp2_Right_Trigger_DownEvent;
import org.firstinspires.ftc.library.component.event.gp2_right_trigger_down.Gp2_Right_Trigger_DownHandler;
import org.firstinspires.ftc.library.motor.EncodedMotor;

/**
 *
 */
public class Winch extends Component {

    /**
     */
    private WinchConfig config;

    /**
     */
    private EncodedMotor motor;

    /**
     * Constructor
     *
     * @param config
     */
    public Winch(WinchConfig config) {
        super(config.robot);

        this.config = config;
        config.debug = true;

        this.motor = new EncodedMotor(this.config.encodedMotorConfig);
    }

    /**
     *
     */
    public WinchConfig getConfig () {
        return this.config;
    }

    /**
     *
     */
    public void init () {
        super.init();

        this.motor.init();

        this.addGp2_Left_Trigger_Handler(new Gp2_Left_Trigger_Handler() {
            @Override
            public void onGp2_Left_Trigger(Gp2_Left_Trigger_Event event) {
//                Winch.this.telemetry.addData("Left Trigger Position: ", "%2f", event.getPosition());
//                Winch.this.telemetry.update();

                if (event.getPosition() == 0) {
                    Winch.this.motor.setBrake(false);
                }
                else {
                    Winch.this.motor.move(event.getPosition() * -1);
                }
            }
        });

        this.addGp2_Right_Trigger_Handler(new Gp2_Right_Trigger_Handler() {
            @Override
            public void onGp2_Right_Trigger(Gp2_Right_Trigger_Event event) {

//                  Winch.this.telemetry.addData("Right Trigger Position: ", "%2f", event.getPosition());
//                  Winch.this.telemetry.update();

                if (event.getPosition() == 0) {
                    Winch.this.motor.setBrake(false);
                }
                else {
                    Winch.this.motor.move(event.getPosition());
                }
            }
        });


    }

    /**
     *
     */
    public void run () {
        super.run();

        this.motor.run();

        if (this.config.debug) {
            this.telemetry.addData("Winch motor current position: ", "%7d", this.motor.getCurrentPosition());
            this.telemetry.update();
        }
    }
}
