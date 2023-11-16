package org.firstinspires.ftc.library.claw;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.claw.events.leftpincherclose.ClawLeftPincherCloseEvent;
import org.firstinspires.ftc.library.claw.events.leftpincheropen.ClawLeftPincherOpenEvent;
import org.firstinspires.ftc.library.claw.events.rightpincherclose.ClawRightPincherCloseEvent;
import org.firstinspires.ftc.library.claw.events.rightpincheropen.ClawRightPincherOpenEvent;
import org.firstinspires.ftc.library.rotator.Rotator;
import org.firstinspires.ftc.teamcode.metalheads.competition.boom.Boom;
import org.firstinspires.ftc.library.component.Component;

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
    private Rotator clawRotator;

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
     */
    private boolean rotatedRight = false;

    /**
     */
    private boolean rotatedLeft = false;

    /**
     * Constructor
     *
     */
    public Claw(ClawConfig config) {
        super(config.robot);
        this.config = config;

        this.clawBoom = new Boom(config.clawBoomConfig);
        this.clawRotator = new Rotator(config.clawRotatorConfig);
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
        this.clawRotator.init();



        this.addGp2_Left_Bumper_PressHandler(event -> {
            Claw.this.toggleLeftClaw();
        });

        this.addGp2_Right_Bumper_PressHandler(event -> {
            Claw.this.toggleRightClaw();
        });

        this.addGp2_Left_Trigger_DownHandler(event -> {
            if (Claw.this.rotatedLeft) {
                Claw.this.rotatedLeft = false;
                Claw.this.clawRotator.gotoPosition(Claw.this.config.clawRotatorConfig.homePosition, 1);
            }
            else {
                Claw.this.rotatedLeft = true;
                Claw.this.clawRotator.gotoPosition(0.65, 1);
            }
        });

        this.addGp2_Right_Trigger_DownHandler(event -> {
            if (Claw.this.rotatedRight) {
                Claw.this.rotatedRight = false;
                Claw.this.clawRotator.gotoPosition(Claw.this.config.clawRotatorConfig.homePosition, 1);
            }
            else {
                Claw.this.rotatedRight = true;
                Claw.this.clawRotator.gotoPosition(0, 1);
            }
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
    public Rotator getRotator () {
        return this.clawRotator;
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
        this.clawRotator.run();
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

        this.fireEvent(new ClawLeftPincherCloseEvent());
    }

    /**
     *
     */
    public void closeRight () {
        this.rightClaw.setPosition(this.config.rightClawMinPosition);
        this.isRightOpen = false;

        this.fireEvent(new ClawRightPincherCloseEvent());
    }

    /**
     *
     */
    public void openLeft () {
        this.leftClaw.setPosition(this.config.leftClawMaxPosition);
        this.isLeftOpen = true;

        this.fireEvent(new ClawLeftPincherOpenEvent());
    }

    /**
     *
     */
    public void openRight () {
        this.rightClaw.setPosition(this.config.rightClawMaxPosition);
        this.isRightOpen = true;

        this.fireEvent(new ClawRightPincherOpenEvent());
    }
}
