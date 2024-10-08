package org.firstinspires.ftc.teamcode.library.potentiometer;

import com.qualcomm.robotcore.hardware.AnalogInput;

import org.firstinspires.ftc.teamcode.library.component.Component;

/**
 *
 */
public class Potentiometer extends Component {

    /**
     */
    private AnalogInput analogInput;

    /**
     */
    private PotentiometerConfig config;

    /**
     * Constructor
     *
     * @param potentiometerConfig
     */
    public Potentiometer(PotentiometerConfig potentiometerConfig) {
        super(potentiometerConfig.robot);

        this.config = potentiometerConfig;
    }

    /**
     *
     */
    @Override
    public void init() {
        super.init();

        this.analogInput = this.robot.hardwareMap.get(AnalogInput.class, this.config.potentiometerName);
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();
    }

    /**
     *
     * @return
     */
    public double getVoltage() {
        return this.analogInput.getVoltage();
    }
}
