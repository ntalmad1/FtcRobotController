package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.drivetrain.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.library.drivetrain.MecanumDriveTrainConfig;

@TeleOp(name="MecanumTest", group="Tests")
public class MecanumTest extends IsaacBot {

    /**
     */
    private MecanumDriveTrain driveTrain;

    public MecanumTest() {

        MecanumDriveTrainConfig config = new MecanumDriveTrainConfig();
        config.robot = this;
        config.leftFrontDeviceName = "leftFrontMotor";
        config.rightFrontDeviceName = "rightFrontMotor";
        config.rightRearDeviceName = "rightRearMotor";
        config.leftRearDeviceName = "leftRearMotor";
        config.imuName = "exIMU";
        config.maxPower = 1;
        config.accelerationIncrement = 0.05;
        config.debug = true;

        driveTrain = new MecanumDriveTrain(config);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        driveTrain.init();

        this.voiceLog("System Armed");

        waitForStart();



        while(this.opModeIsActive()) {
            driveTrain.run();
        }

        this.voiceLog("Disarming System");
    }
}
