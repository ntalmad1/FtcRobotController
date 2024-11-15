package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

public class AutoBot extends CompBot {

    /**
     */
    private TrajectoryFactory trajectoryFactory;

    /**
     * Constructor
     *
     */
    public AutoBot() {
        super();

        this.trajectoryFactory = new TrajectoryFactory(this);

        //this.setInitialPose(new Pose2d(35.5, -61, Math.toRadians(90)));
        //this.setInitialPose(new Pose2d(31.35, -61, Math.toRadians(90)));
    }

    /**
     *
     */
    @Override
    protected void configureBot() {
        super.configureBot();
    }

    /**
     *
     * @return
     */
    protected TrajectoryFactory getTrajectoryFactory() {
        return this.trajectoryFactory;
    }
}
