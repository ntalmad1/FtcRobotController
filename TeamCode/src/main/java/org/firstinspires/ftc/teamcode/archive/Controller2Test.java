package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.component.Component;

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

        this.testComponent.addGp2_Left_Bumper_PressHandler(event -> {
            Controller2Test.this.telemetry.addLine("Left Bumper Holding...");
            Controller2Test.this.telemetry.update();
        });

        this.testComponent.addGp2_Right_Bumper_PressHandler(event -> {
            Controller2Test.this.telemetry.addLine("Right Bumper Holding...");
            Controller2Test.this.telemetry.update();
        });

        this.testComponent.addGp2_Dpad_Left_DownHandler(event -> {
            Controller2Test.this.telemetry.addLine("Dpad Left Holding...");
            Controller2Test.this.telemetry.update();
        });

        this.testComponent.addGp2_Dpad_Right_DownHandler(event -> {
            Controller2Test.this.telemetry.addLine("Dpad Right Holding...");
            Controller2Test.this.telemetry.update();
        });

        this.testComponent.addGp2_Dpad_Up_DownHandler(event -> {
            Controller2Test.this.telemetry.addLine("Dpad Up Holding...");
            Controller2Test.this.telemetry.update();
        });

        this.testComponent.addGp2_Dpad_Down_DownHandler(event -> {
            Controller2Test.this.telemetry.addLine("Dpad Down Holding...");
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

