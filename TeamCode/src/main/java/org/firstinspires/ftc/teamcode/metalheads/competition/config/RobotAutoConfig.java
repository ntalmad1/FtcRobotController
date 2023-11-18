package org.firstinspires.ftc.teamcode.metalheads.competition.config;

import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompAutoBot;

public class RobotAutoConfig {

    public Direction startingTrussDirection;

    public CompAutoBot.Routine routine;

    public int distanceUnderTruss_truss = 90;
    public int distanceUnderTruss_middle = 120;
    public int distanceUnderTruss_oppositeTruss = 120;

    public double placeYellowPixelDistance_near = 68;
    public double placeYellowPixelDistance_middle = 88;
    public double placeYellowPixelDistance_far = 105;

    public double rotateTrussSide = 44;

    public double placePurplePixel_forwards_sideDistance = 16;
    public double placePurplePixel_oppositeTruss_sideDistance = 37;

    public double backboardPlaceTarget = 6.5;

    public boolean autoCorrectAtEnd = true;
}
