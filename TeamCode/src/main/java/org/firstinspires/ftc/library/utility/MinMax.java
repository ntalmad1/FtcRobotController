package org.firstinspires.ftc.library.utility;

/**
 * Simple "POJO" class that represents a point aka coordinate (x,y)
 */
public class MinMax
{
    /**
     * The min
     */
    private double min;

    /**
     * The max
     */
    private double max;

    /**
     * Constructor
     *
     * @param min The min
     * @param max The max
     */
    public MinMax (double min, double max)
    {
        this.setMin(min);
        this.setMax(max);
    }

    /**
     *
     * @return The min
     */
    public double getMin ()
    {
        return this.min;
    }

    /**
     *
     * @return The max
     */
    public double getMax ()
    {
        return this.max;
    }

    /**
     *
     * @param value The min
     */
    public void setMin (double value)
    {
        this.min = value;
    }

    /**
     *
     * @param value The max
     */
    public void setMax (double value)
    {
        this.max = value;
    }
}
