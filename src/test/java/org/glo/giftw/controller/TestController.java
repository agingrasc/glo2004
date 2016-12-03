package org.glo.giftw.controller;

import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.exceptions.MaxNumberException;
import org.glo.giftw.domain.exceptions.TeamNotFound;
import org.glo.giftw.domain.strategy.Field;
import org.glo.giftw.domain.strategy.Player;
import org.glo.giftw.domain.strategy.Sport;
import org.glo.giftw.domain.strategy.Strategy;
import org.glo.giftw.domain.util.Vector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class TestController
{
    private class ControllerStub extends Controller
    {
        public ControllerStub(Strategy strategy)
        {
            this.currentStrategy = strategy;
        }
    }

    private ControllerStub controller;

    @Before
    public void setUp()
    {
        Field field = new Field();
        Sport sport = new Sport("Hockey", Arrays.asList("Centre", "Ailier", "Defenseur", "Gardien"), field, "Puck", "",
                                6, 2);
        Strategy strategy = new Strategy("test", sport, false, false);
        controller = new ControllerStub(strategy);
    }

    @Test
    public void testAddPlayerDefaultTeam() throws TeamNotFound, MaxNumberException, GameObjectNotFound
    {
        String playerUuid = this.controller.addPlayer(new Vector(), 0f, new Vector(), null);
        Player player = (Player) this.controller.getGameObjectByUUID(playerUuid);
        Assert.assertEquals("default", this.controller.getPlayerTeam(player));
    }

    @Test(expected = TeamNotFound.class)
    public void testAddPlayerNewTeamNotFound() throws TeamNotFound, MaxNumberException, GameObjectNotFound
    {
        String playerUuid = this.controller.addPlayer(new Vector(), 0f, new Vector(), "BlU");
        Player player = (Player) this.controller.getGameObjectByUUID(playerUuid);
        Assert.fail("Une exception TeamNotFound devrait etre lance");
    }

    @Test
    public void testAddPlayerNewTeam() throws TeamNotFound, MaxNumberException, GameObjectNotFound
    {
        this.controller.addTeam("BLU", "0x0000FF");
        String playerUuid = this.controller.addPlayer(new Vector(), 0f, new Vector(), "BLU");
        Player player = (Player) this.controller.getGameObjectByUUID(playerUuid);
        Assert.assertEquals("BLU", this.controller.getPlayerTeam(player));
    }

    @Test(expected = MaxNumberException.class)
    public void testAddTeamCheckLimit() throws TeamNotFound, MaxNumberException
    {
        this.controller.setCheckMaxNumberTeam(true);
        this.controller.addTeam("BLU", "0x0000FF");
        this.controller.addTeam("RED", "0xFF0000");
        this.controller.addTeam("FUBAR", "0x00FF00");
        Assert.fail();
    }
}
