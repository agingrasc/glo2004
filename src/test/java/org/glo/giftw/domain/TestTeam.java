package org.glo.giftw.domain;

import org.glo.giftw.domain.exceptions.MaxNumberException;
import org.glo.giftw.domain.strategy.Player;
import org.glo.giftw.domain.strategy.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTeam
{
    private Team team;
    private Player player;

    @Before
    public void setUp()
    {
        this.team = new Team(6, false);
        this.player = new Player("Foo", "Centre", 13);
    }

    @Test
    public void testAddPlayerNoCheck() throws MaxNumberException
    {
        Assert.assertFalse(this.team.isPlayerInTeam(player));
        this.team.addPlayer(this.player);
        Assert.assertTrue(this.team.isPlayerInTeam(this.player));
    }

    @Test(expected = MaxNumberException.class)
    public void testAddPlayerCheck() throws MaxNumberException
    {
        this.team = new Team(1, true);
        this.team.addPlayer(this.player);
        Player nPlayer = new Player("Fubar", "Fubar", 42);
        this.team.addPlayer(nPlayer);
        Assert.fail();
    }

    @Test
    public void testRemovePlayer() throws MaxNumberException
    {
        this.team.addPlayer(this.player);
        Assert.assertTrue(this.team.isPlayerInTeam(this.player));
        this.team.removePlayer(this.player);
        Assert.assertFalse(this.team.isPlayerInTeam(this.player));
    }
}
