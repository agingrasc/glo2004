package org.glo.giftw.domain;

public class Projectile extends GameObject
{
    private Player controller;
    private Vector speed;

    private static int projectileCount = 0;

    public Projectile()
    {
        super();
        this.controller = null;
        this.speed = new Vector(0, 0);
        Projectile.projectileCount++;
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
