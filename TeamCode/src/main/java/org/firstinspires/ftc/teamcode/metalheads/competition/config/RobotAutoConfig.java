package org.firstinspires.ftc.teamcode.metalheads.competition.config;

import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompAutoBot;

/**
 *
 */
public class RobotAutoConfig {

    /**
     */
    public enum ParkPosition {

        /**
         */
        MIDDLE,

        /**
         */
        CORNER,
    }

    /**
     *
     */
    public enum StartPosition {
        NEAR,
        FAR
    }

    public Direction startingTrussDirection;

    public Direction backdropDirection;

    public StartPosition startPosition;

    public ParkPosition parkPosition;

    public boolean useBackdrop;

    public double backboardPlaceTarget = 6.5;

    public String webCamName = "Webcam 1";
}
