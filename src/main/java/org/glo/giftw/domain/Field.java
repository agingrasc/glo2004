package org.glo.giftw.domain;

import java.io.Serializable;

public class Field implements Serializable
{
    public static final long serialVersionUID = 1L;

    private Vector dimensions;

    public Field()
    {
        this.dimensions = new Vector(100, 100);
    }

    public Field(Vector dimensions)
    {
        this.dimensions = dimensions;
    }

    public Vector getDimensions()
    {
        return this.dimensions;
    }

    public void setDimensions(Vector dimensions)
    {
        this.dimensions = dimensions;
    }

    public boolean validatePosition(Vector position)
    {
        return position.getX() >= 0 && position.getY() >= 0 &&
                this.dimensions.getX() - 1 >= position.getX() &&
                this.dimensions.getY() - 1 >= position.getY();
    }

    @Override
    public String toString()
    {
        return "Dimension: " + this.dimensions.toString();
    }
}
