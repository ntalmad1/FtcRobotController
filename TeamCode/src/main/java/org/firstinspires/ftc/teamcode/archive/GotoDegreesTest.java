package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.archive.competition.config.SimpleDriveCompConfig;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrainConfig;
import org.firstinspires.ftc.teamcode.library.event.gp2_a_press.Gp2_A_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_a_press.Gp2_A_PressHandler;


@TeleOp(name="GotoDegreesTest", group="Linear OpMode")
@Disabled
public class GotoDegreesTest extends IsaacBot {

    private SimpleDriveTrain driveTrain;

    public GotoDegreesTest () {
        super();

        SimpleDriveTrainConfig config = new SimpleDriveCompConfig(this);
        this.driveTrain = new SimpleDriveTrain(config);

        this.setImuName(this.driveTrain.getConfig().imuName);


    }

    /**
     *
     */
    public void initBot () {
        super.initBot();
        this.driveTrain.init();

        this.driveTrain.addGp2_A_PressHandler(new Gp2_A_PressHandler() {
            @Override
            public void onGp2_A_Press(Gp2_A_PressEvent event) {
                GotoDegreesTest.this.driveTrain.gotoDegrees(0.2, 0.2, 90);
            }
        });
    }

    /**
     *
     */
    public void go () {
        super.go();
    }

    /**
     *
     */
    public void run () {
        //super.run();

        this.driveTrain.run();
    }

}
