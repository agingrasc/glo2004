package org.gimm.glooo;

public class Player extends GameObject
{
	private String name;
	private int jerseyNumber;
	//private role
	private Vector speed;
	private boolean hasProjectile;
	
	private static int playerCount = 0;
	
	public Player()
	{
		this("", playerCount);
	}
	
	public Player(String name, int number)
	{
		super();
		this.name = name;
		jerseyNumber = number;
		speed = new Vector(0, 0);
		hasProjectile = false;
		playerCount++;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getJerseyNumber()
	{
		return jerseyNumber;
	}

	public void setJerseyNumber(int jerseyNumber)
	{
		this.jerseyNumber = jerseyNumber;
	}

	public Vector getSpeed()
	{
		return speed;
	}

	public void setSpeed(Vector speed)
	{
		this.speed = speed;
	}

	public boolean hasProjectile()
	{
		return hasProjectile;
	}
	
	public void takeProjectile()
	{
		hasProjectile = true;
	}
	
	public void dropProjectile()
	{
		hasProjectile = false;
	}
	
	public static int getPlayerCount()
	{
		return playerCount;
	}
}
