package org.glo.giftw.domain;

import java.util.Collection;
import java.util.List;
import java.util.HashMap;

import org.glo.giftw.domain.Sport;
import org.glo.giftw.domain.Field;

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

    public void addSport(String name, List<String> roles, Vector dimension)
    {
        Field field = new Field(dimension);
        Sport sport = new Sport(name, roles, field);
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
    
    public void setSportInfo(String name, List<String> roles, Vector dimensions)
    {
        Sport sport = this.sports.get(name);
        sport.setRoles(roles);
        sport.setField(new Field(dimensions));
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
