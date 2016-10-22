package org.glo.giftw.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class TestGameObject
{
    private Player stdGO;
    private Player GOPos0;
    private Player GOPos1;
    private Obstacle GOCollide0;
    private Obstacle GOCollide1;

    private Vector pos0 = new Vector(500, 600);
    private Vector pos1 = new Vector(5789, 234);

    @Before
    public void setUp()
    {
        stdGO = new Player("Foo", "Centre", 0);
        GOPos0 = new Player("Bar", "Ailier", 1);
        GOPos1 = new Player("Baz", "Defenseur", 2);
        GOPos1.setCollidable(false);
        GOCollide0 = new Obstacle();
        GOCollide1 = new Obstacle();

        GOPos0.setPosition(pos0);
        GOPos1.setPosition(pos1);
        GOCollide0.setPosition(pos0);
        GOCollide1.setPosition(pos1);
    }

    @Test
    public void testDetectCollision()
    {

        assertFalse(stdGO.detectCollision(GOCollide0));
        assertFalse(stdGO.detectCollision(GOCollide1));

        assertTrue(GOPos0.detectCollision(GOPos0));
        assertTrue(GOPos0.detectCollision(GOCollide0)); //joueur et obstacle a la meme position
        assertFalse(GOPos0.detectCollision(GOCollide1)); //joueur et obstacle a une position differente

        assertFalse(GOPos1.detectCollision(GOPos1)); //on a desactive le collidable sur le joueur 2
        GOPos1.setCollidable(true);
        assertTrue(GOPos1.detectCollision(GOCollide1));
        assertFalse(GOPos1.detectCollision(GOCollide0));
    }

    @Test
    public void testUniqueId()
    {
        GameObject.setObjectCount(1012); //on aug artificiellement le objCount
        Player p = new Player();
        assertEquals(1013, p.getId());

        GameObject.setObjectCount(1012); //l'augmentation ne passera pas
        Player p2 = new Player();
        assertNotEquals(1013, p2.getId());
        assertEquals(1014, p2.getId());

        GameObject.setObjectCount(1112); //on valide qu'on ne suit pas un ordre predet par hasard (13-14-15)
        Player p3 = new Player();
        assertNotEquals(1015, p3.getId());
        assertEquals(1113, p3.getId());
    }
}
