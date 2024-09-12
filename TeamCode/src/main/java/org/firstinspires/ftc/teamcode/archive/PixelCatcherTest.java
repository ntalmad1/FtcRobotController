package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.archive.competition.config.PixelCatcherCompConfig;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.pixelcatcher.PixelCatcher;


@TeleOp(name="PixelCatcherTest", group="Tests")
@Disabled
public class PixelCatcherTest extends IsaacBot {

    private PixelCatcher pixelCatcher;

    public PixelCatcherTest(){
        super();
        this.pixelCatcher = new PixelCatcher(new PixelCatcherCompConfig(this));
    }

    @Override
    public void runOpMode() throws InterruptedException {
        this.pixelCatcher.init();

        this.waitForStart();

        while (this.opModeIsActive()) {

            this.getEventBus().run();
            this.pixelCatcher.run();

        }
    }
}
