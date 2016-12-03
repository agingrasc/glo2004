package org.glo.giftw.domain.strategy;

import java.io.Serializable;
import java.util.UUID;

public abstract class GameObject implements Serializable
{
    public static final long serialVersionUID = 1L;

    protected String name;
    protected boolean collidable;
    protected String id;

    public GameObject()
    {
        this.name = "";
        this.collidable = true;
        this.id = UUID.randomUUID().toString();
    }

    public GameObject(GameObject gameObject)
    {
        this.name = gameObject.name;
        this.collidable = gameObject.collidable;
        this.id = UUID.randomUUID().toString();
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

    public void setId(String id)
    {
        this.id = id;
    }
}
