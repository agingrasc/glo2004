package org.glo.giftw.domain;

import java.io.Serializable;
import java.lang.Math;

public abstract class GameObject implements Serializable
{
    public static final long serialVersionUID = 1L;
    private static Integer objectCount = 0;

    protected float orientation;
    protected Vector position;
    protected Vector scale;
    protected String name;
    protected boolean collidable;
    protected Integer id;

    public GameObject()
    {
        this.orientation = 0;
        this.position = new Vector(0, 0);
        this.scale = new Vector(1, 1);
        this.name = "";
        this.collidable = true;
        GameObject.objectCount++;
        this.id = GameObject.objectCount;
    }

    public GameObject(GameObject gameObject)
    {
        this.orientation = gameObject.orientation;
        this.position = gameObject.position;
        this.scale = gameObject.scale;
        this.name = gameObject.name;
        this.collidable = gameObject.collidable;
        this.id = gameObject.id;
    }
    
    public abstract GameObject copy();

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

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public boolean isCollidable()
    {
        return this.collidable;
    }

    public void setCollidable(boolean isCollidable)
    {
        this.collidable = isCollidable;
    }
    
    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
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
    
    /**
     * Check if the specified GameObject collides with this GameObject
     * @param other : the other GameObject
     * @return A boolean indicating if there is a collision
     */
    public boolean detectCollision(GameObject other)
    {
        if(this.collidable && other.collidable)
        {
            double dx = Math.abs(other.position.getX() - this.position.getX());
            double dy = Math.abs(other.position.getY() - this.position.getY());
            return dx < this.scale.getX() + other.scale.getX() && dy < this.scale.getY() + other.scale.getY();
        }
        else
        {
            return false;
        }
    }

    public static void setObjectCount(Integer count)
    {
        if (GameObject.objectCount < count)
        {
            GameObject.objectCount = count;
        }
    }
}
