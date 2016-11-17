package org.glo.giftw.controller;

import java.util.List;
import java.util.HashMap;

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
    public void createSport(String name, List<String> roles, int fieldLength, int fieldHeight, String projectileName,
            String projectileImagePath, int maxPLayersPerTeam, int maxTeams)
    {
        this.sportPool.addSport(name, roles, new Vector(fieldLength, fieldHeight), projectileName, projectileImagePath,
                maxPLayersPerTeam, maxTeams);
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

    public void createStrategy(String name, String sportName)
    {
        Sport strategySport = this.sportPool.getSportByName(sportName);
        this.currentStrategy = new Strategy(name, strategySport);
    }

    //FIXME : utiliser les listes de la classe Stratégie pour stocker les instances des GameObjects temporaires
    //FIXME : positionner les GameObjects dans la frame courante avec la méthode frame.placeGameObject
    public Integer addPlayer(Vector position, float orientation, Vector dimensions)
    {
        Integer id = this.playersPool.addPlayer(position, orientation, dimensions);
        Frame currentFrame = this.currentStrategy.getFrame(this.currentFrameIdx);
        currentFrame.addGameObject(this.playersPool.getPlayer(id));
        return id;
    }

    public Integer addObstacle(Vector position, float orientation, Vector dimensions)
    {
        Obstacle obstacle = new Obstacle(position, orientation, dimensions);
        Integer id = obstacle.getId();
        this.obstacles.put(id, obstacle);
        return id;
    }

    public Integer addProjectile(Vector position, float orientation, Vector dimensions)
    {
        Projectile projectile = new Projectile(position, orientation, dimensions);
        Integer id = projectile.getId();
        this.projectiles.put(id, projectile);
        return id;
    }
    
    public void placeGameObject(Integer id, Vector position, float orientation, Vector dimensions)
    {
        Frame currentFrame = this.currentStrategy.getFrame(this.currentFrameIdx);
        currentFrame.placeGameObject(id, position, orientation, dimensions);
    }
}
