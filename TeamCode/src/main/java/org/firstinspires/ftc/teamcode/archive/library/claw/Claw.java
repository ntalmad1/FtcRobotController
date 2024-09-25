package org.firstinspires.ftc.teamcode.archive.library.claw;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.archive.library.boom.Boom;
import org.firstinspires.ftc.teamcode.library.component.Component;

/**
 *
 */
public class Claw extends Component {

    /**
     *
     */
    public enum Side {
        LEFT,
        RIGHT
    }


    /**
     */
    private Boom clawBoom;

    /**
     */
    private ClawConfig config;

    /**
     */
    private Servo rightClaw;

    /**
     */
    private Servo leftClaw;

    /**
     */
    private boolean isLeftOpen = false;

    /**
     */
    private boolean isRightOpen = false;

    /**
     * Constructor
     *
     */
    public Claw(ClawConfig config) {
        super(config.robot);
        this.config = config;

        this.clawBoom = new Boom(config.clawBoomConfig);
    }

    /**
     *
     */
    public void init () {
        super.init();

        this.leftClaw = this.robot.hardwareMap.get(Servo.class, this.config.leftClawName);
        this.rightClaw = this.robot.hardwareMap.get(Servo.class, this.config.rightClawName);

        this.leftClaw.resetDeviceConfigurationForOpMode();
        this.rightClaw.resetDeviceConfigurationForOpMode();

        this.leftClaw.setPosition(this.config.leftClawInitPosition);
        this.rightClaw.setPosition(this.config.rightClawInitPosition);

        if (this.config.leftClawInitPosition > this.config.leftClawMinPosition) {
            this.isLeftOpen = true;
        }

        if (this.config.rightClawInitPosition > this.config.rightClawMinPosition) {
            this.isRightOpen = true;
        }

        this.clawBoom.init();

        this.addGp2_Left_Bumper_PressHandler(event -> {
            Claw.this.toggleLeftClaw();
        });

        this.addGp2_Right_Bumper_PressHandler(event -> {
            Claw.this.toggleRightClaw();
        });
    }

    /**
     *
     * @return
     */
    public Boom getBase () {
        return this.clawBoom;
    }

    /**
     *
     * @return
     */
    public ClawConfig getConfig () {
        return this.config;
    }

    /**
     *
     * @return
     */
    public boolean isLeftClosed () {
        return !this.isLeftOpen;
    }

    /**
     *
     * @return
     */
    public boolean isRightClosed () {
        return !this.isRightOpen;
    }

    /**
     *
     */
    public void run () {
        super.run();

        this.clawBoom.run();
    }

    /**
     *
     */
    public void toggleLeftClaw () {
        this.isLeftOpen = !this.isLeftOpen;
        if (this.isLeftOpen) {
            this.openLeft();
        }
        else {
            this.closeLeft();
        }
    }

    /**
     *
     */
    public void toggleRightClaw () {
        this.isRightOpen = !this.isRightOpen;
        if (this.isRightOpen) {
            this.openRight();
        }
        else {
            this.closeRight();
        }
    }

    /**
     *
     */
    public void closeLeft () {
        this.leftClaw.setPosition(this.config.leftClawMinPosition);
        this.isLeftOpen = false;
    }

    /**
     *
     */
    public void closeRight () {
        this.rightClaw.setPosition(this.config.rightClawMinPosition);
        this.isRightOpen = false;
    }

    /**
     *
     */
    public void openLeft () {
        this.leftClaw.setPosition(this.config.leftClawMaxPosition);
        this.isLeftOpen = true;
    }

    /**
     *
     */
    public void openRight () {
        this.rightClaw.setPosition(this.config.rightClawMaxPosition);
        this.isRightOpen = true;
    }
}
