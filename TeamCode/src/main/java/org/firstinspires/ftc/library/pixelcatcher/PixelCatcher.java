package org.firstinspires.ftc.library.pixelcatcher;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.utility.Control;
import org.firstinspires.ftc.library.claw.events.leftpixelping.LeftPixelPingEvent;
import org.firstinspires.ftc.library.claw.events.rightpixelping.RightPixelPingEvent;
import org.firstinspires.ftc.library.component.Component;
import org.firstinspires.ftc.library.pixelcatcher.events.leftarmclose.PixelCatcherLeftArmCloseEvent;
import org.firstinspires.ftc.library.pixelcatcher.events.leftarmopen.PixelCatcherLeftArmOpenEvent;
import org.firstinspires.ftc.library.pixelcatcher.events.rightarmclose.PixelCatcherRightArmCloseEvent;
import org.firstinspires.ftc.library.pixelcatcher.events.rightarmopen.PixelCatcherRightArmOpenEvent;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

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
     */
    private DistanceSensor leftPixelSensor;

    /**
     *
     */
    private DistanceSensor rightPixelSensor;

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

        this.leftArmPos = this.config.leftArmInitPos;
        this.rightArmPos = this.config.rightArmInitPos;

        if (this.config.leftArmToggle == Control.Gp1_LeftTrigger_Down) {
            this.addGp1_Left_Trigger_DownHandler(event -> PixelCatcher.this.toggleLeftArm());
        }

        if (this.config.rightArmToggle == Control.Gp1_RightTrigger_Down) {
            this.addGp1_Right_Trigger_DownHandler(event -> PixelCatcher.this.toggleRightArm());
        }

        this.leftPixelSensor = this.robot.hardwareMap.get(DistanceSensor.class, this.config.leftPixelSensorName);
        this.rightPixelSensor = this.robot.hardwareMap.get(DistanceSensor.class, this.config.rightPixelSensorName);
    }

    /**
     *
     */
    public void run () {
        super.run();

        LeftPixelPingEvent leftPixelPingEvent = new LeftPixelPingEvent(leftPixelSensor.getDistance(DistanceUnit.MM), this.getLeftArmPosition());
        RightPixelPingEvent rightPixelPingEvent = new RightPixelPingEvent(rightPixelSensor.getDistance(DistanceUnit.MM), this.getRightArmPosition());

        this.fireEvent(leftPixelPingEvent);
        this.fireEvent(rightPixelPingEvent);
    }

    /**
     *
     */
    public void toggleLeftArm () {
        switch (this.leftArmPos) {
            case CLOSED:
                this.leftServo.setPosition(this.config.leftArmServoOpenedPos);
                this.leftArmPos = ArmPosition.OPENED;
                this.fireEvent(new PixelCatcherLeftArmOpenEvent());
                break;
            case OPENED:
                this.leftServo.setPosition(this.config.leftArmServoClosedPos);
                this.leftArmPos = ArmPosition.CLOSED;
                this.fireEvent(new PixelCatcherLeftArmCloseEvent());
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
                this.fireEvent(new PixelCatcherRightArmOpenEvent());
                break;
            case OPENED:
                this.rightServo.setPosition(this.config.rightArmServoClosedPos);
                this.rightArmPos = ArmPosition.CLOSED;
                this.fireEvent(new PixelCatcherRightArmCloseEvent());
                break;
        }

    }

    /**
     *
     * @return
     */
    public ArmPosition getLeftArmPosition () {
        return this.leftArmPos;
    }

    /**
     *
     * @return
     */
    public ArmPosition getRightArmPosition () {
        return this.rightArmPos;
    }
}
