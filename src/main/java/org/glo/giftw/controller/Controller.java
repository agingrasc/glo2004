package org.glo.giftw.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.glo.giftw.domain.*;

public class Controller
{
    private SportPool sportPool;
    private ObstaclePool obstaclePool;

    private Strategy currentStrategy;
    
    private static Controller INSTANCE = null;

    private Controller()
    {
        super();
        this.sportPool = new SportPool();
        this.obstaclePool = new ObstaclePool();
        this.currentStrategy = null;
    }
    
    public static Controller getInstance()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }
    
    /**
     * Crée un nouveau sport et le sauvegarde dans SportPool.
     * @param name Le nom du sport.
     * @param roles La liste des rôles associés au sport.
     * @param fieldLength La largeur du terrain (composante x).
     * @param fieldHeight La hauteur du terrain (composante y).
     * @param projectileName Le nom du projectile du sport.
     * @param projectileImagePath Le chemin vers l'image associé au projectile.
     * @param maxPLayersPerTeam Le nombre maximum de joueurs par équipe sur le terrain à un moment donné.
     * @param maxTeams Le nombre maximum d'équipe lors d'une partie.
     */
    public void createSport(String name, List<String> roles, int fieldLength, int fieldHeight, String fieldImagePath, 
            String projectileName, String projectileImagePath, int maxPLayersPerTeam, int maxTeams)
    {
        this.sportPool.addSport(name, roles, new Vector(fieldLength, fieldHeight), fieldImagePath, projectileName,
                projectileImagePath, maxPLayersPerTeam, maxTeams);
    }
    
    /**
     * Crée un nouveau type d'obstacle et le sauvegarde dans Obstacle Pool.
     * @param name Le nom de l'obstacle.
     * @param imagePath Le chemin vers l'image associé à l'obstacle.
     */
    public void createObstacle(String name, String imagePath)
    {
        this.obstaclePool.addObstacle(name, imagePath);
    }

    /**
     * Crée une nouvelle stratégie.
     * @param name Le nom de la nouvelle stratédie.
     * @param sportName Le nom du sport (déjà existant) associé à la stratégie.
     */
    public void createStrategy(String name, String sportName)
    {
        Sport strategySport = this.sportPool.getSportByName(sportName);
        this.currentStrategy = new Strategy(name, strategySport);
    }

    /**
     * Crée un nouveau joueur dans la stratégie et l'ajoute dans la frame courante.
     * @param position La position initiale du joueur.
     * @param orientation L'orientation initiale du joueur.
     * @param dimensions Les dimensions initiales du joueur.
     * @return L'id du joueur nouvellement créé.
     */
    public Integer addPlayer(Vector position, float orientation, Vector dimensions)
    {
        return this.currentStrategy.addPlayer(position, orientation, dimensions);
    }

    /**
     * Crée un nouvel obstacle dans la stratégie et l'ajoute dans la frame courante.
     * @param position La position initiale de l'obstacle.
     * @param orientation L'orientation initiale de l'obstacle.
     * @param dimensions Les dimensions initiales de l'obstacle.
     * @return L'id de l'obstacle nouvellement créé.
     */
    public Integer addObstacle(Vector position, float orientation, Vector dimensions)
    {
        return this.currentStrategy.addObstacle(position, orientation, dimensions);
    }

    /**
     * Crée un nouveau projectile dans la stratégie et l'ajoute dans la frame courante.
     * @param position La position initiale du projectile.
     * @param orientation L'orientation initiale du projectile.
     * @param dimensions Les dimensions initiales du projectile.
     * @return L'id du projectile nouvellement créé.
     */
    public Integer addProjectile(Vector position, float orientation, Vector dimensions)
    {
        return this.currentStrategy.addProjectile(position, orientation, dimensions);
    }
    
    public void placeGameObject(GameObject gameObject, Vector position, float orientation, Vector dimensions)
    {
        this.currentStrategy.placeGameObject(gameObject, position, orientation, dimensions);
    }
    
    /**
     * Fourni une description des sports pour le menu de sélection d'un sport de la vue.
     * @return Une hashmap associant le nom des sports aux chemins des image de son terrain.
     */
    public HashMap<String, String> getSportDescription()
    {
        return this.sportPool.getSportsDescription();
    }
    
    public HashMap<String, String> getObstacles()
    {
    	return this.obstaclePool.getObstacles();
    }
    
    public Collection<Sport> getSports()
    {
    	return this.sportPool.getAllSports();
    }
    
    //TODO Implementer ceci s.v.p
    /*public Collection<Strategy> getStrategies()
    {
    	return this.strategyPool.getAllStrategies();
    }*/
}
