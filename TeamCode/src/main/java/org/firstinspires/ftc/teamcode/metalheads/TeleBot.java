package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.metalheads.compbot.CompBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.PositionStruct;

@TeleOp(name = "TeleBot", group = "Tests")
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

    /**
     *
     */
    @Override
    protected void configureBot() {
        super.configureBot();

        // initialize roadrunner from last op pose
        this.initialPose = PositionStruct.pose == null ? new Pose2d(0, 0, 0) : PositionStruct.pose;
    }
}
