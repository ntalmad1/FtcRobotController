package org.firstinspires.ftc.library.pixelcatcher;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.Control;
import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.component.Component;
import org.firstinspires.ftc.library.component.event.gp1_left_trigger_down.Gp1_Left_Trigger_DownEvent;
import org.firstinspires.ftc.library.component.event.gp1_left_trigger_down.Gp1_Left_Trigger_DownHandler;
import org.firstinspires.ftc.library.component.event.gp1_right_trigger_down.Gp1_Right_Trigger_DownEvent;
import org.firstinspires.ftc.library.component.event.gp1_right_trigger_down.Gp1_Right_Trigger_DownHandler;

/**
 *
 */
public class PixelCatcher extends Component {

    /**
     *
     */
    public enum ArmPosition {
        OPENED,
        CLOSED
    }

    /**
     */
    protected PixelCatcherConfig config;

    /**
     */
    private Servo leftServo;

    /**
     */
    private Servo rightServo;

    /**
     *
     */
    private ArmPosition leftArmPos;

    /**
     *
     */
    private ArmPosition rightArmPos;

    /**
     * Constructor
     *
     * @param config
     */
    public PixelCatcher (PixelCatcherConfig config) {
        super(config.robot);

        this.config = config;
    }

    /**
     *
     */
    public void init () {
        super.init();

        leftServo = this.robot.hardwareMap.get(Servo.class, config.leftArmServoName);
        rightServo = this.robot.hardwareMap.get(Servo.class, config.rightArmServoName);

        leftServo.resetDeviceConfigurationForOpMode();
        rightServo.resetDeviceConfigurationForOpMode();

        this.leftServo.setPosition(this.config.leftArmServoInitPos);
        this.rightServo.setPosition(this.config.rightArmServoInitPos);

        this.leftArmPos = this.config.leftArmIntiPos;
        this.rightArmPos = this.config.rightArmIntiPos;

        if (this.config.leftArmToggle == Control.Gp1_LeftTrigger_Down) {
            this.addGp1_Left_Trigger_DownHandler(event -> PixelCatcher.this.toggleLeftArm());
        }

        if (this.config.rightArmToggle == Control.Gp1_RightTrigger_Down) {
            this.addGp1_Right_Trigger_DownHandler(event -> PixelCatcher.this.toggleRightArm());
        }
    }

    /**
     *
     */
    public void run () {
        super.run();
    }

    /**
     *
     */
    public void toggleLeftArm () {
        switch (this.leftArmPos) {
            case CLOSED:
                this.leftServo.setPosition(this.config.leftArmServoOpenedPos);
                this.leftArmPos = ArmPosition.OPENED;
                break;
            case OPENED:
                this.leftServo.setPosition(this.config.leftArmServoClosedPos);
                this.leftArmPos = ArmPosition.CLOSED;
                break;
        }
    }

    /**
     *
     */
    public void toggleRightArm () {
        switch (this.rightArmPos) {
            case CLOSED:
                this.rightServo.setPosition(this.config.rightArmServoOpenedPos);
                this.rightArmPos = ArmPosition.OPENED;
                break;
            case OPENED:
                this.rightServo.setPosition(this.config.rightArmServoClosedPos);
                this.rightArmPos = ArmPosition.CLOSED;
                break;
        }

    }
}
