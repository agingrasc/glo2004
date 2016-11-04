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

    public void createSport(String name, List<String> roles, int length, int height)
    {
        this.sportPool.addSport(name, roles, new Vector(length, height));
    }

    public void createStrategy(String name, String sportName)
    {
        Sport strategySport = this.sportPool.getSportByName(sportName);
        this.currentStrategy = new Strategy(name, strategySport);
        this.currentFrameIdx = 0;
    }

    public Integer addPlayer(double x, double y)
    {
        return this.playersPool.addPlayer(new Vector(x, y));
    }

    public Integer addObstacle(double x, double y)
    {
        Obstacle obstacle = new Obstacle(new Vector(x, y));
        Integer id = obstacle.getId();
        this.obstacles.put(id, obstacle);
        return id;
    }

    public Integer addProjectile(double x, double y)
    {
        Projectile projectile = new Projectile(new Vector(x, y));
        Integer id = projectile.getId();
        this.projectiles.put(id, projectile);
        return id;
    }
}
