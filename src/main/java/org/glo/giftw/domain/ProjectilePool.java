package org.glo.giftw.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Conteneur permettant de conserver les associations entre les types de projectiles et les images correspondantes
 */
public class ProjectilePool extends ObjectPool
{
    public static final long serialVersionUID = 1L;
    public static final String PROJECTILE_POOL_PATH = "./data/projectile_pool.ser";

    private HashMap<String, String> projectiles;    // Associe le nom d'un projectile avec le chemin vers son image
    
    public ProjectilePool()
    {
        this(true);
    }
    
    public ProjectilePool(boolean persistent)
    {
        super(persistent);
        projectiles = new HashMap<>();
        if(persistent)
        {
            this.loadObjectPool(PROJECTILE_POOL_PATH);
        }
    }
    
    public void addProjectile(String name, String path)
    {
        this.projectiles.put(name, path);
        if(this.persistent)
        {
            this.saveObjectPool(PROJECTILE_POOL_PATH);
        }
    }
    
    public void deleteProjectile(String name)
    {
        this.projectiles.remove(name);
        if(this.persistent)
        {
            this.saveObjectPool(PROJECTILE_POOL_PATH);
        }
    }
    
    public String getProjectileImagePath(String projectileName)
    {
        return projectiles.get(projectileName);
    }
    
    public void setProjectileImagePath(String name, String path)
    {
        this.projectiles.put(name, path);
        if(this.persistent)
        {
            this.saveObjectPool(PROJECTILE_POOL_PATH);
        }
    }
    
    public Collection<String> getAllPaths()
    {
        return this.projectiles.values();
    }
    
    @Override
    protected void copy(ObjectPool op)
    {
        ProjectilePool tmp = (ProjectilePool)op;
        this.projectiles = tmp.projectiles;
    }

    @Override
    public String toString()
    {
        String ret = "ProjectilePool contient: " + this.projectiles.size() + " projectiles\n";

        for (Map.Entry<String, String> entry : this.projectiles.entrySet())
        {
            ret += entry.getKey() + ": " + entry.getValue() + "\n";
        }

        return ret;
    }
}
