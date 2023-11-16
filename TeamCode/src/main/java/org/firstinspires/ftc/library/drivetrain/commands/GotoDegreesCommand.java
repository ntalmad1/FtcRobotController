package org.firstinspires.ftc.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.library.component.command.AbstractSynchronousCommand;
import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.utility.Direction;

/**
 *
 */
public class GotoDegreesCommand extends AbstractDriveTrainGyroTurnCommand {

    protected Direction direction;



    /**
     *
     * @param driveTrain
     * @param direction
     * @param startPower
     * @param maxPower
     * @param degrees
     */
    public GotoDegreesCommand (SimpleDriveTrain driveTrain, Direction direction, double startPower, double maxPower, double degrees) {
        super(driveTrain, startPower, maxPower, degrees);

        this.direction = direction;
    }


    @Override
    public void init () {

        double yaw = this.driveTrain.getRobot().getYaw();

        double targetDegrees = this.degrees - Math.abs(yaw);

        if (targetDegrees < 0) {
            targetDegrees = Math.abs(targetDegrees);
            direction = direction.invert();
        }

        this.driveTrain.getRobot().telemetry.addData("Yaw: ", "%2f", yaw);
        this.driveTrain.getRobot().telemetry.addData("Degrees: ", "%2f", degrees);
        this.driveTrain.getRobot().telemetry.addData("Calculated degrees to turn: ", "%2f", targetDegrees);
        this.driveTrain.getRobot().telemetry.update();


        if (direction.equals(Direction.RIGHT)) {
            this.degrees = -targetDegrees;

            this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.REVERSE);
            this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);
            this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.REVERSE);
            this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);

            this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        else {
            this.degrees = targetDegrees;

            this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);
            this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);
            this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);
            this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);

            this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }


        super.init();

    }

}
