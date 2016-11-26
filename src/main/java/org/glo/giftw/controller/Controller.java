package org.glo.giftw.controller;

import org.glo.giftw.domain.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Controller
{
    public static final double COORDINATE_CONVERSION_RATIO = 1.0; //mm par pixel
    private SportPool sportPool;
    private ObstaclePool obstaclePool;
    private StrategyPool strategyPool;

    private Strategy currentStrategy;

    private static Controller INSTANCE = null;

    protected Controller()
    {
        super();
        this.sportPool = new SportPool();
        this.obstaclePool = new ObstaclePool();
        this.strategyPool = new StrategyPool();
        this.currentStrategy = null;
    }

    public static Controller getInstance()
    {
        if (Controller.INSTANCE == null)
        {
            Controller.INSTANCE = new Controller();
        }
        return Controller.INSTANCE;
    }

    /**
     * Crée un nouveau sport et le sauvegarde dans SportPool.
     *
     * @param name                Le nom du sport.
     * @param roles               La liste des rôles associés au sport.
     * @param fieldLength         La largeur du terrain (composante x).
     * @param fieldHeight         La hauteur du terrain (composante y).
     * @param projectileName      Le nom du projectile du sport.
     * @param projectileImagePath Le chemin vers l'image associé au projectile.
     * @param maxPLayersPerTeam   Le nombre maximum de joueurs par équipe sur le terrain à un moment donné.
     * @param maxTeams            Le nombre maximum d'équipe lors d'une partie.
     */
    public void createSport(String name, List<String> roles, int fieldLength, int fieldHeight, String fieldImagePath,
                            String projectileName, String projectileImagePath, int maxPLayersPerTeam, int maxTeams)
    {
        Vector fieldDimension = new Vector(fieldLength / COORDINATE_CONVERSION_RATIO,
                                           fieldHeight / COORDINATE_CONVERSION_RATIO);
        this.sportPool.addSport(name, roles, fieldDimension, fieldImagePath, projectileName,
                                projectileImagePath, maxPLayersPerTeam, maxTeams);
    }

    /**
     * Crée un nouveau type d'obstacle et le sauvegarde dans Obstacle Pool.
     *
     * @param name      Le nom de l'obstacle.
     * @param imagePath Le chemin vers l'image associé à l'obstacle.
     */
    public void createObstacle(String name, boolean isCollidable, String imagePath)
    {
        this.obstaclePool.addObstacleType(name, isCollidable, imagePath);
    }

    /**
     * Crée une nouvelle stratégie.
     *
     * @param name      Le nom de la nouvelle stratédie.
     * @param sportName Le nom du sport (déjà existant) associé à la stratégie.
     */
    public void createStrategy(String name, String sportName, boolean activateMaxNumberPlayer, boolean activateMaxNumberTeam)
    {
        Sport strategySport = this.sportPool.getSportByName(sportName);
        this.currentStrategy = this.strategyPool.addStrategy(name, strategySport, activateMaxNumberPlayer, activateMaxNumberTeam);
    }

    /**
     * Crée un nouveau joueur dans la stratégie et l'ajoute dans la frame courante.
     *
     * @param position    La position initiale du joueur.
     * @param orientation L'orientation initiale du joueur.
     * @param dimensions  Les dimensions initiales du joueur.
     * @return L'id du joueur nouvellement créé.
     */
    public Integer addPlayer(Vector position, float orientation, Vector dimensions, String team)
    {
        return this.currentStrategy.addPlayer(position, orientation, dimensions, team);
    }

    /**
     * Crée un nouvel obstacle dans la stratégie et l'ajoute dans la frame courante.
     *
     * @param position    La position initiale de l'obstacle.
     * @param orientation L'orientation initiale de l'obstacle.
     * @param dimensions  Les dimensions initiales de l'obstacle.
     * @return L'id de l'obstacle nouvellement créé.
     */
    public Integer addObstacle(String name, Vector position, float orientation, Vector dimensions)
    {
        Obstacle obstacle = this.obstaclePool.create(name);
        return this.currentStrategy.addObstacle(obstacle, position, orientation, dimensions);
    }

    /**
     * Crée un nouveau projectile dans la stratégie et l'ajoute dans la frame courante.
     *
     * @param position    La position initiale du projectile.
     * @param orientation L'orientation initiale du projectile.
     * @param dimensions  Les dimensions initiales du projectile.
     * @return L'id du projectile nouvellement créé.
     */
    public Integer addProjectile(Vector position, float orientation, Vector dimensions)
    {
        return this.currentStrategy.addProjectile(position, orientation, dimensions);
    }

    public GameObject getGameObjectByCoordinate(Vector adjustedMouseCoordinate, float zoomLevel)
    {
        Vector coordinate = this.currentStrategy.getFieldCoordinate(adjustedMouseCoordinate, zoomLevel);
        return currentStrategy.getGameObjectByCoordinate(coordinate);
    }

    public void placeGameObject(GameObject gameObject, Vector position, float orientation, Vector dimensions)
    {
        this.currentStrategy.placeGameObject(gameObject, position, orientation, dimensions);
    }

    /**
     * Fourni une description des sports pour le menu de sélection d'un sport de la vue.
     *
     * @return Une hashmap associant le nom des sports aux chemins des image de son terrain.
     */
    public HashMap<String, String> getSportDescription()
    {
        return this.sportPool.getSportsDescription();
    }

    public Collection<Obstacle> getObstacles()
    {
        return this.obstaclePool.getAllObstacles();
    }

    public Collection<Sport> getSports()
    {
        return this.sportPool.getAllSports();
    }

    public Collection<Strategy> getStrategies()
    {
        return this.strategyPool.getAllStrategies();
    }

    public String getPlayerTeam(Player player){
        return this.currentStrategy.getPlayerTeam(player);
    }

    public void setCheckMaxNumberTeam(boolean checkMaxNumberTeam)
    {
        currentStrategy.setCheckMaxNumberTeam(checkMaxNumberTeam);
    }

    public void setCheckMaxNumberPlayer(boolean checkMaxNumberPlayer)
    {
        currentStrategy.setCheckMaxNumberPlayer(checkMaxNumberPlayer);
    }

    public Vector getRealCoordinate(Vector adjustedCoordinate, float zoomLevel)
    {
        Vector fieldCoord = this.currentStrategy.getFieldCoordinate(adjustedCoordinate, zoomLevel);
        return this.convertToRealCoordinate(fieldCoord, COORDINATE_CONVERSION_RATIO);
    }

    public Vector convertToRealCoordinate(Vector fieldCoord, double ratio)
    {
        if (fieldCoord != null)
        {
            fieldCoord.mul(ratio);
        }
        return fieldCoord;
    }

    /**
     * Retourne la frame courante de la stratégie.
     *
     * @return la frame courante.
     */
    public Frame getCurrentFrame()
    {
        return this.currentStrategy.getCurrentFrame();
    }

    /**
     * Retourne la frame précédente de la stratégie.
     *
     * @return La frame précédente.
     */
    public Frame previousFrame()
    {
        return this.currentStrategy.previousFrame();
    }

    /**
     * Retourne la frame suivante de la stratégie.
     *
     * @return La frame suivante.
     */
    public Frame nextFrame()
    {
        return this.currentStrategy.nextFrame();
    }

    /**
     * Crée une nouvelle frame à la fin de la suite de frames.
     *
     * @return La nouvelle frame.
     */
    public Frame createNewFrame()
    {
        this.currentStrategy.goToEnd();
        this.currentStrategy.createNewFrame();
        return this.currentStrategy.nextFrame();
    }

    /**
     * Permet de modifier la frame courante de la stratégie selon un delta de temps, en secondes, précis auxs dixième de
     * secondes.
     *
     * @param delta La longueur du saut entre les frames, en secondes.
     * @return La nouvelle frame courante.
     */
    public Frame changeCurrentFrame(float delta)
    {
        this.currentStrategy.changeCurrentFrame(delta);
        return this.currentStrategy.getCurrentFrame();
    }
}
