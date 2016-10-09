package org.gimm.glooo.domain;

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
		return (float)Math.sqrt(x*x + y*y);
	}
	
	public Vector normalized()
	{
		/*
		 * Returns a new unit vector parallel to the current vector
		 */
		float len = magnitude();
		return new Vector(x/len, y/len);
	}
	
	
	public static Vector up()
	{
		/*
		 * Generate the up direction unit vector
		 */
		return new Vector(0, 1);
	}
	
	public static Vector down()
	{
		/*
		 * Generate the down direction unit vector
		 */
		return new Vector(0, -1);
	}
	
	public static Vector left()
	{
		/*
		 * Generate the left direction unit vector
		 */
		return new Vector(-1, 0);
	}
	
	public static Vector right()
	{
		/*
		 * Generate the right direction unit vector
		 */
		return new Vector(1, 0);
	}
}
