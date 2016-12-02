package org.glo.giftw.domain.strategy;

import org.glo.giftw.domain.util.Vector;
import org.glo.giftw.domain.util.Viewable;

import java.io.Serializable;

public class Projectile extends GameObject implements Serializable, Viewable
{
    public static final long serialVersionUID = 1L;
    private static int projectileCount = 0;

    private String imagePath;
    private Player controller;
    private Vector speed;

    public Projectile(String name, String imagePath)
    {
        super();
        this.name = name;
        this.imagePath = imagePath;
        this.controller = null;
        this.speed = new Vector(0, 0);
        this.collidable = false;
        Projectile.projectileCount++;
    }

    public Projectile(Projectile proj)
    {
        super(proj);
        this.imagePath = proj.imagePath;
        this.controller = proj.controller;
        this.speed = proj.speed;
        Projectile.projectileCount++;
    }

    public GameObject copy()
    {
        return new Projectile(this);
    }

    public String getImagePath()
    {
        return this.imagePath;
    }

    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
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

    @Override
    public String toString()
    {
        return this.name + " path: " + this.imagePath;
    }
}
