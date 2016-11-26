package org.glo.giftw.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Team implements Serializable
{
    public final static long serialVersionUUID = 0L;

    private Set<Player> players;
    private int maxNumberOfPlayers;
    private boolean checkMaxNumberOfPlayers;

    public Team(int maxNumberOfPlayers, boolean checkMaxNumberOfPlayers)
    {
        this.players = new HashSet<>();
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.checkMaxNumberOfPlayers = checkMaxNumberOfPlayers;
    }

    public Team(int maxNumberOfPlayers, boolean checkMaxNumberOfPlayers, Set<Player> players)
    {
        this(maxNumberOfPlayers, checkMaxNumberOfPlayers);
        this.players = new HashSet<>(players);
    }

    public void addPlayer(Player player)
    {
        if (!this.checkMaxNumberOfPlayers || this.players.size() < maxNumberOfPlayers)
        {
            this.players.add(player);
        }
    }

    public void removePlayer(Player player)
    {
        this.players.remove(player);
    }

    public List<Player> getPlayers()
    {
        return new ArrayList<>(this.players);
    }


    public boolean isPlayerInTeam(Player player)
    {
        return this.players.contains(player);
    }

    public void setCheckMaxNumberOfPlayers(boolean checkMaxNumberOfPlayers)
    {
        this.checkMaxNumberOfPlayers = checkMaxNumberOfPlayers;
    }
}
