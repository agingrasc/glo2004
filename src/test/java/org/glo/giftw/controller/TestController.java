package org.glo.giftw.controller;

import org.glo.giftw.domain.Vector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestController
{
    private Controller controller;

    @Before
    public void setUp()
    {

        controller = new Controller();
    }

    @Test
    public void testConvertToRealCoordinateUnitRatio()
    {
        Vector coord = new Vector(500, 500);
        double ratio = 1.0;
        Vector expected = new Vector(500, 500);
        Assert.assertTrue(expected.equals(controller.convertToRealCoordinate(coord, ratio)));

    }

    @Test
    public void testConvertToRealCoordinateDoubleRatio()
    {
        Vector coord = new Vector(500, 500);
        double ratio = 2.0;
        Vector expected = new Vector(1000, 1000);
        Assert.assertTrue(expected.equals(controller.convertToRealCoordinate(coord, ratio)));
    }

    @Test
    public void testConvertToRealCoordinateHalfRatio()
    {
        Vector coord = new Vector(500, 500);
        double ratio = 0.5;
        Vector expected = new Vector(250, 250);
        Assert.assertTrue(expected.equals(controller.convertToRealCoordinate(coord, ratio)));
    }
}
