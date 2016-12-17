package org.glo.giftw.domain;

import org.glo.giftw.domain.strategy.Obstacle;
import org.glo.giftw.domain.strategy.Player;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class TestGameObject
{

    @Test
    public void testUniqueId()
    {
        Player player = new Player();
        Obstacle obstacle = new Obstacle();
        Assert.assertFalse(player.getId().equals(obstacle.getId()));
    }
}
