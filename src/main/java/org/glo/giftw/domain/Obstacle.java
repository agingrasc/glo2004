package org.glo.giftw.domain;

import java.io.Serializable;

public class Obstacle extends GameObject implements Serializable
{
    public static final long serialVersionUID = 1L;
    //dictates if the obstacle generate collisions with other game object
    private boolean collidable;

    private static int obstacleCount = 0;

    public Obstacle()
    {
        super();
        this.collidable = true;
        Obstacle.obstacleCount++;
    }

    public Obstacle(Vector scale)
    {
        this();
        this.scale = scale;
    }

    public Obstacle(Obstacle obs)
    {
        super(obs);
        this.collidable = obs.collidable;
    }

    public boolean isCollidable()
    {
        return this.collidable;
    }

    public void setCollidable(boolean isCollidable)
    {
        this.collidable = isCollidable;
    }

    public static int getObstacleCount()
    {
        return Obstacle.obstacleCount;
    }
}
