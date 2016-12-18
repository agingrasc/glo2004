package org.glo.giftw.domain.pool;

import org.glo.giftw.domain.exceptions.StrategyNotFound;
import org.glo.giftw.domain.strategy.Sport;
import org.glo.giftw.domain.strategy.Strategy;

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
        if (persistent)
        {
            this.loadObjectPool(STRATEGY_POOL_PATH);
        }
    }

    public Strategy addStrategy(String name, Sport sport, boolean activateMaxNumberPlayer,
                                boolean activateMaxNumberTeam)
    {
        Strategy strat = new Strategy(name, sport, activateMaxNumberPlayer, activateMaxNumberTeam);
        this.saveStrategy(name,  strat);
        return strat;
    }

    public void deleteStrategy(String name)
    {
        this.strategies.remove(name);
        if (this.persistent)
        {
            this.saveObjectPool(STRATEGY_POOL_PATH);
        }
    }

    public Strategy getStrategy(String name) throws StrategyNotFound
    {
        try
        {
            return this.strategies.get(name);
        }
        catch (NullPointerException e)
        {
            throw new StrategyNotFound(String.format("La strategie %s n'existe pas.", name), e);
        }
    }

    public Collection<Strategy> getAllStrategies()
    {
        return this.strategies.values();
    }

    /**
     * Méthode publique permettant de sauvegarder le StrategyPool après avoir modifiée une stratégie.
     */
    public void saveStrategy(String name, Strategy strategy)
    {
        this.strategies.put(name, strategy);
        if (this.persistent)
        {
            this.saveObjectPool(STRATEGY_POOL_PATH);
        }
    }

    @Override
    protected void copy(ObjectPool op)
    {
        StrategyPool tmp = (StrategyPool) op;
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
