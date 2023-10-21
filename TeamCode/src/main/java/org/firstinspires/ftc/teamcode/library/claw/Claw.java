package org.firstinspires.ftc.teamcode.library.claw;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.boom.Boom;
import org.firstinspires.ftc.teamcode.library.rotator.Rotator;
import org.firstinspires.ftc.teamcode.library.component.Component;

/**
 *
 */
public class Claw extends Component {

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
        this.clawBoom.init();
        this.clawRotator.init();

        this.leftClaw = this.robot.hardwareMap.get(Servo.class, this.config.leftClawName);
        this.rightClaw = this.robot.hardwareMap.get(Servo.class, this.config.rightClawName);

        this.leftClaw.resetDeviceConfigurationForOpMode();
        this.rightClaw.resetDeviceConfigurationForOpMode();

        this.leftClaw.setPosition(this.config.leftClawMinPosition);
        this.rightClaw.setPosition(this.config.rightClawMinPosition);

        this.addGp2_Left_Bumper_PressHandler(event -> {
            Claw.this.toggleLeftClaw();
        });

        this.addGp2_Right_Bumper_PressHandler(event -> {
            Claw.this.toggleRightClaw();
        });
    }

    /**
     *
     */
    public void run () {
        this.clawBoom.run();
        this.clawRotator.run();
    }

    /**
     *
     */
    public void toggleLeftClaw () {
        this.isLeftOpen = !this.isLeftOpen;
        double pos = this.isLeftOpen ? this.config.leftClawMaxPosition : this.config.leftClawMinPosition;
        this.leftClaw.setPosition(pos);
    }

    /**
     *
     */
    public void toggleRightClaw () {
        this.isRightOpen = !this.isRightOpen;
        double pos = this.isRightOpen ? this.config.rightClawMaxPosition : this.config.rightClawMinPosition;
        this.rightClaw.setPosition(pos);

    }
}
