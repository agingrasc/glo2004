package org.glo.giftw.domain.strategy;

import java.io.Serializable;
import java.util.*;

public class Frame implements Serializable
{
    public static final long serialVersionUID = 1L;
    
    private boolean isKeyFrame;
    private final HashMap<GameObject, GameObjectState> gameObjects; // associe un GameObject avec son état

    public Frame()
    {
        this.isKeyFrame = false;
        this.gameObjects = new HashMap<>();
    }
    
    public Frame(boolean isKeyFrame)
    {
        this.isKeyFrame = isKeyFrame;
        this.gameObjects = new HashMap<>();
    }
    
    public Frame(Frame frame)
    {
        this.isKeyFrame = frame.isKeyFrame;
        this.gameObjects = new HashMap<>();
        frame.gameObjects.forEach((gameObject, gameObjectState) -> this.addGameObject(gameObject,
                new GameObjectState(gameObjectState)));
    }

    public boolean isKeyFrame()
    {
        return isKeyFrame;
    }

    public void setKeyFrame(boolean isKeyFrame)
    {
        this.isKeyFrame = isKeyFrame;
    }

    public void addGameObject(GameObject gameObject, GameObjectState gameObjectState)
    {
        this.gameObjects.put(gameObject, gameObjectState);
    }
    
    public void removeGameObject(GameObject gameObject)
    {
        this.gameObjects.remove(gameObject);
    }
    
    public void placeGameObject(GameObject gameObject, org.glo.giftw.domain.util.Vector position, float orientation, org.glo.giftw.domain.util.Vector dimensions)
    {
        GameObjectState gameObjectState = this.gameObjects.get(gameObject);
        gameObjectState.setPosition(position);
        gameObjectState.setOrientation(orientation);
        gameObjectState.setDimensions(dimensions);
    }
    
    /**
     * Detects collisions between a specified GameObject and all the other GameObjects of the frame.
     * A GameObject does not generate a collision with himself.
     * @param reference : The reference GameObject generating the collisions. 
     * @return An ArrayList of GameObjects colliding with the reference GameObjects (can be empty).
     */
    public Set<GameObject> detectCollisions(GameObject reference)
    {
        GameObjectState referenceState = this.gameObjects.get(reference);
        Set<GameObject> GameObjectsInCollision = new HashSet<>();
        for (Map.Entry<GameObject, GameObjectState> entry : gameObjects.entrySet())
        {
            GameObject other = entry.getKey();
            if (reference.getId() != other.getId() && reference.isCollidable() && other.isCollidable())
            {
                if(referenceState.detectCollision(entry.getValue()))
                {
                    GameObjectsInCollision.add(other);
                }
            }
        }

        return GameObjectsInCollision;
    }
    
    /**
     * Detects collisions between all the GameObjects of the frame.
     * @return A HashMap mapping the id of every GameObjects of the frame with an ArrayList of Integers containing the
     * id of the colliding GameObjects. Those ArrayLists may be empty.
     */
    public HashMap<GameObject, Set<GameObject>> detectCollisions()
    {
        HashMap<GameObject, Set<GameObject>> collisions = new HashMap<>();
        for(Map.Entry<GameObject, GameObjectState> entry : gameObjects.entrySet())
        {
            collisions.put(entry.getKey(), this.detectCollisions(entry.getKey()));
        }
        return collisions;
    }

    @Override
    public String toString()
    {
        String repr = "Frame: \n";

        for (Map.Entry<GameObject, GameObjectState> entry : gameObjects.entrySet())
        {
            repr += entry.getKey().toString() + "\n" + entry.getValue().toString() + "\n";
        }

        return repr;
    }
    
    public GameObject getGameObjectByCoordinate(org.glo.giftw.domain.util.Vector coordinate)
    {
        for(Map.Entry<GameObject, GameObjectState> entry : gameObjects.entrySet())
        {
            if(entry.getValue().occupiesPosition(coordinate))
            {
                return entry.getKey();
            }
        }
        return null;
    }
    
    public org.glo.giftw.domain.util.Vector getPosition(GameObject gameObject)
    {
        return this.gameObjects.get(gameObject).getPosition();
    }
    
    public float getOrientation(GameObject gameObject)
    {
        return this.gameObjects.get(gameObject).getOrientation();
    }
    
    public org.glo.giftw.domain.util.Vector getDimensions(GameObject gameObject)
    {
        return this.gameObjects.get(gameObject).getDimensions();
    }
    
    public Set<GameObject> getGameObjects()
    {
        return this.gameObjects.keySet();
    }
}
