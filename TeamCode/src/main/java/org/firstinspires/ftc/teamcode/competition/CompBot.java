package org.firstinspires.ftc.teamcode.competition;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.competition.config.ArmCompConfig;
import org.firstinspires.ftc.teamcode.competition.config.MecanumDriveCompConfig;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.arm.Arm;
import org.firstinspires.ftc.teamcode.library.arm.ArmConfig;
import org.firstinspires.ftc.teamcode.library.component.event.EventBus;
import org.firstinspires.ftc.teamcode.library.drivetrain.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.library.drivetrain.MecanumDriveTrainConfig;

/**
 *
 */
@TeleOp(name="CompBot", group="Linear OpMode")
@Disabled
public class CompBot extends IsaacBot{

    /**
     */
    protected ArmCompConfig armConfig;

    /**
     */
    protected MecanumDriveCompConfig driveTrainConfig;

    /**
     */
    private Arm arm;

    /**
     */
    private MecanumDriveTrain driveTrain;

    public CompBot() {
        super();

        this.armConfig = new ArmCompConfig(this);

        this.driveTrainConfig = new MecanumDriveCompConfig(this);
    }

    /**
     *
     */
    public void initBot () {
        super.initBot();

        this.arm = new Arm(armConfig);
        this.driveTrain = new MecanumDriveTrain(driveTrainConfig);

        this.driveTrain.init();
        this.arm.init();


    }

    int loop = 0;

    public void run () {
        super.run();

        this.driveTrain.run();
        this.arm.run();
    }


}
