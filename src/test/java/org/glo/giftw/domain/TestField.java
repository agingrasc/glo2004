package org.glo.giftw.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class TestField
{
    private Field field;
    @Before
    public void setUp()
    {
        field = new Field(new Vector(9000, 6000));
    }

    @Test
    public void testValidatePosition()
    {
        Player p1 = new Player();
        p1.setPosition(new Vector(0, 0));
        Player p2 = new Player();
        p2.setPosition(new Vector(0, 6000-1));
        Player p3 = new Player();
        p3.setPosition(new Vector(9000-1, 6000-1));
        Player p4 = new Player();
        p4.setPosition(new Vector(9000-1, 0));
        Player p5 = new Player();
        p5.setPosition(new Vector(42, 113));

        Player badP1 = new Player();
        badP1.setPosition(new Vector(-1, 500));
        Player badP2 = new Player();
        badP2.setPosition(new Vector(500, -1));
        Player badP3 = new Player();
        badP3.setPosition(new Vector(-1, -1));

        Player badP4 = new Player();
        badP4.setPosition(new Vector(9000, 500));
        Player badP5 = new Player();
        badP5.setPosition(new Vector(500, 6000));
        Player badP6 = new Player();
        badP6.setPosition(new Vector(9000, 6000));

        assertTrue(field.validatePosition(p1));
        assertTrue(field.validatePosition(p2));
        assertTrue(field.validatePosition(p3));
        assertTrue(field.validatePosition(p4));
        assertTrue(field.validatePosition(p5));

        assertFalse(field.validatePosition(badP1));
        assertFalse(field.validatePosition(badP2));
        assertFalse(field.validatePosition(badP3));
        assertFalse(field.validatePosition(badP4));
        assertFalse(field.validatePosition(badP5));
        assertFalse(field.validatePosition(badP6));
    }
}
