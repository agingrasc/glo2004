package org.glo.giftw.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.stream.Collectors;

public class Frame implements Serializable
{
    public static final long serialVersionUID = 1L;
    
    private HashMap<Integer, GameObject> gameObjects;

    public Frame()
    {
        this.gameObjects = new HashMap<>();
    }
    
    public Frame(Frame frame)
    {
        this.gameObjects = new HashMap<>();
        frame.gameObjects.forEach((id, gameObject) -> this.addGameObject(gameObject));
    }

    public void addGameObject(GameObject gameObject)
    {
        this.gameObjects.put(gameObject.getId(), gameObject.copy());
    }
    
    public void removeGameObject(int id)
    {
        this.gameObjects.remove(id);
    }
    
    public void placeGameObject(int id, Vector position, float orientation, Vector scale)
    {
        //on copie l'objet pour pouvoir modifier sa position
        GameObject gameObject = this.gameObjects.get(id).copy();
        gameObject.setPosition(position);
        gameObject.setOrientation(orientation);
        gameObject.setScale(scale);
        //on remplace l'ancienne copie par la nouvelle
        this.gameObjects.put(id, gameObject);
    }
    
    /**
     * Detects collisions between a specified GameObject and all the other GameObjects of the frame.
     * A GameObject does not generate a collision with himself.
     * @param gameObject : The GameObject generating the collisions. 
     * @return An ArrayList of Integers containing the id of the colliding GameObjects (can be empty).
     */
    public ArrayList<Integer> detectCollisions(GameObject gameObject)
    {
        return gameObjects.entrySet().stream().filter(
                entry -> entry.getKey() != gameObject.id && gameObject.detectCollision(
                        entry.getValue())).map(Map.Entry::getKey).collect(Collectors.toCollection(ArrayList::new));
    }
    
    /**
     * Detects collisions between all the GameObjects of the frame.
     * @return A HashMap mapping the id of every GameObjects of the frame with an ArrayList of Integers containing the
     * id of the colliding GameObjects. Those ArrayLists may be empty.
     */
    public HashMap<Integer, ArrayList<Integer>> detectCollisions()
    {
        HashMap<Integer, ArrayList<Integer>> collisions = new HashMap<>();
        for(Map.Entry<Integer, GameObject> entry : gameObjects.entrySet())
        {
            collisions.put(entry.getKey(), this.detectCollisions(entry.getValue()));
        }
        return collisions;
    }

    @Override
    public String toString()
    {
        String repr = "Frame: \n";

        for (GameObject go : this.gameObjects.values())
        {
            repr += go.toString() + "\n";
        }

        return repr;
    }
}
