package org.glo.giftw.domain;

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
    private Obstacle obsOne, obsTwo, obsThree, obsNo, obsNoTwo;
    private Frame frame;
    private Set<Integer> expectedIds;
    private HashMap<Integer, Set<Integer>> expectedCollisionsForIds;

    @Before
    public void setUp()
    {
        playerOne = new Player("Foo", "Goaler", 13);
        playerOne.setPosition(new Vector(0, 0));
        playerTwo = new Player("Bar", "Ailier", 42);
        playerTwo.setPosition(new Vector(1500, 3000));
        obsOne = new Obstacle();
        obsOne.setPosition(new Vector(0, 0));
        obsTwo = new Obstacle();
        obsTwo.setPosition(new Vector(0, 0));
        obsThree = new Obstacle();
        obsThree.setPosition(new Vector(1500, 3000));
        obsNo = new Obstacle();
        obsNo.setPosition(new Vector(500, 700));
        obsNoTwo = new Obstacle();
        obsNoTwo.setPosition(new Vector(700, 120));

        frame = new Frame();
        frame.addGameObject(playerOne);
        frame.addGameObject(obsNo);
        frame.addGameObject(obsNoTwo);

        expectedIds = null;
        expectedCollisionsForIds = new HashMap<>();
        expectedCollisionsForIds.put(playerOne.getId(), new HashSet<>());
        expectedCollisionsForIds.put(obsNo.getId(), new HashSet<>());
        expectedCollisionsForIds.put(obsNoTwo.getId(), new HashSet<>());
    }

    @Test
    public void testDetectCollisionNone()
    {
        expectedIds = new HashSet<>();
        assertEquals(expectedIds, frame.detectCollisions((playerOne)));
    }

    @Test
    public void testDetectCollisionsOne()
    {
        expectedIds = new HashSet<>(Arrays.asList(obsOne.getId()));
        frame.addGameObject(obsOne);
        assertEquals(expectedIds, frame.detectCollisions(playerOne));
    }

    @Test
    public void testDetectCollisionsTwoGameObject()
    {
        expectedIds = new HashSet<>(Arrays.asList(obsOne.getId(), obsTwo.getId()));
        frame.addGameObject(obsOne);
        frame.addGameObject(obsTwo);
        assertEquals(expectedIds, frame.detectCollisions(playerOne));
    }

    @Test
    public void testDetectCollisionsOnePlayerNoCollision()
    {
        assertEquals(expectedCollisionsForIds, frame.detectCollisions());
    }

    @Test
    public void testDetectCollisionsOnePlayerOneCollision()
    {
        frame.addGameObject(obsOne);
        expectedCollisionsForIds.put(obsOne.getId(), new HashSet<>(Arrays.asList(playerOne.getId())));
        expectedCollisionsForIds.put(playerOne.getId(), new HashSet<>(Arrays.asList(obsOne.getId())));
        assertEquals(expectedCollisionsForIds, frame.detectCollisions());
    }

    @Test
    public void testDetectCollisionsOnePlayerManyCollisions()
    {
        frame.addGameObject(obsOne);
        frame.addGameObject(obsTwo);
        List<Integer> idInCollisionsForPlayer = Arrays.asList(obsOne.getId(), obsTwo.getId());
        List<Integer> idInCollisionsForObsOne = Arrays.asList(obsTwo.getId(), playerOne.getId());
        List<Integer> idInCollisionsForObsTwo = Arrays.asList(obsOne.getId(), playerOne.getId());
        expectedCollisionsForIds.put(playerOne.getId(), new HashSet<>(idInCollisionsForPlayer));
        expectedCollisionsForIds.put(obsOne.getId(), new HashSet<>(idInCollisionsForObsOne));
        expectedCollisionsForIds.put(obsTwo.getId(), new HashSet<>(idInCollisionsForObsTwo));

        assertEquals(expectedCollisionsForIds, frame.detectCollisions());
    }

    @Test
    public void testDetectCollisionsManyPlayerNoCollision()
    {
        frame.addGameObject(playerTwo);
        expectedCollisionsForIds.put(playerTwo.getId(), new HashSet<>());

        assertEquals(expectedCollisionsForIds, frame.detectCollisions());
    }

    @Test
    public void testDetectCollisionsManyPlayersOneCollision()
    {
        frame.addGameObject(obsOne);
        frame.addGameObject(playerTwo);
        expectedCollisionsForIds.put(playerTwo.getId(), new HashSet<>());
        Collection<Integer> idInCollisionForPlayerOne = Arrays.asList(obsOne.getId());
        Collection<Integer> idInCollisionForObsOne = Arrays.asList(playerOne.getId());
        expectedCollisionsForIds.put(playerOne.getId(), new HashSet<>(idInCollisionForPlayerOne));
        expectedCollisionsForIds.put(obsOne.getId(), new HashSet<>(idInCollisionForObsOne));
        assertEquals(expectedCollisionsForIds, frame.detectCollisions());

        frame.removeGameObject(obsOne.getId());
        frame.addGameObject(obsThree);
        expectedCollisionsForIds.remove(obsOne.getId());
        expectedCollisionsForIds.put(playerOne.getId(), new HashSet<>());
        Collection<Integer> idInCollisionForPlayerTwo = Arrays.asList(obsThree.getId());
        Collection<Integer> idInCollisionForObsThree = Arrays.asList(playerTwo.getId());
        expectedCollisionsForIds.put(playerTwo.getId(), new HashSet<>(idInCollisionForPlayerTwo));
        expectedCollisionsForIds.put(obsThree.getId(), new HashSet<>(idInCollisionForObsThree));
        assertEquals(expectedCollisionsForIds, frame.detectCollisions());
    }

    @Test
    public void testDetectCollisionsManyPlayersManyCollisions()
    {
        frame.addGameObject(obsOne);
        frame.addGameObject(obsThree);
        frame.addGameObject(playerTwo);
        Collection<Integer> idInCollisionForPlayerOne = Arrays.asList(obsOne.getId());
        Collection<Integer> idInCollisionForObsOne = Arrays.asList(playerOne.getId());
        Collection<Integer> idInCollisionForPlayerTwo = Arrays.asList(obsThree.getId());
        Collection<Integer> idInCollisionForObsThree = Arrays.asList(playerTwo.getId());
        expectedCollisionsForIds.put(playerOne.getId(), new HashSet<>(idInCollisionForPlayerOne));
        expectedCollisionsForIds.put(obsOne.getId(), new HashSet<>(idInCollisionForObsOne));
        expectedCollisionsForIds.put(playerTwo.getId(), new HashSet<>(idInCollisionForPlayerTwo));
        expectedCollisionsForIds.put(obsThree.getId(), new HashSet<>(idInCollisionForObsThree));
        assertEquals(expectedCollisionsForIds, frame.detectCollisions());
    }
}
