package org.glo.giftw.domain.strategy;

import org.glo.giftw.domain.util.Vector;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Frame implements Serializable
{
    public static final long serialVersionUID = 1L;
    private final HashMap<GameObject, GameObjectState> gameObjects; // associe un GameObject avec son état
    private boolean isKeyFrame;

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
        frame.gameObjects.forEach((gameObject, gameObjectState)
                                          -> this.addGameObject(gameObject, new GameObjectState(gameObjectState)));
    }

    public void addGameObject(GameObject gameObject, GameObjectState gameObjectState)
    {
        this.gameObjects.put(gameObject, gameObjectState);
    }

    public boolean isKeyFrame()
    {
        return isKeyFrame;
    }

    public void setKeyFrame(boolean isKeyFrame)
    {
        this.isKeyFrame = isKeyFrame;
    }

    public boolean collide(Vector position, GameObject gameObject)
    {
        GameObjectState gameObjectState = new GameObjectState(position, 0, gameObject.getDimensions());
        for (Map.Entry<GameObject, GameObjectState> entry : gameObjects.entrySet())
        {
            GameObject other = entry.getKey();
            if (gameObject.getId() != other.getId() && gameObject.isCollidable() && other.isCollidable() && gameObjectState.detectCollision(
                    entry.getValue()))
            {
                return true;
            }
        }
        return false;
    }

    public void removeGameObject(GameObject gameObject)
    {
        this.gameObjects.remove(gameObject);
    }

    public void placeGameObject(GameObject gameObject, Vector position, float orientation)
    {
        GameObjectState gameObjectState = this.gameObjects.get(gameObject);
        gameObjectState.setPosition(position);
        gameObjectState.setOrientation(orientation);
    }

    public Set<GameObject> detectCollisionsIgnoreCollidable(GameObject reference)
    {
        GameObjectState referenceState = this.gameObjects.get(reference);
        Set<GameObject> GameObjectsInCollision = new HashSet<>();
        for (Map.Entry<GameObject, GameObjectState> entry : gameObjects.entrySet())
        {
            GameObject other = entry.getKey();
            if (reference.getId() != other.getId())
            {
                if (referenceState.detectCollision(entry.getValue()))
                {
                    GameObjectsInCollision.add(other);
                }
            }
        }

        return GameObjectsInCollision;
    }

    /**
     * Detects collisions between all the GameObjects of the frame.
     *
     * @return A HashMap mapping the id of every GameObjects of the frame with an ArrayList of Integers containing the
     * id of the colliding GameObjects. Those ArrayLists may be empty.
     */
    public HashMap<GameObject, Set<GameObject>> detectCollisions()
    {
        HashMap<GameObject, Set<GameObject>> collisions = new HashMap<>();
        for (Map.Entry<GameObject, GameObjectState> entry : gameObjects.entrySet())
        {
            collisions.put(entry.getKey(), this.detectCollisions(entry.getKey()));
        }
        return collisions;
    }

    /**
     * Detects collisions between a specified GameObject and all the other GameObjects of the frame.
     * A GameObject does not generate a collision with himself.
     *
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
                if (referenceState.detectCollision(entry.getValue()))
                {
                    GameObjectsInCollision.add(other);
                }
            }
        }

        return GameObjectsInCollision;
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

    public GameObject getGameObjectByCoordinate(Vector coordinate)
    {
        for (Map.Entry<GameObject, GameObjectState> entry : gameObjects.entrySet())
        {
            if (entry.getValue().occupiesPosition(coordinate))
            {
                return entry.getKey();
            }
        }
        return null;
    }

    public Vector getPosition(GameObject gameObject) throws GameObjectNotFound
    {
        GameObjectState gos = this.gameObjects.get(gameObject);
        if (gos == null)
        {
            throw new GameObjectNotFound("Le GameObject n'a pas encore été placé dans la frame!");
        }
        return gos.getPosition();
    }

    public float getOrientation(GameObject gameObject) throws GameObjectNotFound
    {
        GameObjectState gos = this.gameObjects.get(gameObject);
        if (gos == null)
        {
            throw new GameObjectNotFound("Le GameObject n'a pas encore été placé dans la frame!");
        }
        return gos.getOrientation();
    }

    public Set<GameObject> getGameObjects()
    {
        return this.gameObjects.keySet();
    }
}
