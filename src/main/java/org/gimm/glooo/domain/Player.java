package org.gimm.glooo.domain;

public class Player extends GameObject
{
    private String name;
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
        this.role = role;
        this.jerseyNumber = number;
        this.speed = new Vector(0, 0);
        this.hasProjectile = false;
        Player.playerCount++;
    }

    public String getName()
    {
        return this.name;
    }

    public String getRole()
    {
        return this.role;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getJerseyNumber()
    {
        return this.jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber)
    {
        this.jerseyNumber = jerseyNumber;
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
        return hasProjectile;
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
