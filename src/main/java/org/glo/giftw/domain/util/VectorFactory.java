package org.glo.giftw.domain.util;

public class VectorFactory
{
    public static Vector up()
    {
        /*
         * Generate the up direction unit vector
         */
        return new Vector(0, 1);
    }

    public static Vector down()
    {
        /*
         * Generate the down direction unit vector
         */
        return new Vector(0, -1);
    }

    public static Vector left()
    {
        /*
         * Generate the left direction unit vector
         */
        return new Vector(-1, 0);
    }

    public static Vector right()
    {
        /*
         * Generate the right direction unit vector
         */
        return new Vector(1, 0);
    }
}
