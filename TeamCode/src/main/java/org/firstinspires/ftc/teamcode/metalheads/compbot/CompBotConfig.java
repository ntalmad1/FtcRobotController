package org.firstinspires.ftc.teamcode.metalheads.compbot;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.drivetrain.RoadrunnerDriveTrainConfig;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.potentiometer.PotentiometerConfig;
import org.firstinspires.ftc.teamcode.library.potentiometermotor.PotentiometerMotorConfig;
import org.firstinspires.ftc.teamcode.library.rotator.RotatorConfig;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponentConfig;
import org.firstinspires.ftc.teamcode.library.utility.Control;
import org.firstinspires.ftc.teamcode.metalheads.components.ArmConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.ClawConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.DoubleHooksConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.FlapperBarsConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.IntakeConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.WinchConfig;

/**
 *
 */
public class CompBotConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public ArmConfig armConfig;

    /**
     */
    public ClawConfig clawConfig;

    /**
     */
    public DoubleHooksConfig doubleHooksConfig;

    /**
     */
    public FlapperBarsConfig flapperBarsConfig;

    /**
     */
    public IntakeConfig intakeConfig;

    /**
     */
    public RoadrunnerDriveTrainConfig driveTrainConfig;

    /**
     */
    public WinchConfig winchConfig;

    /**
     */
    public boolean useDriveTrain = true;

    /**
     */
    public boolean useArm = true;

    /**
     */
    public boolean useClaw = true;

    /**
     */
    public boolean useDoubleHooks = true;

    /**
     */
    public boolean useFlapperBars = true;

    /**
     */
    public boolean useIntake = true;

    /**
     */
    public boolean useWinch = true;

    /**
     */
    public boolean debugAll = false;

    /**
     */
    public boolean debugDriveTrain = false;

    /**
     */
    public boolean debugArm = false;

    /**
     */
    public boolean debugClaw = false;

    /**
     */
    public boolean debugDoubleHooks = false;

    /**
     */
    public boolean debugFlapperBars = false;

    /**
     */
    public boolean debugIntake = false;

    /**
     */
    public boolean debugWinch = false;

    /**
     * Constructor
     *
     * @param robot
     */
    public CompBotConfig(IsaacBot robot) {
        this.robot = robot;

        // driveTrain
        this.configureDriveTrain(robot);

        // arm
        this.configureArm(robot);

        // claw
        this.configureClaw(robot);

        // double hooks
        this.configureDoubleHooks(robot);

        // flapper bars
        this.configureFlapperBars(robot);

        // intake
        this.configureIntake(robot);

        // winch
        this.configureWinch(robot);
    }

    /**
     *
     * @param robot
     */
    private void configureWinch(IsaacBot robot) {
        this.winchConfig = new WinchConfig(robot);
        this.winchConfig.motorName = "winch";
        this.winchConfig.minTics = -90000;
        this.winchConfig.maxTics = 90000;
    }

    /**
     *
     * @param robot
     */
    private void configureFlapperBars(IsaacBot robot) {
        this.flapperBarsConfig = new FlapperBarsConfig(robot);
        this.flapperBarsConfig.isDualServo = true;
        this.flapperBarsConfig.servoName = "leftFlapper";
        this.flapperBarsConfig.zeroDegreePosition = 0;
        this.flapperBarsConfig.homePosition = 0;
        this.flapperBarsConfig.minPosition = 0;
        this.flapperBarsConfig.maxPosition = 1;
        this.flapperBarsConfig.secondaryServoName = "rightFlapper";
        this.flapperBarsConfig.maxIncrement = 0.001;
    }

    /**
     *
     * @param robot
     */
    private void configureDoubleHooks(IsaacBot robot) {
        this.doubleHooksConfig = new DoubleHooksConfig(robot);
        this.doubleHooksConfig.linearActuatorConfig = new EncodedMotorConfig(robot);
        this.doubleHooksConfig.linearActuatorConfig.motorName = "linearActuator";
        this.doubleHooksConfig.linearActuatorConfig.minTics = 0;
        this.doubleHooksConfig.linearActuatorConfig.maxTics = 8574;

        this.doubleHooksConfig.doubleServosConfig = new ServoComponentConfig(robot);
        this.doubleHooksConfig.doubleServosConfig.isDualServo = true;
        this.doubleHooksConfig.doubleServosConfig.servoName = "leftActuatorArm";
        this.doubleHooksConfig.doubleServosConfig.homePosition = 0;
        this.doubleHooksConfig.doubleServosConfig.minPosition = 0;
        this.doubleHooksConfig.doubleServosConfig.maxPosition = 0.336;
        this.doubleHooksConfig.doubleServosConfig.zeroDegreePosition = 0;
        this.doubleHooksConfig.doubleServosConfig.secondaryServoName = "rightActuatorArm";
        this.doubleHooksConfig.doubleServosConfig.secondaryServoOffset = 1 - 0.979;
    }

    /**
     *
     * @param robot
     */
    private void configureArm(IsaacBot robot) {
        this.armConfig = new ArmConfig(robot);
        this.armConfig.viperSlideConfig = new PotentiometerMotorConfig(robot);
        this.armConfig.viperSlideConfig.motorName = "viperSlide";
        this.armConfig.viperSlideConfig.minTics = 100;
        this.armConfig.viperSlideConfig.maxTics = 2900;
        this.armConfig.viperSlideConfig.minVolts = 0.586;
        //this.armConfig.viperSlideConfig.maxVolts = 1.129;
        this.armConfig.viperSlideConfig.maxVolts = Constants.VIPER_SLIDE_VOLTS_MAX;
        this.armConfig.viperSlideConfig.brakeOn = true;

        this.armConfig.viperSlideConfig.potentiometerConfig = new PotentiometerConfig(robot);
        this.armConfig.viperSlideConfig.potentiometerConfig.potentiometerName = "pot";

        this.armConfig.mainBoomConfig = new EncodedMotorConfig(robot);
        this.armConfig.mainBoomConfig.motorName = "arm";
        this.armConfig.mainBoomConfig.minTics = -736;
        this.armConfig.mainBoomConfig.maxTics = 5000;
    }

    /**
     *
     * @param robot
     */
    private void configureDriveTrain(IsaacBot robot) {
        this.driveTrainConfig = new RoadrunnerDriveTrainConfig(robot);
        this.driveTrainConfig.yawOffset = 0;
        this.driveTrainConfig.imuName = "imuExternal";
    }

    /**
     *
     */
    private void configureClaw(IsaacBot robot) {
        this.clawConfig = new ClawConfig(robot);
        this.clawConfig.pincherConfig = new ServoComponentConfig(robot);
        this.clawConfig.pincherConfig.servoName = "pincherServo";
        this.clawConfig.pincherConfig.homePosition = 0.484;
        this.clawConfig.pincherConfig.zeroDegreePosition = 0.5;
        this.clawConfig.pincherConfig.minPosition = 0.484;
        this.clawConfig.pincherConfig.maxPosition = 0.65;

        this.clawConfig.clawRotatorConfig = new RotatorConfig(robot);
        this.clawConfig.clawRotatorConfig.servoName = "clawRotate";
        this.clawConfig.clawRotatorConfig.homePosition = 0;
        this.clawConfig.clawRotatorConfig.zeroDegreePosition = 0;
        this.clawConfig.clawRotatorConfig.minPosition = 0;
        this.clawConfig.clawRotatorConfig.maxPosition = 1;
        this.clawConfig.clawRotatorConfig.maxIncrement = 0.010;
    }

    /**
     *
     */
    private void configureIntake(IsaacBot robot) {

        this.intakeConfig = new IntakeConfig(robot);
        this.intakeConfig.pincherConfig = new ServoComponentConfig(robot);
        this.intakeConfig.pincherConfig.servoName = "intake";
        this.intakeConfig.pincherConfig.homePosition = 0.5;
        this.intakeConfig.pincherConfig.zeroDegreePosition = 0.5;
        this.intakeConfig.pincherConfig.minPosition = Constants.INTAKE_PINCHER_OPEN_POS;
        this.intakeConfig.pincherConfig.maxPosition = Constants.INTAKE_PINCHER_CLOSE_POS;

        this.intakeConfig.hServoConfig = new RotatorConfig(robot);
        this.intakeConfig.hServoConfig.servoName = "intakeHorizontal";
        this.intakeConfig.hServoConfig.controllerInputMethod = Control.Gp1_LeftStickX;
        this.intakeConfig.hServoConfig.zeroDegreePosition = 0.5;
        this.intakeConfig.hServoConfig.homePosition = 0.5011;
        this.intakeConfig.hServoConfig.minPosition = 0;
        this.intakeConfig.hServoConfig.maxPosition = 1;
        this.intakeConfig.hServoConfig.maxIncrement = 0.070;

        this.intakeConfig.vServoConfig = new RotatorConfig(robot);
        this.intakeConfig.vServoConfig.servoName = "intakeVertical";
        this.intakeConfig.vServoConfig.controllerInputMethod = Control.Gp1_LeftStickY;
        this.intakeConfig.vServoConfig.zeroDegreePosition = 0.5;
        this.intakeConfig.vServoConfig.homePosition = Constants.INTAKE_V_SERVO_INIT_POS;
        this.intakeConfig.vServoConfig.minPosition = 0.0;
        this.intakeConfig.vServoConfig.maxPosition = 0.674;
        this.intakeConfig.vServoConfig.maxIncrement = 0.005;
    }
}
