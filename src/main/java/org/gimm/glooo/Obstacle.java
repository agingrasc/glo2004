package org.gimm.glooo;

public class Obstacle extends GameObject
{
	//dictates if the obstacle generate collisions with other game object
	private boolean collider;
	
	private static int obstacleCount = 0;
	
	public Obstacle()
	{
		super();
		collider = true;
		obstacleCount++;
	}

	public boolean isCollider()
	{
		return collider;
	}

	public void setCollider(boolean isCollider)
	{
		this.collider = isCollider;
	}

	public static int getObstacleCount()
	{
		return obstacleCount;
	}
}
