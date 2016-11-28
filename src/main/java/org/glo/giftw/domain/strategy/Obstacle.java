package org.glo.giftw.domain.strategy;

import org.glo.giftw.domain.Dragable;

import java.io.Serializable;

public class Obstacle extends GameObject implements Serializable, Dragable
{
    public static final long serialVersionUID = 1L;
    private static int obstacleCount = 0;
    private String imagePath;

    public Obstacle()
    {
        this("", true, "");        
    }
    
    public Obstacle(String name, boolean isCollidable, String imagePath)
    {
        super();
        this.name = name;
        this.collidable = isCollidable;
        this.imagePath = imagePath;
        Obstacle.obstacleCount++;
    }

    public Obstacle(Obstacle obs)
    {
        super(obs);
        this.imagePath = obs.imagePath;
        Obstacle.obstacleCount++;
    }
    
    public String getImagePath()
    {
        return imagePath;
    }

    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }

    public GameObject copy()
    {
        return new Obstacle(this);
    }

    public static int getObstacleCount()
    {
        return Obstacle.obstacleCount;
    }
    
    @Override
    public String toString()
    {
        return this.name + " collisions: " + this.collidable + " path: " + this.imagePath;
    }
}
