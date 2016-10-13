package org.gimm.glooo.domain;

public abstract class GameObject
{
    private float orientation;
    private Vector position;
    private Vector scale;

    public GameObject()
    {
        this.orientation = 0;
        this.position = new Vector(0, 0);
        this.scale = new Vector(1, 1);
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

    public Vector getScale()
    {
        return this.scale;
    }

    public void setScale(Vector scale)
    {
        this.scale = scale;
    }


    public void rotate(float angle)
    {
        /*
         * Applies a rotation to the GameObject
         */
        this.orientation += angle;
    }

    public void move(Vector delta)
    {
        /*
         * Applies a translation to the GameObject
         */
        this.position.setX(position.getX() + delta.getX());
        this.position.setY(position.getY() + delta.getY());
    }
}
