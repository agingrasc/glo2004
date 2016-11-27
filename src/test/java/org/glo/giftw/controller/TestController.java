package org.glo.giftw.controller;

import org.glo.giftw.domain.*;
import org.glo.giftw.domain.exceptions.MaxNumberException;
import org.glo.giftw.domain.exceptions.TeamNotFound;
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
            super();
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
    public void testConvertToRealCoordinateUnitRatio()
    {
        Vector coord = new Vector(500, 500);
        double ratio = 1.0;
        Vector expected = new Vector(500, 500);
        Assert.assertTrue(expected.equals(controller.convertToRealCoordinate(coord, ratio)));

    }

    @Test
    public void testConvertToRealCoordinateDoubleRatio()
    {
        Vector coord = new Vector(500, 500);
        double ratio = 2.0;
        Vector expected = new Vector(1000, 1000);
        Assert.assertTrue(expected.equals(controller.convertToRealCoordinate(coord, ratio)));
    }

    @Test
    public void testConvertToRealCoordinateHalfRatio()
    {
        Vector coord = new Vector(500, 500);
        double ratio = 0.5;
        Vector expected = new Vector(250, 250);
        Assert.assertTrue(expected.equals(controller.convertToRealCoordinate(coord, ratio)));
    }

    @Test
    public void testAddPlayerDefaultTeam() throws TeamNotFound, MaxNumberException
    {
        Player player = (Player) this.controller.addPlayer(new Vector(), 0f, new Vector(), null);
        Assert.assertEquals("default", this.controller.getPlayerTeam(player));
    }

    @Test(expected = TeamNotFound.class)
    public void testAddPlayerNewTeamNotFound() throws TeamNotFound, MaxNumberException
    {
        Player player = (Player) this.controller.addPlayer(new Vector(), 0f, new Vector(), "BlU");
        Assert.fail("Une exception TeamNotFound devrait etre lance");
    }

    @Test
    public void testAddPlayerNewTeam() throws TeamNotFound, MaxNumberException
    {
        this.controller.addTeam("BLU");
        Player player = (Player) this.controller.addPlayer(new Vector(), 0f, new Vector(), "BLU");
        Assert.assertEquals("BLU", this.controller.getPlayerTeam(player));
    }

    @Test(expected = MaxNumberException.class)
    public void testAddTeamCheckLimit() throws TeamNotFound, MaxNumberException
    {
        this.controller.setCheckMaxNumberTeam(true);
        this.controller.addTeam("BLU");
        this.controller.addTeam("RED");
        this.controller.addTeam("FUBAR");
        Assert.fail();
    }
}
