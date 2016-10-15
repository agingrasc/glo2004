package org.glo.giftw.domain;

import java.util.List;
import java.util.ArrayList;
import org.glo.giftw.domain.Player;
import org.glo.giftw.domain.Obstacle;
import org.glo.giftw.domain.Projectile;

public class Frame
{
    public static final int MAX_NUMBER_PLAYERS = 6;
    private ArrayList<Player> players;
    private ArrayList<Obstacle> obstacles;
    private Projectile projectile;

    public Frame()
    {
        this.players = new ArrayList<Player>(MAX_NUMBER_PLAYERS);
        this.obstacles = new ArrayList<Obstacle>();
        this.projectile = new Projectile();
    }

    public Frame(List<Obstacle> obstacles)
    {
        this();
        for (Obstacle obs : obstacles)
        {
            addObstacle(obs);
        }
    }

    public void addPlayer(Player player)
    {
        Player nPlayer = new Player(player);
        this.players.add(nPlayer);
    }

    public void addObstacle(Obstacle obstacle)
    {
        Obstacle nObstacle = new Obstacle(obstacle);
        this.obstacles.add(nObstacle);
    }

    public void placeProjectile(Projectile proj)
    {
        this.projectile = new Projectile(proj);
    }
}
