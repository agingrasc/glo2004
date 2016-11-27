package org.glo.giftw.domain.strategy;

import org.glo.giftw.domain.util.Vector;

import java.io.Serializable;

public class Projectile extends GameObject implements Serializable
{
    public static final long serialVersionUID = 1L;

    private Player controller;
    private Vector speed;

    private static int projectileCount = 0;

    public Projectile()
    {
        super();
        this.controller = null;
        this.speed = new Vector(0, 0);
        this.collidable = false;
        Projectile.projectileCount++;
    }

    public Projectile(Projectile proj)
    {
        super(proj);
        this.controller = proj.controller;
        this.speed = proj.speed;
    }
    
    public GameObject copy()
    {
        return new Projectile(this);
    }

    public boolean isControlled()
    {
        return this.controller != null;
    }

    public Player getController()
    {
        return this.controller;
    }

    public void setController(Player controller)
    {
        this.controller = controller;
    }

    public Vector getSpeed()
    {
        return this.speed;
    }

    public void setSpeed(Vector speed)
    {
        this.speed = speed;
    }

    public static int getProjectileCount()
    {
        return Projectile.projectileCount;
    }
}
