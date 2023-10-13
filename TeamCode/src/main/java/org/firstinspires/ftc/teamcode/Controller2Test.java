package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.Control;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.arm.Arm;
import org.firstinspires.ftc.teamcode.library.arm.ArmConfiguration;
import org.firstinspires.ftc.teamcode.library.arm.boom.BoomConfiguration;
import org.firstinspires.ftc.teamcode.library.component.Component;

@TeleOp(name="Controller2Test", group="Linear OpMode")
@Disabled
public class Controller2Test extends IsaacBot {

    private TestComponent testComponent;

    public Controller2Test(){
        super();

        this.testComponent = new TestComponent(this);
    }


    @Override
    public void runOpMode() throws InterruptedException {
        this.testComponent.init();

        this.testComponent.addGp2_A_PressHandler(event -> {
            Controller2Test.this.telemetry.addLine("A pressed...");
            Controller2Test.this.telemetry.update();
        });

        this.testComponent.addGp2_B_PressHandler(event -> {
            Controller2Test.this.telemetry.addLine("B pressed...");
            Controller2Test.this.telemetry.update();
        });

        this.testComponent.addGp2_X_PressHandler(event -> {
            Controller2Test.this.telemetry.addLine("X pressed...");
            Controller2Test.this.telemetry.update();
        });

        this.testComponent.addGp2_Y_PressHandler(event -> {
            Controller2Test.this.telemetry.addLine("Y pressed...");
            Controller2Test.this.telemetry.update();
        });

        this.waitForStart();

        while (this.opModeIsActive()) {
            this.getEventBus().run();
        }
    }
}

class TestComponent extends Component {

    /**
     * @param robot
     */
    public TestComponent(IsaacBot robot) {
        super(robot);
    }
}

