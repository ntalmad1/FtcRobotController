package org.firstinspires.ftc.teamcode.metalheads.compbot;

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

    /**
     *
     * @param trajectoryFactory
     */
    protected void setTrajectoryFactory(TrajectoryFactory trajectoryFactory) {
        this.trajectoryFactory = trajectoryFactory;
    }
}
