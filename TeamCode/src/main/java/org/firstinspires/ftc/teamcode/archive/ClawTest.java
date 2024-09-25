package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.archive.competition.config.ClawCompConfig;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.archive.library.claw.Claw;
import org.firstinspires.ftc.teamcode.archive.library.claw.ClawConfig;
import org.firstinspires.ftc.teamcode.library.event.EventBus;

@TeleOp(name="ClawTest", group="Linear OpMode")
@Disabled
public class ClawTest extends IsaacBot {

    private Claw claw;

    public ClawTest() {
        ClawConfig clawConfig = new ClawCompConfig(this);


        this.claw = new Claw(clawConfig);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        this.claw.init();

        this.waitForStart();

        while (this.opModeIsActive()) {
            EventBus.getInstance().run();
            this.claw.run();
        }

    }
}
