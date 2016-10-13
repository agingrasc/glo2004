package org.glo.giftw.domain;

import java.lang.Math;

public class Vector
{
    /*
     * Representation of 2D vectors and points.
     */
    private float x;
    private float y;

    public Vector(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector()
    {
        this(0, 0);
    }


    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }


    public float magnitude()
    {
        /*
         * Returns the length of this vector
         */
        return (float)Math.sqrt(this.x*this.x + this.y*this.y);
    }
    
    public float direction()
    {
    	/*
    	 * Returns the direction of this vector in the range -pi to pi.
    	 */
    	return (float)Math.atan2(this.y, this.x);
    }

    public Vector normalized()
    {
        /*
         * Returns a new unit vector parallel to the current vector
         */
        float len = magnitude();
        return new Vector(x/len, y/len);
    }
}
