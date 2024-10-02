package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;

/**
 *
 */
public class Arm extends Component {

    /**
     */
    public ArmConfig config;

    /**
     */
    public EncodedMotor mainBoom;

    /**
     */
    public EncodedMotor viperSlide;

    /**
     * Constructor
     *
     * @param armConfig
     */
    public Arm(ArmConfig armConfig) {
        super(armConfig.robot);

        this.config = armConfig;

        this.mainBoom = new EncodedMotor(this.config.mainBoomConfig);
        this.viperSlide = new EncodedMotor(this.config.viperSlideConfig);
    }

    /**
     *
     */
    @Override
    public void init() {
        super.init();

        this.mainBoom.init();
        this.viperSlide.init();
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        this.mainBoom.run();
        this.viperSlide.run();

        if (this.isDebug()) {

            telemetry.addLine("Arm");

            telemetry.addData("   Main Boom Position:", this.mainBoom.getCurrentPosition());
            telemetry.addData("   Viper Slide Position:", this.viperSlide.getCurrentPosition());
            telemetry.addData("   VS ZeroPower Behav:", this.viperSlide.getZeroPowerBehavior());
        }
    }
}
