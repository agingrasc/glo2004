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

    public boolean validatePosition(GameObject gameObject)
    {
        return gameObject.getPosition().getX() >= 0 &&
                gameObject.getPosition().getY() >= 0 &&
                this.dimensions.getX() - 1 >= gameObject.getPosition().getX() &&
                this.dimensions.getY() - 1 >= gameObject.getPosition().getY();
    }

    @Override
    public String toString()
    {
        return "Dimension: " + this.dimensions.toString();
    }
}
