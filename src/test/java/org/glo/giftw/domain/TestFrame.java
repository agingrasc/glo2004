package org.glo.giftw.domain;

import org.glo.giftw.domain.strategy.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 *
 */
@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class TestFrame
{
    private Player playerOne, playerTwo;
    private GameObjectState gosP1, gosP2;
    private Obstacle obsOne, obsTwo, obsThree, obsNo, obsNoTwo;
    private GameObjectState gosO1, gosO2, gosO3, gosNo, gosNo2;
    private Frame frame;
    private Set<GameObject> expectedCollisions;
    private HashMap<GameObject, Set<GameObject>> allExpectedCollisions;

    @Before
    public void setUp()
    {
        playerOne = new Player("Foo", "Goaler", 13);
        gosP1 = new GameObjectState();        
        playerTwo = new Player("Bar", "Ailier", 42);
        gosP2 = new GameObjectState(new org.glo.giftw.domain.util.Vector(1500, 3000), 0, new org.glo.giftw.domain.util.Vector(1, 1));
        
        obsOne = new Obstacle();
        gosO1 = new GameObjectState();
        obsTwo = new Obstacle();
        gosO2 = new GameObjectState();
        obsThree = new Obstacle();
        gosO3 = new GameObjectState(new org.glo.giftw.domain.util.Vector(1500, 3000), 0, new org.glo.giftw.domain.util.Vector(1, 1));
        obsNo = new Obstacle();
        gosNo = new GameObjectState(new org.glo.giftw.domain.util.Vector(500, 700), 0, new org.glo.giftw.domain.util.Vector());
        obsNoTwo = new Obstacle();
        gosNo2 = new GameObjectState(new org.glo.giftw.domain.util.Vector(700, 120), 0, new org.glo.giftw.domain.util.Vector());

        frame = new Frame();
        frame.addGameObject(playerOne, gosP1);
        frame.addGameObject(obsNo, gosNo);
        frame.addGameObject(obsNoTwo, gosNo2);

        expectedCollisions = null;
        allExpectedCollisions = new HashMap<>();
        allExpectedCollisions.put(playerOne, new HashSet<>());
        allExpectedCollisions.put(obsNo, new HashSet<>());
        allExpectedCollisions.put(obsNoTwo, new HashSet<>());
    }

    @Test
    public void testDetectCollisionNone()
    {
        expectedCollisions = new HashSet<>();
        assertEquals(expectedCollisions, frame.detectCollisions((playerOne)));
    }

    @Test
    public void testDetectCollisionsOne()
    {
        expectedCollisions = new HashSet<GameObject>();
        expectedCollisions.add(obsOne);
        frame.addGameObject(obsOne, gosO1);
        assertEquals(expectedCollisions, frame.detectCollisions(playerOne));
    }

    @Test
    public void testDetectCollisionsTwoGameObject()
    {
        expectedCollisions = new HashSet<GameObject>();
        expectedCollisions.add(obsOne);
        expectedCollisions.add(obsTwo);
        frame.addGameObject(obsOne, gosO1);
        frame.addGameObject(obsTwo, gosO2);
        assertEquals(expectedCollisions, frame.detectCollisions(playerOne));
    }

    @Test
    public void testDetectCollisionsOnePlayerNoCollision()
    {
        assertEquals(allExpectedCollisions, frame.detectCollisions());
    }

    @Test
    public void testDetectCollisionsOnePlayerOneCollision()
    {
        frame.addGameObject(obsOne, gosO1);
        HashSet<GameObject> h1 = new HashSet<GameObject>();
        h1.add(playerOne);
        allExpectedCollisions.put(obsOne, h1);
        HashSet<GameObject> h2 = new HashSet<GameObject>();
        h2.add(obsOne);
        allExpectedCollisions.put(playerOne, h2);
        assertEquals(allExpectedCollisions, frame.detectCollisions());
    }

    @Test
    public void testDetectCollisionsOnePlayerManyCollisions()
    {
        frame.addGameObject(obsOne, gosO1);
        frame.addGameObject(obsTwo, gosO2);
        HashSet<GameObject> h1 = new HashSet<GameObject>();
        h1.add(obsOne);
        h1.add(obsTwo);
        HashSet<GameObject> h2 = new HashSet<GameObject>();
        h2.add(obsTwo);
        h2.add(playerOne);
        HashSet<GameObject> h3 = new HashSet<GameObject>();
        h3.add(obsOne);
        h3.add(playerOne);
        allExpectedCollisions.put(playerOne, h1);
        allExpectedCollisions.put(obsOne, h2);
        allExpectedCollisions.put(obsTwo, h3);

        assertEquals(allExpectedCollisions, frame.detectCollisions());
    }

    @Test
    public void testDetectCollisionsManyPlayerNoCollision()
    {
        frame.addGameObject(playerTwo, gosP2);
        allExpectedCollisions.put(playerTwo, new HashSet<>());

        assertEquals(allExpectedCollisions, frame.detectCollisions());
    }

    @Test
    public void testDetectCollisionsManyPlayersOneCollision()
    {
        frame.addGameObject(obsOne, gosO1);
        frame.addGameObject(playerTwo, gosP2);
        allExpectedCollisions.put(playerTwo, new HashSet<>());
        HashSet<GameObject> h1 = new HashSet<GameObject>();
        h1.add(obsOne);
        HashSet<GameObject> h2 = new HashSet<GameObject>();
        h2.add(playerOne);
        allExpectedCollisions.put(playerOne, h1);
        allExpectedCollisions.put(obsOne, h2);
        assertEquals(allExpectedCollisions, frame.detectCollisions());

        frame.removeGameObject(obsOne);
        frame.addGameObject(obsThree, gosO3);
        allExpectedCollisions.remove(obsOne);
        allExpectedCollisions.put(playerOne, new HashSet<>());
        HashSet<GameObject> h3 = new HashSet<GameObject>();
        h3.add(obsThree);
        HashSet<GameObject> h4 = new HashSet<GameObject>();
        h4.add(playerTwo);
        allExpectedCollisions.put(playerTwo, h3);
        allExpectedCollisions.put(obsThree, h4);
        assertEquals(allExpectedCollisions, frame.detectCollisions());
    }

    @Test
    public void testDetectCollisionsManyPlayersManyCollisions()
    {
        frame.addGameObject(obsOne, gosO1);
        frame.addGameObject(obsThree, gosO3);
        frame.addGameObject(playerTwo, gosP2);
        
        HashSet<GameObject> h1 = new HashSet<GameObject>();
        h1.add(obsOne);
        HashSet<GameObject> h2 = new HashSet<GameObject>();
        h2.add(playerOne);
        HashSet<GameObject> h3 = new HashSet<GameObject>();
        h3.add(obsThree);
        HashSet<GameObject> h4 = new HashSet<GameObject>();
        h4.add(playerTwo);
        allExpectedCollisions.put(playerOne, h1);
        allExpectedCollisions.put(obsOne, h2);
        allExpectedCollisions.put(playerTwo, h3);
        allExpectedCollisions.put(obsThree, h4);
        
        assertEquals(allExpectedCollisions, frame.detectCollisions());
    }
}
