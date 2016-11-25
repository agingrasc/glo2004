package org.glo.giftw.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Conteneur permettant de conserver les types d'obstacles créés
 */
public class ObstaclePool extends ObjectPool
{
    public static final long serialVersionUID = 1L;
    public static final String obstacle_POOL_PATH = "./data/obstacle_pool.ser";

    private HashMap<String, Obstacle> obstacles;    // Associe le nom d'un obstacle avec le chemin vers son image
    
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
    
    public void addObstacleType(String name, boolean isCollidable, String imagePath)
    {
        this.obstacles.put(name, new Obstacle(name, isCollidable, imagePath));
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
    
    public Obstacle create(String name)
    {
        return new Obstacle(this.obstacles.get(name));
    }
    
    public Obstacle getObstacle(String name)
    {
        return this.obstacles.get(name);
    }
    
    public Collection<Obstacle> getAllObstacles()
	{
		return obstacles.values();
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
        String ret = "ObstaclePool contient: " + this.obstacles.size() + "obstacles\n";
        for (Map.Entry<String, Obstacle> entry : this.obstacles.entrySet())
        {
            ret += "    " + entry.getValue() + "\n";
        }
        
        return ret;
    }
}
