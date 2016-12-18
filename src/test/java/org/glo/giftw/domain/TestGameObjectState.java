package org.glo.giftw.domain;

import org.glo.giftw.domain.strategy.GameObjectState;
import org.glo.giftw.domain.util.Vector;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGameObjectState
{
    private float precision = 0.001f;

    private GameObjectState gos1;
    private GameObjectState gos2;

    @Before
    public void setUp()
    {
        gos1 = new GameObjectState();
        gos1.setOrientation(30);
        gos1.setPosition(new Vector(50, 50));
        gos1.setDimensions(new Vector(10, 20));

        gos2 = new GameObjectState();
        gos2.setPosition(new Vector(70, 70));
        gos2.setDimensions(new Vector(10, 10));
    }

    @Test
    public void testRotate()
    {
        gos1.rotate(60);
        assertEquals(90, gos1.getOrientation(), this.precision);
        gos1.rotate(-45);
        assertEquals(45, gos1.getOrientation(), this.precision);
    }

    @Test
    public void testMove()
    {
        gos1.move(new Vector(30, -25));
        assertEquals(80, gos1.getPosition().getX(), this.precision);
        assertEquals(25, gos1.getPosition().getY(), this.precision);
    }

    @Ignore
    @Test
    public void testDetectCollisions()
    {
        assertTrue(gos1.detectCollision(gos1)); //collisionne avec un object à la même position

        assertFalse(gos1.detectCollision(gos2)); //pas de collision
        assertFalse(gos2.detectCollision(gos1)); //symétrie

        gos2.setPosition(new Vector(50, 30));
        assertFalse(gos1.detectCollision(gos2));

        gos2.setPosition(new Vector(60, 50));
        assertFalse(gos1.detectCollision(gos2)); //limite horizontale

        gos2.setPosition(new Vector(50, 65));
        assertFalse(gos1.detectCollision(gos2)); //limite verticale

        gos2.setPosition(new Vector(59, 64));
        assertTrue(gos1.detectCollision(gos2));

        gos2.setPosition(new Vector(42, 45));
        assertTrue(gos1.detectCollision(gos2));
    }

    @Test
    public void testOccupiesPosition()
    {
        assertTrue(gos1.occupiesPosition(new Vector(50, 50)));
        assertTrue(gos1.occupiesPosition(new Vector(55, 60)));
        assertTrue(gos1.occupiesPosition(new Vector(60, 70)));
        assertTrue(gos1.occupiesPosition(new Vector(51, 68)));
        assertTrue(gos1.occupiesPosition(new Vector(59, 52)));

        assertFalse(gos1.occupiesPosition(new Vector(0, 0)));
        assertFalse(gos1.occupiesPosition(new Vector(55, 71)));
        assertFalse(gos1.occupiesPosition(new Vector(63, 60)));
        assertFalse(gos1.occupiesPosition(new Vector(55, 42)));
        assertFalse(gos1.occupiesPosition(new Vector(-50, -50)));
    }
}
