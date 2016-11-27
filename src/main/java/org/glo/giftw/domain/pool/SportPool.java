package org.glo.giftw.domain.pool;

import java.util.Collection;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.glo.giftw.domain.strategy.Field;
import org.glo.giftw.domain.strategy.Sport;
import org.glo.giftw.domain.util.Vector;

/**
 * Conteneur permettant de conserver les types de sports créés
 */
public class SportPool extends ObjectPool
{
    public static final long serialVersionUID = 1L;
    public static final String SPORT_POOL_PATH = "./data/sport_pool.ser";

    private HashMap<String, Sport> sports;

    public SportPool()
    {
        this(true);
    }
    
    public SportPool(boolean persistent)
    {
        super(persistent);
        sports = new HashMap<>();
        if(persistent)
        {
            this.loadObjectPool(SPORT_POOL_PATH);
        }
    }

    public void addSport(String name, List<String> roles, Vector dimension, String fieldImagePath,
                         String projectileName, String projectileImagePath, int maxPLayersPerTeam, int maxTeams)
    {
        Field field = new Field(dimension, fieldImagePath);
        Sport sport = new Sport(name, roles, field, projectileName, projectileImagePath, maxPLayersPerTeam, maxTeams);
        this.sports.put(sport.getName(), sport);
        if(this.persistent)
        {
            this.saveObjectPool(SPORT_POOL_PATH);
        }
    }
    
    public void deleteSport(String name)
    {
        this.sports.remove(name);
        if(this.persistent)
        {
            this.saveObjectPool(SPORT_POOL_PATH);
        }
    }

    public Sport getSportByName(String sportName)
    {
        return sports.get(sportName);
    }
    
    public Collection<Sport> getAllSports()
    {
        return this.sports.values();
    }
    
    public HashMap<String, String> getSportsDescription()
    {
        HashMap<String, String> ret = new HashMap<>();
        for(Map.Entry<String, Sport> entry : this.sports.entrySet())
        {
            ret.put(entry.getKey(), entry.getValue().getFieldImagePath());
        }
        return ret;
    }

    public String getFieldImagePath(String sportName)
    {
        return this.getSportByName(sportName).getFieldImagePath();
    }

    public void setSportInfo(String name, List<String> roles, Vector dimensions, String fieldImagePath, 
            String projectileName, String projectileImagePath, int maxPLayersPerTeam, int maxTeams)
    {
        Sport sport = this.sports.get(name);
        sport.setRoles(roles);
        sport.setField(new Field(dimensions, fieldImagePath));
        sport.setProjectileName(projectileName);
        sport.setProjectileImagePath(projectileImagePath);
        sport.setMaxPLayersPerTeam(maxPLayersPerTeam);
        sport.setMaxTeams(maxTeams);
        if(this.persistent)
        {
            this.saveObjectPool(SPORT_POOL_PATH);
        }
    }
    
    public void copy(ObjectPool op)
    {
        SportPool tmp = (SportPool)op;
        this.sports = tmp.sports;
    }
    
    @Override
    public String toString()
    {
        String ret = "SportPool contient: " + this.sports.size() + " sports\n";

        for (Sport s: this.sports.values())
        {
            ret += s.toString() + "\n";
        }

        return ret;
    }
}
