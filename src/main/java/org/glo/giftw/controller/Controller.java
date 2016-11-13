package org.glo.giftw.controller;

import java.util.List;
import java.util.HashMap;

import org.glo.giftw.domain.*;

public class Controller
{
    private PlayerPool playersPool;
    private SportPool sportPool;
    private HashMap<Integer, Obstacle> obstacles;
    private HashMap<Integer, Projectile> projectiles;

    private Strategy currentStrategy;
    private int currentFrameIdx;

    public Controller()
    {
        super();
        this.playersPool = new PlayerPool();
        this.sportPool = new SportPool();
        this.currentStrategy = null;
        this.currentFrameIdx = 0;
    }

    public void createSport(String name, List<String> roles, int fieldLength, int fieldHeight)
    {
        this.sportPool.addSport(name, roles, new Vector(fieldLength, fieldHeight));
    }

    public void createStrategy(String name, String sportName)
    {
        Sport strategySport = this.sportPool.getSportByName(sportName);
        this.currentStrategy = new Strategy(name, strategySport);
        this.currentFrameIdx = 0;
    }

    public Integer addPlayer(Vector position, float orientation, Vector dimensions)
    {
        Integer id = this.playersPool.addPlayer(position, orientation, dimensions);
        Frame currentFrame = this.currentStrategy.getFrame(this.currentFrameIdx);
        currentFrame.addGameObject(this.playersPool.getPlayer(id));
        return id;
    }

    public Integer addObstacle(Vector position, float orientation, Vector dimensions)
    {
        Obstacle obstacle = new Obstacle(position, orientation, dimensions);
        Integer id = obstacle.getId();
        this.obstacles.put(id, obstacle);
        return id;
    }

    public Integer addProjectile(Vector position, float orientation, Vector dimensions)
    {
        Projectile projectile = new Projectile(position, orientation, dimensions);
        Integer id = projectile.getId();
        this.projectiles.put(id, projectile);
        return id;
    }
}
