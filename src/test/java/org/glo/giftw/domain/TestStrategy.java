package org.glo.giftw.domain;

import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.exceptions.MaxNumberException;
import org.glo.giftw.domain.exceptions.TeamNotFound;
import org.glo.giftw.domain.strategy.*;
import org.glo.giftw.domain.util.Vector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestStrategy
{
    ArrayList<String> roles;
    Field patinoire;
    Player joueur;
    Projectile projectile;
    Sport hockey;
    Strategy strat;

    @Before
    public void setUp()
    {
        roles = new ArrayList<>();
        roles.add("attaquant");
        roles.add("defenseur");
        roles.add("gardien");

        patinoire = new Field();
        projectile = new Projectile("puck", "", new Vector(8, 8));
        hockey = new Sport("hockey", roles, patinoire, projectile, 6, 2);
        strat = new Strategy("test", hockey, true, true);
        try
        {
            String id = strat.addPlayer(null);
            joueur = (Player) strat.getGameObjectByUUID(id);
            strat.placeGameObject(id, new Vector(400, 1295), 0);
        }
        catch (TeamNotFound e)
        {
            e.printStackTrace();
        }
        catch (MaxNumberException e)
        {
            e.printStackTrace();
        } 
        catch (GameObjectNotFound e)
        {
            e.printStackTrace();
        }
        joueur.setName("Carey Price");
        joueur.setJerseyNumber(31);
        joueur.setRole("gardien");
    }

    @Test
    public void testCreateFrame()
    {
        int initialFrameCount = strat.getFrames().size();
        strat.createNewFrame();
        int newFrameCount = strat.getFrames().size();
        Assert.assertTrue(newFrameCount == initialFrameCount + 15);
    }

    @Test
    public void testPreviousKeyFrame()
    {
        for(int i = 0; i < 3; i++)
        {
            strat.createNewFrame();
        }
        strat.previousKeyFrame();
        Assert.assertEquals(0, strat.getCurrentFrameIdx());
        
        strat.setCurrentFrameIdx(12);
        strat.previousKeyFrame();
        Assert.assertEquals(0, strat.getCurrentFrameIdx());
        
        strat.setCurrentFrameIdx(45);
        strat.previousKeyFrame();
        Assert.assertEquals(30, strat.getCurrentFrameIdx());
        
        strat.previousKeyFrame();
        Assert.assertEquals(15, strat.getCurrentFrameIdx());
        
        strat.previousKeyFrame();
        Assert.assertEquals(0, strat.getCurrentFrameIdx());
        
        strat.setCurrentFrameIdx(29);
        strat.previousKeyFrame();
        Assert.assertEquals(15, strat.getCurrentFrameIdx());
        
        strat.setCurrentFrameIdx(36);
        strat.previousKeyFrame();
        Assert.assertEquals(30, strat.getCurrentFrameIdx());
        
        
    }
    
    @Test
    public void testNextKeyFrame()
    {
        for(int i = 0; i < 3; i++)
        {
            strat.createNewFrame();
        }
        strat.nextKeyFrame();
        Assert.assertEquals(15, strat.getCurrentFrameIdx());
        
        strat.nextKeyFrame();
        Assert.assertEquals(30, strat.getCurrentFrameIdx());
        
        strat.nextKeyFrame();
        Assert.assertEquals(45, strat.getCurrentFrameIdx());
        
        strat.nextKeyFrame();
        Assert.assertEquals(45, strat.getCurrentFrameIdx());
        
        strat.setCurrentFrameIdx(8);
        strat.nextKeyFrame();
        Assert.assertEquals(15, strat.getCurrentFrameIdx());
        
        strat.setCurrentFrameIdx(29);
        strat.nextKeyFrame();
        Assert.assertEquals(30, strat.getCurrentFrameIdx());
        
        strat.setCurrentFrameIdx(31);
        strat.nextKeyFrame();
        Assert.assertEquals(45, strat.getCurrentFrameIdx());
    }

    @Test
    public void testPlaceGameObject() throws GameObjectNotFound
    {
        strat.createNewFrame();
        strat.goToEnd();
        strat.placeGameObject(joueur.getId(), new Vector(550, 1445), 15);
        for (int i = 1; i < 16; i++)
        {
            Frame frame = strat.getFrame(i);
            Assert.assertTrue(frame.getPosition(joueur).equals(new Vector(400 + 10 * i, 1295 + 10 * i)));
            Assert.assertEquals(i, frame.getOrientation(joueur), 0.1);
        }
    }

    @Test(expected = GameObjectNotFound.class)
    public void testClearUnplacedGameObjects() throws TeamNotFound, MaxNumberException, GameObjectNotFound
    {
        String placedPlayerId = strat.addPlayer(null);
        strat.placeGameObject(placedPlayerId, new Vector(), 0);
        String unPlacedPlayerId = strat.addPlayer(null);

        //les trois appels devraient fonctionner
        strat.getGameObjectByUUID(joueur.getId());
        strat.getGameObjectByUUID(placedPlayerId);
        strat.getGameObjectByUUID(unPlacedPlayerId);
        
        strat.clearUnplacedGameObjects();
        
        strat.getGameObjectByUUID(joueur.getId());
        strat.getGameObjectByUUID(placedPlayerId);
        strat.getGameObjectByUUID(unPlacedPlayerId);
        Assert.fail("Une exception GameObjectNotFound devrait être lancée.");
    }
    
    @Test
    public void testAddProjectile() throws GameObjectNotFound
    {
        String gloriousPuckId = this.strat.addProjectile();
        Projectile gloriousPuck = (Projectile)this.strat.getGameObjectByUUID(gloriousPuckId);
        String expected = "puck";
        String actual = gloriousPuck.getName();
        Assert.assertTrue(expected.endsWith(actual));
    }
}
