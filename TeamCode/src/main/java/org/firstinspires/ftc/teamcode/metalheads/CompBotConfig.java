package org.firstinspires.ftc.teamcode.metalheads;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.continuousservo.ContinuousServoConfig;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
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
    public WinchConfig winchConfig;

    /**
     */
    public boolean debugAll = false;

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

        // arm
        this.armConfig = new ArmConfig(robot);
        this.armConfig.viperSlideConfig = new EncodedMotorConfig(robot);
        this.armConfig.viperSlideConfig.increment = 10000;
        this.armConfig.viperSlideConfig.motorName = "viperSlide";
        this.armConfig.viperSlideConfig.minTics = 100;
        this.armConfig.viperSlideConfig.maxTics = 2900;
        this.armConfig.viperSlideConfig.brakeOn = true;
        this.armConfig.viperSlideConfig.control = Control.Gp2_RightStickX;

        this.armConfig.mainBoomConfig = new EncodedMotorConfig(robot);
        this.armConfig.mainBoomConfig.increment = 10000;
        this.armConfig.mainBoomConfig.motorName = "arm";
        this.armConfig.mainBoomConfig.minTics = -736;
        this.armConfig.mainBoomConfig.maxTics = 5000;
        this.armConfig.mainBoomConfig.control = Control.Gp2_LeftStickY;

        // claw
        this.clawConfig = new ClawConfig(robot);

        // double hooks
        this.doubleHooksConfig = new DoubleHooksConfig(robot);
        this.doubleHooksConfig.linearActuatorConfig = new EncodedMotorConfig(robot);
        this.doubleHooksConfig.linearActuatorConfig.motorName = "linearActuator";
        this.doubleHooksConfig.linearActuatorConfig.increment = 10000;
        this.doubleHooksConfig.linearActuatorConfig.minTics = 0;
        this.doubleHooksConfig.linearActuatorConfig.maxTics = 8574;
        this.doubleHooksConfig.linearActuatorConfig.control = Control.Gp2_Dpad_UpDown;

        this.doubleHooksConfig.doubleServosConfig = new ServoComponentConfig(robot);
        this.doubleHooksConfig.doubleServosConfig.isDualServo = true;
        this.doubleHooksConfig.doubleServosConfig.servoName = "leftActuatorArm";
        this.doubleHooksConfig.doubleServosConfig.homePosition = 0;
        this.doubleHooksConfig.doubleServosConfig.minPosition = 0;
        this.doubleHooksConfig.doubleServosConfig.maxPosition = 0.336;
        this.doubleHooksConfig.doubleServosConfig.zeroDegreePosition = 0;
        this.doubleHooksConfig.doubleServosConfig.secondaryServoName = "rightActuatorArm";
        this.doubleHooksConfig.doubleServosConfig.secondaryServoOffset = 1 - 0.979;
        this.doubleHooksConfig.doubleServosConfig.controllerInputMethod = Control.Gp2_Dpad_Left;
        this.doubleHooksConfig.doubleServosConfig.controllerInputMethod2 = Control.Gp2_Dpad_Right;

        // flapper bars
        this.flapperBarsConfig = new FlapperBarsConfig(robot);
        this.flapperBarsConfig.isDualServo = true;
        this.flapperBarsConfig.servoName = "leftFlapper";
        this.flapperBarsConfig.zeroDegreePosition = 0;
        this.flapperBarsConfig.homePosition = 0;
        this.flapperBarsConfig.minPosition = 0;
        this.flapperBarsConfig.maxPosition = 1;
        this.flapperBarsConfig.secondaryServoName = "rightFlapper";
        this.flapperBarsConfig.controllerInputMethod = Control.Gp2_RightStickY;

        // intake
        this.intakeConfig = new IntakeConfig(robot);
        intakeConfig.rollerConfig = new ContinuousServoConfig(robot);
        intakeConfig.rollerConfig.servoName = "intake";

        this.intakeConfig.hServoConfig = new RotatorConfig(robot);
        this.intakeConfig.hServoConfig.servoName = "intakeHorizontal";
        this.intakeConfig.hServoConfig.controllerInputMethod = Control.Gp1_LeftStickX;
        this.intakeConfig.hServoConfig.zeroDegreePosition = 0.5;
        this.intakeConfig.hServoConfig.homePosition = 0.5;
        this.intakeConfig.hServoConfig.minPosition = 0.15;
        this.intakeConfig.hServoConfig.maxPosition = 0.80;

        this.intakeConfig.vServoConfig = new RotatorConfig(robot);
        this.intakeConfig.vServoConfig.servoName = "intakeVertical";
        this.intakeConfig.vServoConfig.controllerInputMethod = Control.Gp1_LeftStickY;
        this.intakeConfig.vServoConfig.zeroDegreePosition = 0.5;
        this.intakeConfig.vServoConfig.homePosition = 0.5;
        this.intakeConfig.vServoConfig.minPosition = 0.233;
        this.intakeConfig.vServoConfig.maxPosition = 0.674;

        // winch
        this.winchConfig = new WinchConfig(robot);
        this.winchConfig.control = Control.Gp1_RightStickY;
        this.winchConfig.motorName = "winch";
        this.winchConfig.minTics = 0;
        this.winchConfig.maxTics = 9999;
        this.winchConfig.increment = 10000;

    }
}
