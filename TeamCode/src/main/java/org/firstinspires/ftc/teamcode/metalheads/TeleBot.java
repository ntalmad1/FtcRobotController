package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.metalheads.compbot.CompBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.DriveTrainPositionStruct;

@TeleOp(name = "TeleBot", group = "Comp")
public class TeleBot extends CompBot {

    /**
     * Constructor
     *
     */
    public TeleBot() {
        super();

        this.setConfig(new TeleBotConfig(this));
        this.configureBot();
    }

    @Override
    public void initBot() {
        super.initBot();

        this.bigArm.mainBoom.moveToPosition(1, 525);
        this.setArmPos(ArmPos.INIT);
    }

    /**
     *
     */
    @Override
    protected void configureBot() {
        super.configureBot();

        // initialize roadrunner from last op pose
        this.initialPose = DriveTrainPositionStruct.pose == null ? new Pose2d(0, 0, 0) : DriveTrainPositionStruct.pose;
    }
}
