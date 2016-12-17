package org.glo.giftw.domain.strategy;

import org.glo.giftw.domain.util.Vector;

import java.io.Serializable;

public class Field implements Serializable
{
    public static final long serialVersionUID = 1L;
    private Vector pixeltoUnitRatio; //pixel par cm
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

    public Vector getPixelToUnitRatio()
    {
        return this.pixeltoUnitRatio;
    }

    public void setPixelToUnitRatio(Vector ratio)
    {
        assert ratio.getX() > 0 && ratio.getY() > 0;
        this.pixeltoUnitRatio = ratio;
    }

    public String getImagePath()
    {
        return imagePath;
    }

    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }

    public Vector getFieldCoordinate(Vector adjustedCoordinate)
    {
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
