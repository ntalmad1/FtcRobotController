package org.firstinspires.ftc.teamcode.library.utility;

/**
 * Simple "POJO" class that represents a point aka coordinate (x,y)
 */
public class Point
{
    /**
     * The x coord
     */
    private double x;

    /**
     * The y coord
     */
    private double y;

    /**
     * Constructor
     *
     * @param x The x coord
     * @param y The y coord
     */
    public Point (double x, double y)
    {
        this.setX(x);
        this.setY(y);
    }

    /**
     *
     * @return The x coord
     */
    public double getX ()
    {
        return this.x;
    }

    /**
     *
     * @return The y coord
     */
    public double getY ()
    {
        return this.y;
    }

    /**
     *
     * @param value The x coord
     */
    public void setX (double value)
    {
        this.x = value;
    }

    /**
     *
     * @param value The y coord
     */
    public void setY (double value)
    {
        this.y = value;
    }
}
