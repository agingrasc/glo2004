package org.glo.giftw.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class TestGameObject
{
    @Before
    public void setUp()
    {
        
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
