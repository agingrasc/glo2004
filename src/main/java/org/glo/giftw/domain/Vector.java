package org.glo.giftw.domain;

import java.lang.Math;
import java.io.Serializable;

/**
 * Representation of 2D vectors and points.
 */
public class Vector implements Serializable
{
    public static final long serialVersionUID = 1L;

    private double x;
    private double y;

    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector()
    {
        this(0, 0);
    }


    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    /**
     * @param x valeur en x
     * @param y valeur en y
     */
    public void set(double x, double y)
    {
        this.x = x;
        this.y = y;
    }


    /**
     * Returns the length of this vector
     */
    public double magnitude()
    {
        return Math.sqrt(this.x*this.x + this.y*this.y);
    }

    /**
     * Returns the direction of this vector in the range -pi to pi.
     * @return angle de la direction
     */
    public double direction()
    {
        return Math.atan2(this.y, this.x);
    }

    /**
     * Returns a new unit vector parallel to the current vector
     */
    public Vector normalized()
    {
        double len = magnitude();
        return new Vector(x/len, y/len);
    }

    public boolean equals(Vector rhd)
    {
        return this.x == rhd.x && this.y == rhd.y;
    }

    @Override
    public String toString()
    {
        return "(x: " + this.x + ", y: " + this.y + ")";
    }
    
    public double getDistance(Vector other)
    {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
}
