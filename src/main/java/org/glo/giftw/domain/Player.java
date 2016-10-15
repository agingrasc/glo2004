package org.glo.giftw.domain;

public class Player extends GameObject
{
    private String name;
    private int jerseyNumber;
    private String role;

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

    public static int getPlayerCount()
    {
        return Player.playerCount;
    }
}
