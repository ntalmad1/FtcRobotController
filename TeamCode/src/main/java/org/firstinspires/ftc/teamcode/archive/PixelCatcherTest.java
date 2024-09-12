package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcher;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.PixelCatcherCompConfig;

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
