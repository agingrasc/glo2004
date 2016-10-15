package org.glo.giftw.domain;

import java.util.HashMap;

import org.glo.giftw.domain.Player;

public class PlayerPool
{
    public static final String PLAYER_POOL_PATH = "./data/player_pool.ser";

    private HashMap<Integer, Player> players;

    public PlayerPool()
    {
        //load logic
        this.loadPlayerPool(PLAYER_POOL_PATH);
    }

    public Integer addPlayer(Vector scale)
    {
        Player player = new Player(scale);
        this.players.put(player.getId(), player);
        return player.getId();
    }

    public void setPlayerInfo(Integer id, String name, int number, String role)
    {
        Player player = this.players.get(id);
        player.setName(name);
        player.setJerseyNumber(number);
        player.setRole(role);
    }

    public Player getPlayer(Integer id)
    {
        return this.players.get(id);
    }

    private void loadPlayerPool(String poolPath)
    {
        //load logic
    }

    private void savePlayerPool(String poolPaht)
    {
        //save logic
    }
}
