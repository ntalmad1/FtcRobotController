package org.firstinspires.ftc.library.utility;

import androidx.annotation.NonNull;

/**
 * A collection of utility methods for (x,y) coordinate manipulations
 * and calculations
 */
public class GridUtils
{
    /**
     * Private constructor so class cannot be instantiated or extended
     */
    private GridUtils () { }

    /**
     *
     * @param x
     * @param y
     * @param degrees
     * @return
     */
    @NonNull
    public static Point rotatePointByDegrees (double x, double y, double degrees)
    {
//        Noah's attempt
//        double x1 = 0;
//        double y1 = 0;
//
//        double x2 = x;
//        double y2 = y;
//
//        double r = Math.sqrt( (x2 - x1) * (x2 -x1) + (y2 - y1) * (y2 - y1) );
//
//        double radians = degrees * (Math.PI / (double)180);
//
//        double x3 = r * Math.cos(radians);
//        double y3 = r * Math.sin(radians);

        double x1 = 0;
        double y1 = 0;

        double x2 = x;
        double y2 = y;

        double radians = degrees * Math.PI / (double)180;

        double x3 = Math.cos(radians) * (x2 - x1) - Math.sin(radians) * (y2 - y1) + x1;
        double y3 = Math.sin(radians) * (x2 - x1) + Math.cos(radians) * (y2 - y1) + y1;

        return new Point(roundDecimal(x3,3), roundDecimal(y3, 3));
        //return rotateLineByDegrees(0,0, x, y, degrees);
    }

    /**
     * s = 2 π r (θ/360°)
     *
     * @param radius
     * @param degrees
     * @return
     */
    public static double arcLength (double radius, double degrees)
    {
        double arcLength = 2 * Math.PI * radius * ( degrees / (double)360 );

        return arcLength;
    }

    /**
     *
     * @param value
     * @param places
     * @return
     */
    public static double roundDecimal(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
