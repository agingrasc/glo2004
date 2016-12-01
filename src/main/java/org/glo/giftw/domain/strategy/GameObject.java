package org.glo.giftw.domain.strategy;

import org.glo.giftw.domain.Dragable;

import java.io.Serializable;

public abstract class GameObject implements Serializable, Dragable
{
    public static final long serialVersionUID = 1L;
    private static Integer objectCount = 0;

    protected String name;
    protected boolean collidable;
    protected Integer id;

    public GameObject()
    {
        this.name = "";
        this.collidable = true;
        GameObject.objectCount++;
        this.id = GameObject.objectCount;
    }

    public GameObject(GameObject gameObject)
    {
        this.name = gameObject.name;
        this.collidable = gameObject.collidable;
        GameObject.objectCount++;
        this.id = GameObject.objectCount;
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

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public static void setObjectCount(Integer count)
    {
        if (GameObject.objectCount < count)
        {
            GameObject.objectCount = count;
        }
    }
}
