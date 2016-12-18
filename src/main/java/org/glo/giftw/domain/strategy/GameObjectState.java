package org.glo.giftw.domain.strategy;

import org.glo.giftw.domain.util.Vector;

import java.io.Serializable;

/**
 * Classe conteneur regroupant la position, l'orientation et les dimensions d'un GameObject.
 * Cette classe est utilisée pour stocker l'état des GameObjects dans le hashmap de la classe Frame.
 */
public class GameObjectState implements Serializable
{
    public static final long serialVersionUID = 1L;

    private Vector position;    //La position d'un gameObject correspond à son coin supérieur gauche 
    private float orientation;
    private Vector dimensions;


    public GameObjectState()
    {
        this.orientation = 0;
        this.position = new Vector(0, 0);
        this.dimensions = new Vector(1, 1);
    }

    public GameObjectState(Vector position, float orientation, Vector dimensions)
    {
        this.position = position;
        this.orientation = orientation;
        this.dimensions = dimensions;
    }

    public GameObjectState(GameObjectState gos)
    {
        this.position = new Vector(gos.position);
        this.orientation = gos.orientation;
        this.dimensions = new Vector(gos.dimensions);
    }

    public float getOrientation()
    {
        return this.orientation;
    }

    public void setOrientation(float orientation)
    {
        this.orientation = orientation;
    }

    public Vector getPosition()
    {
        return this.position;
    }

    public void setPosition(Vector position)
    {
        this.position = position;
    }

    public Vector getDimensions()
    {
        return this.dimensions;
    }

    public void setDimensions(Vector dimensions)
    {
        this.dimensions = dimensions;
    }

    /**
     * Applies a rotation to the GameObject
     */
    public void rotate(float angle)
    {
        this.orientation += angle;
    }

    /**
     * Applies a translation to the GameObject
     */
    public void move(Vector delta)
    {
        this.position.setX(position.getX() + delta.getX());
        this.position.setY(position.getY() + delta.getY());
    }

    /**
     * Check if the specified GameObject collides with this GameObject
     *
     * @param other : the state of the other GameObject
     * @return A boolean indicating if there is a collision
     */
    public boolean detectCollision(GameObjectState other)
    {
        double dx = Math.abs(other.position.getX() - this.position.getX() + 0.5*(other.dimensions.getX() - this.dimensions.getX()));
        double dy = Math.abs(other.position.getY() - this.position.getY() + 0.5*(other.dimensions.getY() - this.dimensions.getY()));
        return dx < (this.dimensions.getX() + other.dimensions.getX()) / 2 && dy < (this.dimensions.getY() + other.dimensions.getY()) / 2;
    }

    public double getDistance(Vector position)
    {
        return this.position.getDistance(position);
    }

    public double getDistance(GameObjectState other)
    {
        return this.getDistance(other.position);
    }

    public boolean occupiesPosition(Vector position)
    {
        return  position.getX() > this.position.getX() &&
                this.position.getX() + this.dimensions.getX() > position.getX() &&
                position.getY() > this.position.getY() &&
                this.position.getY() + this.dimensions.getY() > position.getY();
    }

    @Override
    public String toString()
    {
        return "Position: " + this.position.toString() + "\nOrientation: " + Float.toString(this.orientation) +
                "\nDimensions: " + this.dimensions.toString();
    }
}
