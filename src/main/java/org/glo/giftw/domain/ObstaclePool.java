package org.glo.giftw.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Conteneur permettant de conserver les associations entre les types d'obstacle et les images correspondantes
 */
public class ObstaclePool extends ObjectPool
{
    public static final long serialVersionUID = 1L;
    public static final String obstacle_POOL_PATH = "./data/obstacle_pool.ser";

    private HashMap<String, String> obstacles;    // Associe le nom d'un obstacle avec le chemin vers son image
    
    public ObstaclePool()
    {
        this(true);
    }
    
    public ObstaclePool(boolean persistent)
    {
        super(persistent);
        obstacles = new HashMap<>();
        if(persistent)
        {
            this.loadObjectPool(obstacle_POOL_PATH);
        }
    }
    
    public void addObstacle(String name, String imagePath)
    {
        this.obstacles.put(name, imagePath);
        if(this.persistent)
        {
            this.saveObjectPool(obstacle_POOL_PATH);
        }
    }
    
    public void deleteObstacle(String name)
    {
        this.obstacles.remove(name);
        if(this.persistent)
        {
            this.saveObjectPool(obstacle_POOL_PATH);
        }
    }
    
    public String getObstacleImagePath(String obstacleName)
    {
        return this.obstacles.get(obstacleName);
    }
    
    public void setObstacleImagePath(String name, String imagePath)
    {
        this.obstacles.put(name, imagePath);
        if(this.persistent)
        {
            this.saveObjectPool(obstacle_POOL_PATH);
        }
    }
    
    public Collection<String> getAllObstaclePaths()
    {
        return this.obstacles.values();
    }
    
    @Override
    protected void copy(ObjectPool op)
    {
        ObstaclePool tmp = (ObstaclePool)op;
        this.obstacles = tmp.obstacles;
    }

    @Override
    public String toString()
    {
        String ret = "GameObjectPool contient: " + this.obstacles.size() + "obstacles\n";
        for (Map.Entry<String, String> entry : this.obstacles.entrySet())
        {
            ret += entry.getKey() + ": " + entry.getValue() + "\n";
        }
        
        return ret;
    }
}
