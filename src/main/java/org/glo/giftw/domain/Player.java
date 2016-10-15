package org.glo.giftw.domain;

public class Player extends GameObject
{
    private int jerseyNumber;
    private String role;
    private Vector speed;
    private boolean hasProjectile;

    //FIXME: move logic in object containing players
    private static int playerCount = 0;

    public Player()
    {
        this("", "", playerCount);
    }

    public Player(String name, String role, int number)
    {
        super();
        this.name = name;
        this.jerseyNumber = number;
        this.role = role;
        this.speed = new Vector(0, 0);
        this.hasProjectile = false;
        Player.playerCount++;
    }
    
    public Player(Vector scale)
    {
    	this("", "", playerCount);
    	this.setScale(scale);
    }

    public Player(Player player)
    {
        super(player);
        this.jerseyNumber = player.jerseyNumber;
        this.role = player.role;
        this.speed = player.speed;
        this.hasProjectile = player.hasProjectile;
    }
    
    public int getJerseyNumber()
    {
        return this.jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber)
    {
        this.jerseyNumber = jerseyNumber;
    }

    public String getRole()
    {
        return this.role;
    }

    public void setRole(String role)
    {
    	this.role = role;
    }

    public Vector getSpeed()
    {
        return this.speed;
    }

    public void setSpeed(Vector speed)
    {
        this.speed = speed;
    }
    
    public boolean hasProjectile()
    {
    	return this.hasProjectile;
    }
    
    public void takeProjectile()
    {
    	this.hasProjectile = true;
    }
    
    public void dropProjectile()
    {
    	this.hasProjectile = false;
    }

    public static int getPlayerCount()
    {
        return Player.playerCount;
    }
}
