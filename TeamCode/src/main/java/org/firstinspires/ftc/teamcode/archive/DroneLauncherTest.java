package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.archive.competition.config.DroneLauncherCompConfig;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.dronelauncher.DroneLauncher;
import org.firstinspires.ftc.teamcode.library.dronelauncher.DroneLauncherConfig;
import org.firstinspires.ftc.teamcode.library.event.gp2_a_press.Gp2_A_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_a_press.Gp2_A_PressHandler;
import org.firstinspires.ftc.teamcode.library.utility.Control;


@TeleOp(name="DroneLauncherTest", group="Tests")
@Disabled
public class DroneLauncherTest extends IsaacBot {

    private DroneLauncher droneLauncher;

    public DroneLauncherTest() {
        super();

        DroneLauncherConfig config = new DroneLauncherCompConfig(this);

        config.rotatorConfig.controllerInputMethod = Control.Gp2_LeftStickY;
        config.rotatorConfig.debug = true;
        config.rotatorConfig.maxIncrement = 0.001;

        this.droneLauncher = new DroneLauncher(config);
    }

    /**
     *
     */
    public void initBot () {
        super.initBot();
        this.droneLauncher.init();

        this.droneLauncher.addGp2_A_PressHandler(new Gp2_A_PressHandler() {
            @Override
            public void onGp2_A_Press(Gp2_A_PressEvent event) {
                DroneLauncherTest.this.droneLauncher.toggleTriggger();
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
        super.run();

        this.droneLauncher.run();
    }

}
