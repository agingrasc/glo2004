package org.glo.giftw.domain;

import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.exceptions.MaxNumberException;
import org.glo.giftw.domain.exceptions.StrategyNotFound;
import org.glo.giftw.domain.exceptions.TeamNotFound;
import org.glo.giftw.domain.pool.ObstaclePool;
import org.glo.giftw.domain.pool.SportPool;
import org.glo.giftw.domain.pool.StrategyPool;
import org.glo.giftw.domain.strategy.*;
import org.glo.giftw.domain.util.Vector;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Controller
{
    public static final double COORDINATE_CONVERSION_RATIO = 1.0; //mm par pixel
    private static Controller INSTANCE = null;
    protected Strategy currentStrategy;
    private SportPool sportPool;
    private ObstaclePool obstaclePool;
    private StrategyPool strategyPool;

    protected Controller()
    {
        super();
        this.sportPool = new SportPool();
        this.obstaclePool = new ObstaclePool();
        this.strategyPool = new StrategyPool();
        this.currentStrategy = new NullStrategy();
    }

    public static Controller getInstance()
    {
        if (Controller.INSTANCE == null)
        {
            Controller.INSTANCE = new Controller();
        }
        return Controller.INSTANCE;
    }

    public boolean isLastFrame()
    {
        return currentStrategy.isLastFrame();
    }

    public boolean isFirstFrame()
    {
        return currentStrategy.isFirstFrame();
    }

    public void goToBeginning()
    {
        currentStrategy.goToBeginning();
    }

    public void goToEnd()
    {
        currentStrategy.goToEnd();
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
    public void createStrategy(String name, String sportName, boolean activateMaxNumberPlayer,
                               boolean activateMaxNumberTeam)
    {
        Sport strategySport = this.sportPool.getSportByName(sportName);
        this.currentStrategy = this.strategyPool.addStrategy(name, strategySport, activateMaxNumberPlayer,
                                                             activateMaxNumberTeam);
    }

    /**
     * Crée un nouveau joueur dans la stratégie et l'ajoute à l'équipe spécifiée.
     *
     * @param team  L'équipe du joueur.
     * @return L'id du joueur nouvellement créé.
     */
    public String addPlayer(String team) throws TeamNotFound, MaxNumberException
    {
        return this.currentStrategy.addPlayer(team);
    }

    /**
     * Crée un nouvel obstacle dans la stratégie.
     *
     * @param name  Le nom du type d'obstacle.
     * @return L'id de l'obstacle nouvellement créé.
     */
    public String addObstacle(String name)
    {
        Obstacle obstacle = this.obstaclePool.create(name);
        return this.currentStrategy.addObstacle(obstacle);
    }

    /**
     * Crée un nouveau projectile dans la stratégie.
     *
     * @return L'id du projectile nouvellement créé.
     */
    public String addProjectile()
    {
        return this.currentStrategy.addProjectile();
    }

    public void addTeam(String teamName, String colour) throws MaxNumberException
    {
        this.currentStrategy.addTeam(teamName, colour);
    }

    public String getTeamColour(String teamName)
    {
        return this.currentStrategy.getTeamColour(teamName);
    }

    public void setTeamColour(String teamName, String colour)
    {
        this.currentStrategy.setTeamColour(teamName, colour);
    }

    public void setPixelToUnitRatio(Vector ratio)
    {
        this.currentStrategy.setPixelToUnitRatio(ratio);
    }

    public String getGameObjectByCoordinate(Vector adjustedMouseCoordinate) throws GameObjectNotFound
    {
        Vector coordinate = this.getFieldCoordinate(adjustedMouseCoordinate);
        GameObject gameObject = currentStrategy.getGameObjectByCoordinate(coordinate);
        if (gameObject == null)
        {
            throw new GameObjectNotFound("Aucun gameObject au coordonnee: " + adjustedMouseCoordinate.toString());
        }
        else
        {
            return gameObject.getId();
        }
    }

    public Vector getFieldCoordinate(Vector adjustedCoordinate)
    {
        return this.currentStrategy.getFieldCoordinate(adjustedCoordinate);
    }

    public GameObject getGameObjectByUUID(String uuid) throws GameObjectNotFound
    {
        return this.currentStrategy.getGameObjectByUUID(uuid);
    }

    /**
     * Place un GameObject dans la frame courante. Si le GameObject est déjà présent, son état est mis à jour.
     * @param gameObjectUuid Le uuid du GameObject.
     * @param position       La position du GameObject.
     * @param orientation    L'orientation du GameObject.
     * @param dimensions     Les dimensions du GameObject.
     * @throws GameObjectNotFound
     */
    public void placeGameObject(String gameObjectUuid, Vector position, float orientation,
                                Vector dimensions) throws GameObjectNotFound
    {
        this.currentStrategy.placeGameObject(gameObjectUuid, position, orientation, dimensions);
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

    public String getSportFieldImagePath()
    {
        return this.currentStrategy.getFieldImagePath();
    }

    public Collection<Sport> getSports()
    {
        return this.sportPool.getAllSports();
    }

    public String getStrategyName()
    {
        if (this.currentStrategy != null)
        {
            return this.currentStrategy.getName();
        }
        else
        {
            return null;
        }
    }

    public Collection<Strategy> getStrategies()
    {
        return this.strategyPool.getAllStrategies();
    }

    public String getPlayerTeam(GameObject player)
    {
        return this.currentStrategy.getPlayerTeam((Player) player);
    }

    public void openStrategy(String strategyName) throws StrategyNotFound
    {
        Strategy strategy = this.strategyPool.getStrategy(strategyName);
        this.currentStrategy = strategy;
    }

    public void setCheckMaxNumberTeam(boolean checkMaxNumberTeam)
    {
        this.currentStrategy.setCheckMaxNumberTeam(checkMaxNumberTeam);
    }

    public void setCheckMaxNumberPlayer(boolean checkMaxNumberPlayer)
    {
        this.currentStrategy.setCheckMaxNumberPlayer(checkMaxNumberPlayer);
    }

    public Vector getFieldDimensions()
    {
        return currentStrategy.getFieldDimensions();
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
     * Crée une nouvelle key frame, précédée du bon nombre de subFrames, à la fin de la suite de frames.
     *
     * @return La nouvelle key frame.
     */
    public Frame createNewFrame()
    {
        this.currentStrategy.createNewFrame();
        this.currentStrategy.goToEnd();
        return this.currentStrategy.getCurrentFrame();
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

    public Collection<Team> getTeams()
    {
        return this.currentStrategy.getTeams();
    }

    public Projectile getProjectile()
    {
        return this.currentStrategy.getProjectile();
    }

    public Collection<Obstacle> getObstacles()
    {
        return this.obstaclePool.getAllObstacles();
    }

    public void saveStrategies()
    {
        this.strategyPool.save();
    }
}
