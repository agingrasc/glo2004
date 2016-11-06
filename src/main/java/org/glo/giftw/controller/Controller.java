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

    public Integer addPlayer(double dimX, double dimY)
    {
        return this.playersPool.addPlayer(new Vector(dimX, dimY));
    }

    public Integer addObstacle(double dimX, double dimY)
    {
        Obstacle obstacle = new Obstacle(new Vector(dimX, dimY));
        Integer id = obstacle.getId();
        this.obstacles.put(id, obstacle);
        return id;
    }

    public Integer addProjectile(double dimX, double dimY)
    {
        Projectile projectile = new Projectile(new Vector(dimX, dimY));
        Integer id = projectile.getId();
        this.projectiles.put(id, projectile);
        return id;
    }
}
