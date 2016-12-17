package org.glo.giftw.domain;

import org.glo.giftw.domain.strategy.Field;
import org.glo.giftw.domain.util.Vector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void testValidatePositionValid()
    {
        Vector pos = new Vector(500, 300);
        assertTrue(this.field.validatePosition(pos));
    }

    @Test
    public void testValidatePositionValidLeftTopCorner()
    {
        Vector pos = new Vector(0, 0);
        assertTrue(this.field.validatePosition(pos));
    }

    @Test
    public void testValidatePositionValidRightTopCorner()
    {
        Vector pos = new Vector(9000, 0);
        assertTrue(this.field.validatePosition(pos));
    }

    @Test
    public void testValidatePositionValidLeftBottomCorner()
    {
        Vector pos = new Vector(0, 6000);
    }

    @Test
    public void testValidatePositionValidRightBottomCorner()
    {
        Vector pos = new Vector(9000, 6000);
    }

    @Test
    public void testValidatePositionInvalidOutsideLeft()
    {
        Vector pos = new Vector(-1, 300);
        assertFalse(this.field.validatePosition(pos));
    }

    @Test
    public void testValidatePositionInvalidOutsideBottom()
    {
        Vector pos = new Vector(500, -1);
        assertFalse(this.field.validatePosition(pos));
    }

    @Test
    public void testValidatePositionInvalidOutRight()
    {
        Vector pos = new Vector(9001, 300);
        assertFalse(this.field.validatePosition(pos));
    }

    @Test
    public void testValidatePositionInvalidOutsideTop()
    {
        Vector pos = new Vector(500, 6001);
        assertFalse(this.field.validatePosition(pos));
    }

    @Test
    public void testGetFieldCoordinateUnitZoom()
    {
        Vector coordInPixel = new Vector(500, 500);
        Vector ratioPixelToUnit = new Vector(1, 1);
        this.field.setPixelToUnitRatio(ratioPixelToUnit);
        Vector expected = new Vector(500, 500);
        assertTrue(expected.equals(field.getFieldCoordinate(coordInPixel)));

    }

    @Test
    public void testGetFieldCoordinateZoomIn()
    {
        Vector coordInPixel = new Vector(500, 500);
        Vector ratioPixelToUnit = new Vector(2, 2);
        Vector expected = new Vector(250, 250);
        this.field.setPixelToUnitRatio(ratioPixelToUnit);
        assertTrue(expected.equals(field.getFieldCoordinate(coordInPixel)));
    }

    @Test
    public void testGetFieldCoordinateZoomOut()
    {
        Vector coordInPixel = new Vector(500, 500);
        Vector ratioPixelToUnit = new Vector(0.5, 0.5);
        Vector expected = new Vector(1000, 1000);
        this.field.setPixelToUnitRatio(ratioPixelToUnit);
        assertTrue(expected.equals(field.getFieldCoordinate(coordInPixel)));
    }

    @Test
    public void testGetFieldCoordinateOutOfBound()
    {
        Vector coordInPixel = new Vector(-100, 200);
        Vector ratioPixelToUnit = new Vector(1, 1);
        this.field.setPixelToUnitRatio(ratioPixelToUnit);
        assertNull(field.getFieldCoordinate(coordInPixel));
    }

    @Test
    public void testGetRealFieldCoordinateUnitRatio()
    {
        Vector coord = new Vector(500, 500);
        Vector ratioPixelToUnit = new Vector(1, 1);
        Vector expected = new Vector(500, 500);
        this.field.setPixelToUnitRatio(ratioPixelToUnit);
        Assert.assertTrue(expected.equals(field.getFieldCoordinate(coord)));

    }

    @Test
    public void testGetFieldCoordinateNormalRatio()
    {
        Vector pixelCoord = new Vector(500, 500);
        Vector ratioPixelToUnit = new Vector(1, 1);  //1 pixel per unit
        Vector expected = new Vector(500, 500);
        this.field.setPixelToUnitRatio(ratioPixelToUnit);
        Assert.assertTrue(expected.equals(field.getFieldCoordinate(pixelCoord)));
    }

    @Test
    public void testGetFieldCoordinateDoubleRatio()
    {
        Vector pixelCoord = new Vector(500, 500);
        Vector ratioPixelToUnit = new Vector(2, 2);  //2 pixels par unit
        Vector expected = new Vector(250, 250);
        this.field.setPixelToUnitRatio(ratioPixelToUnit);
        Assert.assertTrue(expected.equals(field.getFieldCoordinate(pixelCoord)));
    }

    @Test
    public void testGetFieldCoordinateHalfRatio()
    {
        Vector pixelCoord = new Vector(500, 500);
        Vector ratioPixelToUnit = new Vector(0.5, 0.5); //0.5 pixels per unit
        Vector expected = new Vector(1000, 1000);
        this.field.setPixelToUnitRatio(ratioPixelToUnit);
        Assert.assertTrue(expected.equals(field.getFieldCoordinate(pixelCoord)));
    }

    @Test
    public void testGetFieldCoordinateOutsideField()
    {
        Vector pixelCoord = new Vector(10000, 7000);
        Vector ratioPixelToUnit = new Vector(1, 1);  //1 pixel per unit
        this.field.setPixelToUnitRatio(ratioPixelToUnit);
        Assert.assertEquals(null, field.getFieldCoordinate(pixelCoord));
    }
}
