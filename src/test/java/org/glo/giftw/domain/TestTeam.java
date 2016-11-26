package org.glo.giftw.domain;

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
    public void testAddPlayerNoCheck()
    {
        Assert.assertFalse(this.team.isPlayerInTeam(player));
        this.team.addPlayer(this.player);
        Assert.assertTrue(this.team.isPlayerInTeam(this.player));
    }

    @Test
    public void testAddPlayerCheck()
    {
        this.team = new Team(1, true);
        this.team.addPlayer(this.player);
        Player nPlayer = new Player("Fubar", "Fubar", 42);
        this.team.addPlayer(nPlayer);
        Assert.assertFalse(this.team.isPlayerInTeam(nPlayer));
    }

    @Test
    public void testRemovePlayer()
    {
        this.team.addPlayer(this.player);
        Assert.assertTrue(this.team.isPlayerInTeam(this.player));
        this.team.removePlayer(this.player);
        Assert.assertFalse(this.team.isPlayerInTeam(this.player));
    }
}
