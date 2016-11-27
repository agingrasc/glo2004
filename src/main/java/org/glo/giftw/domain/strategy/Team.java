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

    private String name;
    private String colour;
    private Set<Player> players;
    private int maxNumberOfPlayers;
    private boolean checkMaxNumberOfPlayers;

    public Team(String name, String colour, int maxNumberOfPlayers, boolean checkMaxNumberOfPlayers)
    {
        this.name = name;
        this.colour = colour;
        this.players = new HashSet<>();
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.checkMaxNumberOfPlayers = checkMaxNumberOfPlayers;
    }

    public Team(String name, String colour, int maxNumberOfPlayers, boolean checkMaxNumberOfPlayers, Set<Player> players)
    {
        this(name, colour, maxNumberOfPlayers, checkMaxNumberOfPlayers);
        this.players = new HashSet<>(players);
    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getColour()
    {
        return colour;
    }

    public void setColour(String colour)
    {
        this.colour = colour;
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
}
