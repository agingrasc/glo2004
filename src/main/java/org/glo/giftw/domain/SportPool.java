package org.glo.giftw.domain;

import java.util.List;
import java.util.HashMap;

import org.glo.giftw.domain.Sport;
import org.glo.giftw.domain.Field;

public class SportPool
{
    public static final String SPORT_POOL_PATH = "./data/sport_pool.ser";

    private HashMap<String, Sport> sports;

    public SportPool()
    {
        //load logic
    }

    public void addSport(String name, List<String> roles, Vector dimension)
    {
        Field field = new Field(dimension);
        Sport sport = new Sport(name, roles, field);
        this.sports.put(sport.getName(), sport);
    }

    public Sport getSportByName(String sportName)
    {
        return sports.get(sportName);
    }
}
