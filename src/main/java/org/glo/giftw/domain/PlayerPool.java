package org.glo.giftw.domain;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;

public class PlayerPool implements Serializable
{
    public static final long serialVersionUID = 1L;
    public static final String PLAYER_POOL_PATH = "./data/player_pool.ser";

    private HashMap<Integer, Player> players;

    public PlayerPool()
    {
        //load logic
        players = new HashMap<>();
        this.loadPlayerPool(PLAYER_POOL_PATH);
        Integer objCount = this.findHighestId();
        GameObject.setObjectCount(objCount);
    }

    public PlayerPool(boolean persistent)
    {
        //FIXME: considere reellement le bool
        players = new HashMap<>();
        Integer objCount = 0;
        GameObject.setObjectCount(objCount);
    }

    public Integer addPlayer(Vector position, float orientation, Vector dimensions)
    {
        return this.addPlayer(position, orientation, dimensions, true);
    }

    public Integer addPlayer(Vector position, float orientation, Vector dimensions, boolean persistent)
    {
        Player player = new Player(position, orientation, dimensions);
        this.players.put(player.getId(), player);
        if(persistent)
        {
            this.savePlayerPool(PLAYER_POOL_PATH);
        }
        return player.getId();
    }
    
    public void deletePlayer(Integer id)
    {
        this.deletePlayer(id, true);
    }
    
    public void deletePlayer(Integer id, boolean persistent)
    {
        this.players.remove(id);
        if(persistent)
        {
            this.savePlayerPool(PLAYER_POOL_PATH);
        }
    }

    public void setPlayerInfo(Integer id, String name, int number, String role)
    {
        Player player = this.players.get(id);
        player.setName(name);
        player.setJerseyNumber(number);
        player.setRole(role);
        this.savePlayerPool(PLAYER_POOL_PATH);
    }

    public Player getPlayer(Integer id)
    {
        return this.players.get(id);
    }

    public Collection<Player> getAllPlayer()
    {
        return this.players.values();
    }

    @Override
    public String toString()
    {
        String ret = "PlayerPool contient: " + this.players.size() + " joueurs\n";

        for (Player p: this.players.values())
        {
            ret += p.toString() + "\n";
        }

        return ret;
    }

    private void loadPlayerPool(String poolPath)
    {
        try (FileInputStream fileIn = new FileInputStream(poolPath); ObjectInputStream in = new ObjectInputStream(fileIn))
        {
            PlayerPool tmp = (PlayerPool) in.readObject();
            this.players = tmp.players;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("Il n'y a pas de fichier existant de PlayerPool, aucun chargement.");
        }
    }

    private void savePlayerPool(String poolPath)
    {
        File f = new File(poolPath);
        //noinspection ResultOfMethodCallIgnored
        f.getParentFile().mkdirs();
        try
        {
            //noinspection ResultOfMethodCallIgnored
            f.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try (FileOutputStream fileOut = new FileOutputStream(poolPath); ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            out.writeObject(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private Integer findHighestId()
    {
        Integer highestId = 0;
        for (Player p : this.players.values())
        {
           if (highestId < p.getId())
           {
               highestId = p.getId();
           }
        }

        return highestId;
    }
}
