package org.glo.giftw.domain;

import org.glo.giftw.domain.strategy.Field;
import org.glo.giftw.domain.util.Vector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
        field = new Field(new Vector(9000, 6000), "");
    }

    @Test
    public void testValidatePosition()
    {
        Vector p1 = new Vector(0, 0);
        Vector p2 = new Vector(0, 6000-1);
        Vector p3 = new Vector(9000-1, 6000-1);
        Vector p4 = new Vector(9000-1, 0);
        Vector p5 = new Vector(42, 113);

        Vector badP1 = new Vector(-1, 500);
        Vector badP2 = new Vector(500, -1);
        Vector badP3 = new Vector(-1, -1);

        Vector badP4 = new Vector(9000, 500);
        Vector badP5 = new Vector(500, 6000);
        Vector badP6 = new Vector(9000, 6000);

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

    @Test
    public void testGetFieldCoordinateUnitZoom()
    {
        Vector coordInPixel = new Vector(500, 500);
        Vector ratioPixelToUnit = new Vector(1, 1);
        Vector expected = new Vector(500, 500);
        assertTrue(expected.equals(field.getFieldCoordinate(coordInPixel, ratioPixelToUnit)));

    }

    @Test
    public void testGetFieldCoordinateZoomIn()
    {
        Vector coordInPixel = new Vector(500, 500);
        Vector ratioPixelToUnit = new Vector(2, 2);
        Vector expected = new Vector(250, 250);
        assertTrue(expected.equals(field.getFieldCoordinate(coordInPixel, ratioPixelToUnit)));
    }

    @Test
    public void testGetFieldCoordinateZoomOut()
    {
        Vector coordInPixel = new Vector(500, 500);
        Vector ratioPixelToUnit = new Vector(0.5, 0.5);
        Vector expected = new Vector(1000, 1000);
        assertTrue(expected.equals(field.getFieldCoordinate(coordInPixel, ratioPixelToUnit)));
    }

    @Test
    public void testGetFieldCoordinateOutOfBound()
    {
        Vector coordInPixel = new Vector(-100, 200);
        Vector ratioPixelToUnit = new Vector(1, 1);
        assertNull(field.getFieldCoordinate(coordInPixel, ratioPixelToUnit));
    }

    @Test
    public void testGetRealFieldCoordinateUnitRatio()
    {
        Vector coord = new Vector(500, 500);
        Vector ratioPixelToUnit = new Vector(1, 1);
        Vector expected = new Vector(500, 500);
        Assert.assertTrue(expected.equals(field.getFieldCoordinate(coord, ratioPixelToUnit)));

    }
    
    @Test
    public void testGetFieldCoordinateNormalRatio()
    {
        Vector pixelCoord = new Vector(500, 500);
        Vector ratioPixelToUnit = new Vector(1, 1);  //1 pixel per unit
        Vector expected = new Vector(500, 500);
        Assert.assertTrue(expected.equals(field.getFieldCoordinate(pixelCoord, ratioPixelToUnit)));
    }

    @Test
    public void testGetFieldCoordinateDoubleRatio()
    {
        Vector pixelCoord = new Vector(500, 500);
        Vector ratioPixelToUnit = new Vector(2, 2);  //2 pixels par unit
        Vector expected = new Vector(250, 250);
        Assert.assertTrue(expected.equals(field.getFieldCoordinate(pixelCoord, ratioPixelToUnit)));
    }

    @Test
    public void testGetFieldCoordinateHalfRatio()
    {
        Vector pixelCoord = new Vector(500, 500);
        Vector ratioPixelToUnit = new Vector(0.5, 0.5); //0.5 pixels per unit
        Vector expected = new Vector(1000, 1000);
        Assert.assertTrue(expected.equals(field.getFieldCoordinate(pixelCoord, ratioPixelToUnit)));
    }
    
    @Test
    public void testGetFieldCoordinateOutsideField()
    {
        Vector pixelCoord = new Vector(10000, 7000);
        Vector ratioPixelToUnit = new Vector(1, 1);  //1 pixel per unit
        Assert.assertEquals(null, field.getFieldCoordinate(pixelCoord, ratioPixelToUnit));
    }
}
