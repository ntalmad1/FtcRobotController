package org.firstinspires.ftc.library.dronelauncher;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.component.Component;
import org.firstinspires.ftc.library.rotator.Rotator;

/**
 *
 */
public class DroneLauncher extends Component {

    /**
     *
     */
    protected DroneLauncherConfig config;

    /**
     *
     */
    protected Rotator rotator;

    /**
     *
     */
    protected Servo trigger;

    /**
     * Constructor
     *
     * @param config The configuration values for the drone launcher
     */
    public DroneLauncher (DroneLauncherConfig config) {
        super(config.robot);

        this.config = config;
    }

    /**
     *
     */
    public void init () {
        super.init();

        this.trigger = this.robot.hardwareMap.get(Servo.class, this.config.triggerServoName);
        this.trigger.resetDeviceConfigurationForOpMode();
        this.trigger.setPosition(this.config.triggerServoDownPos);

        this.rotator = new Rotator(this.config.rotatorConfig);
        this.rotator.init();
    }

    /**
     *
     */
    public void run () {
        super.run();

        this.rotator.run();
    }

    /**
     *
     * @param position The position to rotate the launcher to
     * @return DroneLauncher - fluid interface
     */
    public DroneLauncher rotateToPosition (double position) {
        this.rotator.gotoPosition(position);
        return this;
    }
}
