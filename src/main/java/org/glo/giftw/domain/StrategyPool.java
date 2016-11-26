package org.glo.giftw.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Conteneur permettant de conserver les stratégies créées
 */
public class StrategyPool extends ObjectPool
{
    public static final long serialVersionUID = 1L;
    public static final String STRATEGY_POOL_PATH = "./data/strategy_pool.ser";
    
    private HashMap<String, Strategy> strategies;
    
    public StrategyPool()
    {
        this(true);
    }
    
    public StrategyPool(boolean persistent)
    {
        super(persistent);
        strategies = new HashMap<>();
        if(persistent)
        {
            this.loadObjectPool(STRATEGY_POOL_PATH);
        }
    }
    
    public Strategy addStrategy(String name, Sport sport)
    {
        Strategy strat = new Strategy(name, sport);
        this.strategies.put(name, strat);
        if(this.persistent)
        {
            this.saveObjectPool(STRATEGY_POOL_PATH);
        }
        return strat;
    }
    
    public void deleteStrategy(String name)
    {
        this.strategies.remove(name);
        if(this.persistent)
        {
            this.saveObjectPool(STRATEGY_POOL_PATH);
        }
    }
    
    public Strategy getStrategy(String name)
    {
        return this.strategies.get(name);
    }
    
    public Collection<Strategy> getAllStrategies()
    {
        return this.strategies.values();
    }
    
    /**
     * Méthode publique permettant de sauvegarder le StrategyPool après avoir modifiée une stratégie.
     */
    public void save()
    {
        if(this.persistent)
        {
            this.saveObjectPool(STRATEGY_POOL_PATH);
        }
    }
    
    @Override
    protected void copy(ObjectPool op)
    {
        StrategyPool tmp = (StrategyPool)op;
        this.strategies = tmp.strategies;
    }

    @Override
    public String toString()
    {
        String ret = "StrategyPool contient: " + this.strategies.size() + "strategies\n";
        for (Map.Entry<String, Strategy> entry : this.strategies.entrySet())
        {
            ret += entry.getKey() + ": " + entry.getValue().getSport().getName() + "\n";
        }
        
        return ret;
    }
}
