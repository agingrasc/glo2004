package org.glo.giftw.domain.strategy;

import org.glo.giftw.domain.util.Vector;

import java.io.Serializable;

public class Field implements Serializable
{
    public static final long serialVersionUID = 1L;
    private static double unitRatio = 100;  //nombre d'unités par mètre
    private Vector pixeltoUnitRatio;

    private Vector dimensions;
    private String imagePath;

    public Field()
    {
        this(new Vector(6096, 2590), ""); //taille d'une patinoire nord-américaine standard en cm
    }

    public Field(Vector dimensions, String fieldImagePath)
    {
        this.dimensions = dimensions;
        this.imagePath = fieldImagePath;
        this.pixeltoUnitRatio = new Vector();
    }

    public Vector getDimensions()
    {
        return this.dimensions;
    }

    public void setDimensions(Vector dimensions)
    {
        this.dimensions = dimensions;
    }

    public double getUnitRatio()
    {
        return Field.unitRatio;
    }

    public String getImagePath()
    {
        return imagePath;
    }

    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }

    public void setPixelToUnitRatio(Vector ratio)
    {
        this.pixeltoUnitRatio = ratio;
    }

    public Vector getFieldCoordinate(Vector adjustedCoordinate)
    {
        assert this.pixeltoUnitRatio.getX() > 0 && this.pixeltoUnitRatio.getY() > 0;
        Vector zoomAdjustedCoordinate = adjustedCoordinate.div(this.pixeltoUnitRatio);
        if (this.validatePosition(zoomAdjustedCoordinate))
        {
            return zoomAdjustedCoordinate;
        }
        else
        {
            return null;
        }

    }

    public boolean validatePosition(Vector position)
    {
        return position.getX() >= 0 && position.getY() >= 0 &&
                this.dimensions.getX() >= position.getX() &&
                this.dimensions.getY() >= position.getY();
    }

    @Override
    public String toString()
    {
        return "Dimension: " + this.dimensions.toString() + "\nImage path: " + this.imagePath;
    }
}
