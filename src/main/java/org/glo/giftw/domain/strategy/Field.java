package org.glo.giftw.domain.strategy;

import org.glo.giftw.domain.util.Vector;

import java.io.Serializable;

public class Field implements Serializable
{
    public static final long serialVersionUID = 1L;

    private Vector dimensions;
    private double unitRatio;  //nombre d'unités par mètre
    private String imagePath;

    public Field()
    {
        this(new Vector(6096, 2590), 100, ""); //taille d'une patinoire nord-américaine standard en cm
    }

    public Field(Vector dimensions, String fieldImagePath)
    {
        this(dimensions, 100, fieldImagePath);
    }
    
    public Field(Vector dimensions, double unitRatio, String fieldImagePath)
    {
        this.dimensions = dimensions;
        this.unitRatio = unitRatio;
        this.imagePath = fieldImagePath;
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
        return this.unitRatio;
    }
    
    public void setUnitRatio(Vector dimensionInPixel)
    {
        double ratio = this.getDimensions().getX() / dimensionInPixel.getX();
        this.unitRatio = ratio;
    }

    public String getImagePath()
    {
        return imagePath;
    }

    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }

    public Vector getFieldCoordinate(Vector adjustedCoordinate, float zoomLevel)
    {
        Vector fieldCoord = new Vector(adjustedCoordinate);
        fieldCoord.div(zoomLevel);
        if (this.validatePosition(fieldCoord))
        {
            return fieldCoord;
        }
        else
        {
            return null;
        }

    }

    public Vector getRealFieldCoordinate(Vector adjustedCoordinate, float zoomLevel)
    {
        Vector fieldCoord = this.getFieldCoordinate(adjustedCoordinate, zoomLevel);
        if (fieldCoord != null)
        {
            fieldCoord.mul(this.getUnitRatio());
        }
        return fieldCoord;
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
        return "Dimension: " + this.dimensions.toString() + "\nUnit Ratio: " + Double.toString(this.unitRatio) +
                "\nImage path: " + this.imagePath;
    }
    
    public Vector getCoordinate(Vector clickPosition, Vector fieldPosition, float zoomLevel)
    {
        assert zoomLevel > 0;
        
        double x = (clickPosition.getX() - fieldPosition.getX()) / zoomLevel;
        double y = (clickPosition.getY() - fieldPosition.getY()) / zoomLevel;
        
        if(x >= 0 && y >= 0 && x < this.dimensions.getX() && y < this.dimensions.getY())
        {
            return new Vector(x, y);
        }
        else
        {
            return null;
        }
    }
}
