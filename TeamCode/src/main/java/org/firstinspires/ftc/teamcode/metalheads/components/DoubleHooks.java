package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponent;

/**
 *
 */
public class DoubleHooks extends Component {

    /**
     */
    private DoubleHooksConfig config;

    /**
     */
    public EncodedMotor linearActuator;

    /**
     */
    public ServoComponent doubleServos;

    /**
     */
    private boolean initialized = false;

    /**
     * Constructor
     * @param doubleHooksConfig
     */
    public DoubleHooks(DoubleHooksConfig doubleHooksConfig) {
        super(doubleHooksConfig.robot);
        this.config = doubleHooksConfig;
    }

    public void init() {
        this.linearActuator = new EncodedMotor(this.config.linearActuatorConfig);
        this.doubleServos = new ServoComponent(this.config.doubleServosConfig);

        this.linearActuator.init();
        this.doubleServos.init();

        this.initialized = true;
    }

    /**
     */
    @Override
    public void run() {
        if (this.initialized) {
            super.run();

            this.doubleServos.run();
            this.linearActuator.run();

            if (this.isDebug()) {
                telemetry.addData("Linear Act Pos: ", this.linearActuator.getCurrentPosition());
                telemetry.addData("Double Hooks Servo Pos", this.doubleServos.getPosition());
            }
        }
    }
}
