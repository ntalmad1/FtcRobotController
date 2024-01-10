package org.firstinspires.ftc.teamcode.metalheads.competition.config;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.dronelauncher.DroneLauncherConfig;
import org.firstinspires.ftc.library.rotator.RotatorConfig;

/**
 *
 */
public class DroneLauncherCompConfig extends DroneLauncherConfig {

    /**
     * Constructor
     *
     * @param robot
     */
    public DroneLauncherCompConfig (IsaacBot robot) {
        this.robot = robot;

        this.triggerServoName = "droneTrigger";

        this.triggerServoDownPos = 0.58;

        this.triggerServoUpPos = 0.43;


        this.rotatorConfig = new RotatorConfig();
        this.rotatorConfig.robot = robot;
        this.rotatorConfig.servoName = "droneBase";
        this.rotatorConfig.direction = Servo.Direction.FORWARD;

        this.rotatorConfig.invertInput = false;

        this.rotatorConfig.minPosition = 0;

        this.rotatorConfig.maxPosition = 1;

        this.rotatorConfig.zeroDegreePosition =0.5;

        this.rotatorConfig.homePosition = 0.44;

        this.rotatorConfig.maxIncrement = 0.01;

        this.rotatorConfig.debug = false;

        this.launchPosition = 0.3;
    }
}
