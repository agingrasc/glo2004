package org.glo.giftw.domain;

import java.util.List;
import java.util.ArrayList;
import org.glo.giftw.domain.Player;
import org.glo.giftw.domain.Obstacle;
import org.glo.giftw.domain.Projectile;

public class Frame
{
	//remplacer 3 arraylist par hashmap de gameobject
    private ArrayList<Player> players;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Projectile> projectiles;

    public Frame()
    {
        this.players = new ArrayList<Player>();
        this.obstacles = new ArrayList<Obstacle>();
        this.projectiles = new ArrayList<Projectile>();
    }
    
    public Frame(Frame frame)
    {
    	//for each de chacun des gameobjects du hashmap pour deep copy
    	this.players = frame.players;
    	this.obstacles = frame.obstacles;
    	this.projectiles = frame.projectiles;
    }

    //ajouter list<player>
    public Frame(List<Obstacle> obstacles, List<Projectile> projectiles)
    {
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
    
    //TODO: ajouter méthode de détection de collisions
    //TODO: ajouter méthode placeGameObject(GameObject) qui va modifier la position de son GameObject ayant le même id
}
