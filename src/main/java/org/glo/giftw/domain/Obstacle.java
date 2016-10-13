package org.glo.giftw.domain;

public class Obstacle extends GameObject
{
    //dictates if the obstacle generate collisions with other game object
    private boolean collidable;

    private static int obstacleCount = 0;

    public Obstacle()
    {
        super();
        this.collidable = true;
        Obstacle.obstacleCount++;
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