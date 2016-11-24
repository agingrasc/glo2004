package org.glo.giftw.domain;

import java.io.Serializable;

public class Obstacle extends GameObject implements Serializable
{
    public static final long serialVersionUID = 1L;
    private static int obstacleCount = 0;

    public Obstacle()
    {
        this(true);        
    }
    
    public Obstacle(boolean isCollidable)
    {
        super();
        this.collidable = isCollidable;
        Obstacle.obstacleCount++;
    }

    public Obstacle(Obstacle obs)
    {
        super(obs);
        Obstacle.obstacleCount++;
    }
    
    public GameObject copy()
    {
        return new Obstacle(this);
    }

    public static int getObstacleCount()
    {
        return Obstacle.obstacleCount;
    }
}
