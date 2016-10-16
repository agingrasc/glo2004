package org.glo.giftw.domain;

import java.io.Serializable;

public class Obstacle extends GameObject implements Serializable
{
    public static final long serialVersionUID = 1L;
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
    }
    
    public GameObject copy()
    {
        return new Obstacle((Obstacle)this);
    }

    public static int getObstacleCount()
    {
        return Obstacle.obstacleCount;
    }
}
