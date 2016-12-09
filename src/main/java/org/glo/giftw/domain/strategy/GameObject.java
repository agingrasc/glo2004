package org.glo.giftw.domain.strategy;

import java.io.Serializable;
import java.util.UUID;

import org.glo.giftw.domain.util.Vector;

public abstract class GameObject implements Serializable
{
    public static final long serialVersionUID = 1L;

    protected String name;
    protected boolean collidable;
    protected String id;
    protected Vector dimensions;

    public GameObject()
    {
        this.name = "";
        this.collidable = true;
        this.id = UUID.randomUUID().toString();
        this.dimensions = new Vector(1, 1);
    }

    public GameObject(GameObject gameObject)
    {
        this.name = gameObject.name;
        this.collidable = gameObject.collidable;
        this.id = UUID.randomUUID().toString();
        this.dimensions = gameObject.dimensions;
    }

    public abstract GameObject copy();

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

    public String getId()
    {
        return this.id;
    }

    public Vector getDimensions()
    {
        return dimensions;
    }

    public void setDimensions(Vector dimensions)
    {
        this.dimensions = dimensions;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
