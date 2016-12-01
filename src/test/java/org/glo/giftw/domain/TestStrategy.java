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
        hockey = new Sport("hockey", roles, patinoire, "puck", "", 6, 2);
        strat = new Strategy("test", hockey, true, true);
        try
        {
            joueur = (Player) strat.addPlayer(new Vector(400, 1295), 0, new Vector(50, 100), null);
        }
        catch (TeamNotFound e)
        {
            e.printStackTrace();
        }
        catch (MaxNumberException e)
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
    public void testPlaceGameObject() throws GameObjectNotFound
    {
        strat.createNewFrame();
        strat.goToEnd();
        strat.placeGameObject(joueur.getId(), new Vector(550, 1445), 15, new Vector(80, 130));
        for (int i = 1; i < 16; i++)
        {
            Frame frame = strat.getFrame(i);
            Assert.assertTrue(frame.getPosition(joueur).equals(new Vector(400 + 10 * i, 1295 + 10 * i)));
            Assert.assertEquals(i, frame.getOrientation(joueur), 0.1);
            Assert.assertTrue(frame.getDimensions(joueur).equals(new Vector(50 + 2 * i, 100 + 2 * i)));
        }
    }
}
