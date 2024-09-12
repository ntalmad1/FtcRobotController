package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.component.event.gp2_a_press.Gp2_A_PressEvent;
import org.firstinspires.ftc.library.component.event.gp2_a_press.Gp2_A_PressHandler;
import org.firstinspires.ftc.library.dronelauncher.DroneLauncher;
import org.firstinspires.ftc.library.dronelauncher.DroneLauncherConfig;
import org.firstinspires.ftc.library.utility.Control;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.DroneLauncherCompConfig;

@TeleOp(name="DroneLauncherTest", group="Tests")
//@Disabled
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
