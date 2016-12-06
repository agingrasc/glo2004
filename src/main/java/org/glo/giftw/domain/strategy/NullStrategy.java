package org.glo.giftw.domain.strategy;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by alexandre on 12/4/16.
 */
public class NullStrategy extends Strategy
{

    @Override
    public Collection<Team> getTeams()
    {
        return new HashSet<Team>();
    }

    @Override
    public Projectile getProjectile()
    {
        return null;
    }
}
