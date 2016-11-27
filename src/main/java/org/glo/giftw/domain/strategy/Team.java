package org.glo.giftw.domain.strategy;

import org.glo.giftw.domain.exceptions.MaxNumberException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Team implements Serializable
{
    public final static long serialVersionUID = 0L;

    private Set<Player> players;
    private int maxNumberOfPlayers;
    private boolean checkMaxNumberOfPlayers;
    private String colour;

    public Team(int maxNumberOfPlayers, boolean checkMaxNumberOfPlayers, String colour)
    {
        this.players = new HashSet<>();
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.checkMaxNumberOfPlayers = checkMaxNumberOfPlayers;
        this.colour = colour;
    }

    public Team(int maxNumberOfPlayers, boolean checkMaxNumberOfPlayers, String color, Set<Player> players)
    {
        this(maxNumberOfPlayers, checkMaxNumberOfPlayers, color);
        this.players = new HashSet<>(players);
    }

    public void addPlayer(Player player) throws MaxNumberException
    {
        if (!this.checkMaxNumberOfPlayers || this.players.size() < maxNumberOfPlayers)
        {
            this.players.add(player);
        }
        else
        {
            throw new MaxNumberException(
                    String.format("L'equipe contient le nombre maximal de joueur %d", this.maxNumberOfPlayers));
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

    public String getColour()
    {
        return colour;
    }

    public void setColour(String colour)
    {
        this.colour = colour;
    }
}
