package org.gimm.glooo.domain;

public class Projectile extends GameObject
{
	private Player controller;
	private Vector speed;
	
	private static int projectileCount = 0;
	
	public Projectile()
	{
		super();
		controller = null;
		speed = new Vector(0, 0);
		projectileCount++;
	}

	public boolean isControlled()
	{
		return controller != null;
	}
	
	public Player getController()
	{
		return controller;
	}
	
	public void setController(Player controller)
	{
		this.controller = controller;
	}
	
	public Vector getSpeed()
	{
		return speed;
	}

	public void setSpeed(Vector speed)
	{
		this.speed = speed;
	}
	
	public static int getProjectileCount()
	{
		return projectileCount;
	}
}
