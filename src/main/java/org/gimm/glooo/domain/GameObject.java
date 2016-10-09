package org.gimm.glooo.domain;

public abstract class GameObject
{
	private float orientation;
	private Vector position;
	private Vector scale;
	
	public GameObject()
	{
		orientation = 0;
		position = new Vector(0, 0);
		scale = new Vector(1, 1);
	}
	

	public float getOrientation()
	{
		return orientation;
	}

	public void setOrientation(float orientation)
	{
		this.orientation = orientation;
	}

	public Vector getPosition()
	{
		return position;
	}

	public void setPosition(Vector position)
	{
		this.position = position;
	}

	public Vector getScale()
	{
		return scale;
	}

	public void setScale(Vector scale)
	{
		this.scale = scale;
	}

	
	public void Rotate(float angle)
	{
		/*
		 * Applies a rotation to the GameObject
		 */
		orientation += angle;
	}
	
	public void Move(Vector delta)
	{
		/*
		 * Applies a translation to the GameObject
		 */
		position.setX(position.getX() + delta.getX());
		position.setY(position.getY() + delta.getY());
	}
}
