package org.glo.giftw.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestVector
{
    private static final double EPSILON = 0.0001;

    private Vector nVec;
    private Vector upVec;
    private Vector downVec;
    private Vector leftVec;
    private Vector rightVec;

    @Before
    public void setUp()
    {
        this.nVec = new Vector();
        this.upVec = VectorFactory.up();
        this.downVec = VectorFactory.down();
        this.leftVec = VectorFactory.left();
        this.rightVec = VectorFactory.right();
    }

    @After
    public void tearDown()
    {
        //pass
    }

    @Test
    public void testMagnitude()
    {
        assertEquals(nVec.magnitude(), 0, EPSILON);
        assertEquals(upVec.magnitude(), 1, EPSILON);
        assertEquals(downVec.magnitude(), 1, EPSILON);
        assertEquals(leftVec.magnitude(), 1, EPSILON);
        assertEquals(rightVec.magnitude(), 1, EPSILON);

        assertFalse(nVec.magnitude() == 42);
        assertFalse(upVec.magnitude() == 13);

        Vector testVec = new Vector(1, 1);
        assertEquals(testVec.magnitude(), Math.sqrt(2), EPSILON);

        testVec = new Vector(3, 4);
        assertEquals(testVec.magnitude(), 5, EPSILON);
        assertFalse(testVec.magnitude() == 5.05);
    }

    @Test
    public void testDirection()
    {
        assertEquals(nVec.direction(), 0, EPSILON);
        assertFalse(nVec.direction() == 42);

        assertEquals(upVec.direction(), Math.PI/2, EPSILON);
        assertEquals(downVec.direction(), -Math.PI/2, EPSILON);
        assertEquals(rightVec.direction(), 0, EPSILON);
        assertEquals(leftVec.direction(), Math.PI, EPSILON);

        Vector testVec = new Vector(1, 1);
        assertEquals(testVec.direction(), Math.PI/4, EPSILON);
        testVec = new Vector(3, 4);
        assertEquals(testVec.direction(), 0.9273, EPSILON);
    }

    @Test
    public void testNormalized()
    {
        Vector testVec = new Vector(Math.sqrt(2)/2, Math.sqrt(2)/2);
        assertTrue(testVec.equals(testVec.normalized()));

        testVec = new Vector(1, 1);
        Vector normTestVec = testVec.normalized();
        assertEquals(normTestVec.magnitude(), 1, EPSILON);
    }
}
