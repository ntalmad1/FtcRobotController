package org.firstinspires.ftc.library.pixelcatcher;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.component.Component;
import org.firstinspires.ftc.library.pixelcatcher.events.leftarmclose.PixelCatcherLeftArmCloseEvent;
import org.firstinspires.ftc.library.pixelcatcher.events.leftarmopen.PixelCatcherLeftArmOpenEvent;
import org.firstinspires.ftc.library.pixelcatcher.events.rightarmclose.PixelCatcherRightArmCloseEvent;
import org.firstinspires.ftc.library.pixelcatcher.events.rightarmopen.PixelCatcherRightArmOpenEvent;
import org.firstinspires.ftc.library.servo.ServoComponent;
import org.firstinspires.ftc.library.utility.Control;

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

    public enum WinchPosition {
        UP,
        DOWN
    }

    /**
     */
    protected PixelCatcherConfig config;

    /**
     */
    private ServoComponent leftServo;

    /**
     */
    private ServoComponent rightServo;

    /**
     *
     */
    private ArmPosition leftArmPos;

    /**
     *
     */
    private ArmPosition rightArmPos;

    /**
     *
     */
    private WinchPosition winchPosition;

    /**
     */
    private Servo winchServo;

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

        this.leftServo = new ServoComponent(this.config.leftArmServoConfig);
        this.leftServo.init();

        this.rightServo = new ServoComponent(this.config.rightArmServoConfig);
        this.rightServo.init();

        this.leftArmPos = this.config.leftArmInitPos;
        this.rightArmPos = this.config.rightArmInitPos;
        this.winchPosition = this.config.winchInitPosition;

        this.winchServo = this.robot.hardwareMap.get(Servo.class, config.winchServoName);
        this.winchServo.resetDeviceConfigurationForOpMode();

        this.winchServo.setPosition(this.config.winchServoInitPos);

        if (this.config.leftArmToggle == Control.Gp1_LeftTrigger_Down) {
            this.addGp1_Left_Trigger_DownHandler(event -> PixelCatcher.this.toggleLeftArm());
        }

        if (this.config.rightArmToggle == Control.Gp1_RightTrigger_Down) {
            this.addGp1_Right_Trigger_DownHandler(event -> PixelCatcher.this.toggleRightArm());
        }

        if (this.config.winchToggle1 == Control.Gp1_RightBumper_Down || this.config.winchToggle2 == Control.Gp1_RightBumper_Down) {
            this.addGp1_Right_Bumper_DownHandler(event -> PixelCatcher.this.toggleWinch());
        }

        if (this.config.winchToggle1 == Control.Gp1_LeftBumper_Down || this.config.winchToggle2 == Control.Gp1_LeftBumper_Down) {
            this.addGp1_Left_Bumper_DownHandler(event -> PixelCatcher.this.toggleWinch());
        }

        this.moveLeftArmToPosition(this.config.leftArmServoInitPos);
        this.moveRightArmToPosition(this.config.rightArmServoInitPos);
    }

    /**
     *
     */
    public void run () {
        super.run();

        this.leftServo.run();
        this.rightServo.run();
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

    /**
     *
     * @return
     */
    public WinchPosition getWinchPosition () {
        return this.winchPosition;
    }

    /**
     *
     * @param position
     */
    public void moveLeftArmToPosition (double position) {
        this.leftServo.gotoPosition(position);
    }

    /**
     *
     * @param position
     */
    public void moveRightArmToPosition (double position) {
        this.rightServo.gotoPosition(position);
    }

    /**
     *
     */
    public void toggleLeftArm () {
        switch (this.leftArmPos) {
            case CLOSED:
                this.moveLeftArmToPosition(this.config.leftArmServoOpenedPos);
                this.leftArmPos = ArmPosition.OPENED;
                this.fireEvent(new PixelCatcherLeftArmOpenEvent());
                break;
            case OPENED:
                this.moveLeftArmToPosition(this.config.leftArmServoClosedPos);
                this.leftArmPos = ArmPosition.CLOSED;
                this.fireEvent(new PixelCatcherLeftArmCloseEvent());
                break;
        }
    }

    /**
     *
     * @param pos
     */
    public void setLeftArmPosition (double pos) {
        this.moveLeftArmToPosition(pos);
    }

    /**
     *
     * @param pos
     */
    public void setRightArmPosition (double pos) {
        this.moveRightArmToPosition(pos);
    }


    /**
     *
     */
    public void toggleRightArm () {
        switch (this.rightArmPos) {
            case CLOSED:
                this.moveRightArmToPosition(this.config.rightArmServoOpenedPos);
                this.rightArmPos = ArmPosition.OPENED;
                this.fireEvent(new PixelCatcherRightArmOpenEvent());
                break;
            case OPENED:
                this.moveRightArmToPosition(this.config.rightArmServoClosedPos);
                this.rightArmPos = ArmPosition.CLOSED;
                this.fireEvent(new PixelCatcherRightArmCloseEvent());
                break;
        }

    }

    /**
     *
     */
    public void toggleWinch () {
       switch (this.winchPosition) {
            case DOWN:
                this.winchServo.setPosition(this.config.winchUpPosition);
                this.winchPosition = WinchPosition.UP;
                break;
            case UP:
                this.winchServo.setPosition(this.config.winchDownPosition);
                this.winchPosition = WinchPosition.DOWN;
                break;
        }
    }

    /**
     *
     * @param milliseconds
     * @return
     */
    public PixelCatcher wait (int milliseconds) {
        return (PixelCatcher) super.wait(milliseconds);
    }
}