package org.glo.giftw.domain.strategy;

import org.glo.giftw.domain.util.Vector;

import java.io.Serializable;

public class Player extends GameObject implements Serializable
{
    public static final long serialVersionUID = 2L;

    private int jerseyNumber;
    private String role;
    private Vector speed;
    private Projectile projectile;

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
        this.collidable = true;
        this.speed = new Vector(0, 0);
        this.dimensions = new Vector(300, 300); // TODO: faire des tests pour trouver une bonne valeur
        Player.playerCount++;
    }

    public Player(Player player)
    {
        super(player);
        this.jerseyNumber = player.jerseyNumber;
        this.role = player.role;
        this.speed = player.speed;
        this.projectile = player.projectile;
    }

    public GameObject copy()
    {
        return new Player(this);
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
        if (this.projectile == null)
        {
            return false;
        }
        return true;
    }

    public void takeProjectile(Projectile projectile)
    {
        this.projectile = projectile;
    }

    public void dropProjectile()
    {
        this.projectile.setController(null);
        this.projectile = null;
    }

    public static int getPlayerCount()
    {
        return Player.playerCount;
    }

    @Override
    public String toString()
    {
        return "Joueur(Nom: " +
                this.name +
                ", Numero: " +
                this.jerseyNumber +
                ", Role: " +
                this.role + ", ID: " +
                this.id.toString() + ")";
    }
}
