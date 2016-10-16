package org.glo.giftw.domain;

import java.util.HashMap;
import java.io.Serializable;

public class Frame implements Serializable
{
    public static final long serialVersionUID = 1L;
    
    private HashMap<Integer, GameObject> gameObjects;

    public Frame()
    {
        this.gameObjects = new HashMap<Integer, GameObject>();
    }
    
    public Frame(Frame frame)
    {
        this.gameObjects = new HashMap<Integer, GameObject>();
        frame.gameObjects.forEach((id, gameObject) -> this.addGameObject(gameObject));
    }

    public void addGameObject(GameObject gameObject)
    {
        this.gameObjects.put(new Integer(gameObject.getId()), gameObject.copy());
    }
    
    public void removeGameObject(int id)
    {
        this.gameObjects.remove(new Integer(id));
    }
    
    public void placeGameObject(int id, Vector position, float orientation, Vector scale)
    {
        GameObject gameObject = this.gameObjects.get(new Integer(id));
        gameObject.setPosition(position);
        gameObject.setOrientation(orientation);
        gameObject.setScale(scale);
    }
    
    //TODO: ajouter m�thode de d�tection de collisions
    /*
    public X detectCollisions(GameObject gameObject)
    {
        
    }
    
    public X detectCollisions()
    {
        
    }
    */
}
