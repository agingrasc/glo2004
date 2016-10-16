package org.glo.giftw.domain;

public abstract class GameObject
{
    private static int objectCount = 0;

    protected float orientation;
    protected Vector position;
    protected Vector scale;
    protected String name;
    protected boolean collidable;
    protected int id;

    public GameObject()
    {
        this.orientation = 0;
        this.position = new Vector(0, 0);
        this.scale = new Vector(1, 1);
        this.name = "";
        this.collidable = true;
        this.id = GameObject.objectCount;
        GameObject.objectCount++;
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
}
