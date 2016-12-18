package org.glo.giftw.controller;

import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.exceptions.MaxNumberException;
import org.glo.giftw.domain.exceptions.TeamNotFound;
import org.glo.giftw.domain.strategy.*;
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
        
        public int getUndoStackSize()
        {
            return this.undoStack.size();
        }
        
        public int getRedoStackSize()
        {
            return this.redoStack.size();
        }
    }

    private ControllerStub controller;

    @Before
    public void setUp()
    {
        Field field = new Field();
        Projectile proj = new Projectile("Puck", "", new Vector(8, 8));
        Sport sport = new Sport("Hockey", Arrays.asList("Centre", "Ailier", "Defenseur", "Gardien"), field, proj, 6, 2);
        Strategy strategy = new Strategy("test", sport, false, false);
        controller = new ControllerStub(strategy);
    }

    @Test
    public void testAddPlayerDefaultTeam() throws TeamNotFound, MaxNumberException, GameObjectNotFound
    {
        String playerUuid = this.controller.addPlayer(null);
        Player player = (Player) this.controller.getGameObjectByUUID(playerUuid);
        Assert.assertEquals("default", this.controller.getPlayerTeam(player));
    }

    @Test(expected = TeamNotFound.class)
    public void testAddPlayerNewTeamNotFound() throws TeamNotFound, MaxNumberException, GameObjectNotFound
    {
        String playerUuid = this.controller.addPlayer("BlU");
        Player player = (Player) this.controller.getGameObjectByUUID(playerUuid);
        Assert.fail("Une exception TeamNotFound devrait etre lance");
    }

    @Test
    public void testAddPlayerNewTeam() throws TeamNotFound, MaxNumberException, GameObjectNotFound
    {
        this.controller.addTeam("BLU", "0x0000FF");
        String playerUuid = this.controller.addPlayer("BLU");
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
    
    @Test
    public void testPushStrategyOnStack()
    {
        this.controller.setCheckMaxNumberTeam(false);
        Assert.assertEquals(1, this.controller.getUndoStackSize());
    }
    
    @Test
    public void TestPushRemovedOnException()
    {
        this.controller.setCheckMaxNumberTeam(true);
        try
        {
            this.controller.addTeam("BLU", "0x0000FF");
            this.controller.addTeam("RED", "0xFF0000");
            this.controller.addTeam("FUBAR", "0x00FF00");
        }
        catch (MaxNumberException e)
        {
            //no fucks given
        }
        Assert.assertEquals(3, this.controller.getUndoStackSize());
    }
    
    @Test
    public void TestPushClearRedoStack()
    {
        this.controller.setCheckMaxNumberTeam(true);
        this.controller.undo();
        Assert.assertEquals(1, this.controller.getRedoStackSize());
        this.controller.setCheckMaxNumberTeam(true);
        Assert.assertEquals(0, this.controller.getRedoStackSize());
    }
    
    @Test
    public void testUndo()
    {
        this.controller.undo();
        Assert.assertEquals(0, this.controller.getUndoStackSize());
        Assert.assertEquals(0, this.controller.getRedoStackSize());
        try
        {
            this.controller.addTeam("BLU", "0x0000FF");
            this.controller.addTeam("RED", "0xFF0000");
        }
        catch (MaxNumberException e)
        {
            //no fucks given
        }
        Assert.assertEquals(2, this.controller.getUndoStackSize());
        this.controller.undo();
        Assert.assertEquals(1, this.controller.getTeams().size());
        Assert.assertEquals(1, this.controller.getUndoStackSize());
        Assert.assertEquals(1, this.controller.getRedoStackSize());
    }
    
    @Test
    public void testRedo()
    {
        this.controller.redo();
        Assert.assertEquals(0, this.controller.getUndoStackSize());
        Assert.assertEquals(0, this.controller.getRedoStackSize());
        try
        {
            this.controller.addTeam("BLU", "0x0000FF");
            this.controller.addTeam("RED", "0xFF0000");
        }
        catch (MaxNumberException e)
        {
            //no fucks given
        }
        this.controller.undo();
        this.controller.redo();
        Assert.assertEquals(2, this.controller.getTeams().size());
        Assert.assertEquals(2, this.controller.getUndoStackSize());
        Assert.assertEquals(0, this.controller.getRedoStackSize());
    }
}
