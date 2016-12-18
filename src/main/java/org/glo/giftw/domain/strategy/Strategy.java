package org.glo.giftw.domain.strategy;

import org.glo.giftw.domain.TreeViewable;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.exceptions.MaxNumberException;
import org.glo.giftw.domain.exceptions.TeamNotFound;
import org.glo.giftw.domain.util.Vector;

import java.io.*;
import java.util.*;

/**
 * Contient les frames et les appels necessaires pour les joueurs
 */
public class Strategy implements Serializable, TreeViewable
{
    public static final long serialVersionUID = 2L;
    public static final String STRATEGY_PATH = "data/strategies";
    public static int FRAME_PER_SECOND = 30;
    public static int KEY_FRAME_PER_SECOND = 2;

    private String name;
    private Sport sport;
    private int currentFrameIdx;
    private HashMap<String, Team> teams; //Associe chaque équipe impliquée dans une strategie avec son nom
    private HashSet<GameObject> gameObjects; //Set contenant les instances des gameObjects de la stratégie
    private ArrayList<Frame> frames;
    private boolean checkMaxNumberPlayer;
    private boolean checkMaxNumberTeam;

    protected Strategy()
    {

    }

    public Strategy(String name, Sport sport, boolean checkMaxNumberPlayer, boolean checkMaxNumberTeam)
    {
        this.name = name;
        this.sport = sport;
        this.currentFrameIdx = 0;
        this.teams = new HashMap<>();
        this.gameObjects = new HashSet<>();
        this.frames = new ArrayList<>();
        this.frames.add(new Frame(true));
        this.checkMaxNumberPlayer = checkMaxNumberPlayer;
        this.checkMaxNumberTeam = checkMaxNumberTeam;
    }
    
    public Strategy(Strategy strategy)
    {
        this.name = strategy.name;
        this.sport = strategy.sport;
        this.currentFrameIdx = strategy.currentFrameIdx;
        this.teams = new HashMap<>();
        strategy.teams.forEach((name, team) -> this.teams.put(name, new Team(team)));
        this.gameObjects = new HashSet<>();
        for (GameObject gameObject : strategy.gameObjects)
        {
            this.gameObjects.add(gameObject.copy());
        }
        this.frames = new ArrayList<>();
        strategy.frames.forEach(frame -> this.frames.add(new Frame(frame)));
        this.checkMaxNumberPlayer = strategy.checkMaxNumberPlayer;
        this.checkMaxNumberTeam = strategy.checkMaxNumberTeam;
    }


    /*
     * Getters et setters
     */
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Sport getSport()
    {
        return this.sport;
    }

    public void setSport(Sport sport)
    {
        this.sport = sport;
    }

    public int getCurrentFrameIdx()
    {
        return currentFrameIdx;
    }

    public void setCurrentFrameIdx(int currentFrameIdx)
    {
        assert currentFrameIdx >= 0 && currentFrameIdx < this.frames.size();
        this.currentFrameIdx = currentFrameIdx;
    }

    public List<Frame> getFrames()
    {
        return this.frames;
    }

    public void setFrames(ArrayList<Frame> frames)
    {
        this.frames = frames;
    }

    public Frame getFrame(int frameId)
    {
        return this.frames.get(frameId);
    }

    public Collection<Team> getTeams()
    {
        return this.teams.values();
    }

    public List<Player> getTeamPlayers(String teamName)
    {
        return this.teams.get(teamName).getPlayers();
    }

    public String getPlayerTeam(Player player)
    {
        for (String teamName : this.teams.keySet())
        {
            if (this.teams.get(teamName).isPlayerInTeam(player))
            {
                return teamName;
            }
        }
        return null;
    }

    public Projectile getProjectile()
    {
        return this.sport.getProjectile();
    }

    public void setCheckMaxNumberTeam(boolean checkMaxNumberTeam)
    {
        this.checkMaxNumberTeam = checkMaxNumberTeam;
    }

    public void setCheckMaxNumberPlayer(boolean checkMaxNumberPlayer)
    {
        this.checkMaxNumberPlayer = checkMaxNumberPlayer;
        for (Team team : this.teams.values())
        {
            team.setCheckMaxNumberOfPlayers(checkMaxNumberPlayer);
        }
    }


    public Vector getFieldCoordinate(Vector adjustedCoordinate)
    {
        return this.sport.getFieldCoordinate(adjustedCoordinate);
    }

    //Logique des frames
    public void addFrame(int frameId, Frame frame)
    {
        this.frames.add(frameId, frame);
    }

    public void createNewFrame()
    {
        if (this.frames.isEmpty())
        {
            this.frames.add(new Frame(true));
        }
        else
        {
            Frame lastKeyFrame = this.frames.get(this.frames.size() - 1);

            //ajout des subFrames
            for (int i = 1; i < (Strategy.FRAME_PER_SECOND / Strategy.KEY_FRAME_PER_SECOND); i++)
            {
                Frame subFrame = new Frame(lastKeyFrame);
                subFrame.setKeyFrame(false);
                this.frames.add(subFrame);
            }

            this.frames.add(new Frame(lastKeyFrame));
        }
    }

    public boolean isLastFrame()
    {
        return this.currentFrameIdx == this.frames.size() - 1;
    }

    public boolean isFirstFrame()
    {
        return this.currentFrameIdx == 0;
    }

    public Frame getCurrentFrame()
    {
        return this.frames.get(this.currentFrameIdx);
    }

    /**
     * Fait reculer l'index de la frame courante, puis retourne la frame précédant la frame courrante.
     * Si la frame courante est la première frame, l'index reste inchangé et la première frame est retournée.
     *
     * @return La frame précédente.
     */
    public Frame previousFrame()
    {
        if (this.currentFrameIdx != 0)
        {
            this.currentFrameIdx--;
        }
        return this.frames.get(this.currentFrameIdx);
    }

    /**
     * Modifie l'index de la frame courante pour qu'il pointe sur la keyFrame précédente, puis retourne celle-ci.
     * Si la frame courante est la première frame, l'index reste inchangé et la première frame est retournée.
     *
     * @return La keyFrame précédente.
     */
    public Frame previousKeyFrame()
    {
        if (this.currentFrameIdx != 0)
        {
            int intervalBetweenKeyFrame = Strategy.FRAME_PER_SECOND / Strategy.KEY_FRAME_PER_SECOND;
            if (this.currentFrameIdx % intervalBetweenKeyFrame != 0)
            {
                this.currentFrameIdx -= this.currentFrameIdx % intervalBetweenKeyFrame;
            }
            else
            {
                this.currentFrameIdx -= intervalBetweenKeyFrame;
            }
        }
        return this.frames.get(this.currentFrameIdx);
    }

    /**
     * Fait avancer l'index de la frame courante, puis retourne la frame suivant la frame courante.
     * Si la frame courante est la dernière frame, l'index reste inchangé et la dernière frame est retournée.
     *
     * @return La frame suivante.
     */
    public Frame nextFrame()
    {
        if (!this.isLastFrame())
        {
            this.currentFrameIdx++;
        }
        return this.frames.get(this.currentFrameIdx);
    }

    /**
     * Modifie l'index de la frame courante pour qu'il pointe sur la keyFrame suivante, puis retourne celle-ci.
     * Si la frame courante est la dernière frame, l'index reste inchangé et la dernière frame est retournée.
     *
     * @return La keyFrame suivante.
     */
    public Frame nextKeyFrame()
    {
        if (!this.isLastFrame())
        {
            int intervalBetweenKeyFrame = Strategy.FRAME_PER_SECOND / Strategy.KEY_FRAME_PER_SECOND;
            this.currentFrameIdx = (this.currentFrameIdx / intervalBetweenKeyFrame + 1) * intervalBetweenKeyFrame;
        }
        return this.frames.get(this.currentFrameIdx);
    }

    /**
     * Permet de modifier la frame courant selon un delta de temps, en secondes, précis aux dixièmes de secondes.
     *
     * @param delta La longueur du saut entre les frames, en secondes.
     */
    public void changeCurrentFrame(float delta)
    {
        this.currentFrameIdx += Math.round(
                delta * 10) * Strategy.FRAME_PER_SECOND / 10; //bond précis au 1/10 de secondes
    }

    /**
     * Place l'index de la frame courante à la première frame.
     */
    public void goToBeginning()
    {
        this.currentFrameIdx = 0;
    }

    /**
     * Place l'index de la frame courante à la dernière frame.
     */
    public void goToEnd()
    {
        this.currentFrameIdx = this.frames.size() - 1;
    }


    /*
     * Gestion des équipes
     */
    public void addTeam(String teamName, String colour) throws MaxNumberException
    {
        if (!teamName.equals("default"))
        {
            try
            {
                this.removeTeam("default");
            }
            catch (TeamNotFound e)
            {
                // Il n'y a pas d'équipe nommée "default", but who cares ?
            }
        }

        Team team = new Team(teamName, colour, this.sport.getMaxPlayersPerTeam(), this.checkMaxNumberPlayer);

        if (!this.checkMaxNumberTeam || this.teams.size() < this.sport.getMaxTeams())
        {
            this.teams.put(teamName, team);
        }
        else
        {
            throw new MaxNumberException(
                    String.format("Le nombre maximal d'equipe a ete atteint %d", this.sport.getMaxTeams()));
        }
    }

    public void removeTeam(String teamName) throws TeamNotFound
    {
        if (this.teams.containsKey(teamName))
        {
            this.teams.remove(teamName);
        }
        else
        {
            throw new TeamNotFound(String.format("L'equipe %s n'existe pas", teamName));
        }
    }

    public void addTeamPlayer(String teamName, Player player) throws TeamNotFound, MaxNumberException
    {
        try
        {
            this.teams.get(teamName).addPlayer(player);
        }
        catch (NullPointerException e)
        {
            throw new TeamNotFound(String.format("L'equipe %s n'existe pas", teamName), e);
        }
    }

    public void removeTeamPlayer(String teamName, Player player)
    {
        this.teams.get(teamName).removePlayer(player);
    }

    public void switchTeamPlayer(String oldTeamName, String newTeamName,
                                 Player player) throws TeamNotFound, MaxNumberException
    {
        this.addTeamPlayer(newTeamName, player);
        this.removeTeamPlayer(oldTeamName, player);
    }

    public String getTeamColour(String teamName)
    {
        return this.teams.get(teamName).getColour();
    }

    public void setTeamColour(String teamName, String colour)
    {
        this.teams.get(teamName).setColour(colour);
    }

    public Vector getPixelToUnitRatio()
    {
        return this.sport.getPixelToUnitRatio();
    }

    public void setPixelToUnitRatio(Vector ratio)
    {
        this.sport.setPixelToUnitRatio(ratio);
    }


    /*
     * Gestion des GameObjects
     */
    public String addPlayer(String team) throws TeamNotFound, MaxNumberException
    {
        Player player = new Player();
        if (team == null)
        {
            if (this.teams.get("default") == null)
            {
                this.addTeam("default", "0x0000FF");
            }
            team = "default";
        }
        this.addTeamPlayer(team, player);
        this.gameObjects.add(player);
        return player.getId();
    }

    public String addProjectile()
    {
        Projectile projectile = new Projectile(this.sport.getProjectile());
        this.gameObjects.add(projectile);
        return projectile.getId();
    }

    public String addObstacle(Obstacle obstacle)
    {
        this.gameObjects.add(obstacle);
        return obstacle.getId();
    }

    public void placeGameObject(String gameObjectUuid, Vector position, float orientation) throws GameObjectNotFound
    {
        GameObject gameObject = this.getGameObjectByUUID(gameObjectUuid);
        Set<GameObject> currentFrameGameObjects = this.getCurrentFrame().getGameObjects();
        //FIXME: il faut déplacer les projectiles associés aux joueurs
        if (currentFrameGameObjects.contains(gameObject))
        {
            this.getCurrentFrame().placeGameObject(gameObject, position, orientation);
            if (this.currentFrameIdx != 0)
            {
                int nbFrames = Strategy.FRAME_PER_SECOND / Strategy.KEY_FRAME_PER_SECOND;
                int previousKeyFrameId = this.currentFrameIdx - nbFrames;
                Frame previousKeyFrame = this.getFrame(previousKeyFrameId);

                double posDeltaX = (position.getX() - previousKeyFrame.getPosition(gameObject).getX()) / nbFrames;
                double posDeltaY = (position.getY() - previousKeyFrame.getPosition(gameObject).getY()) / nbFrames;
                float deltaOrientation = (orientation - previousKeyFrame.getOrientation(gameObject)) / nbFrames;

                for (int i = 1; i < nbFrames; i++)
                {
                    Frame subFrame = this.getFrame(previousKeyFrameId + i);
                    Vector p = subFrame.getPosition(gameObject);
                    float o = subFrame.getOrientation(gameObject);
                    subFrame.placeGameObject(gameObject, new Vector(p.getX() + i * posDeltaX, p.getY() + i * posDeltaY),
                                             o + i * deltaOrientation);
                }
            }
        }
        else
        {
            GameObjectState gameObjectState = new GameObjectState(position, orientation, gameObject.getDimensions());
            this.getCurrentFrame().addGameObject(gameObject, gameObjectState);
        }
    }

    public void deleteGameObject(GameObject gameObject)
    {
        if (gameObject instanceof Player)
        {
            boolean isPlayerInFrame = false;
            for (Frame frame: this.frames)
            {
                if (frame.getGameObjects().contains(gameObject))
                {
                    isPlayerInFrame = true;
                    break;
                }
            }
            if (!isPlayerInFrame)
            {
                String teamName = this.getPlayerTeam((Player) gameObject);
                this.removeTeamPlayer(teamName, (Player) gameObject);
            }
        }
        for (int i = this.currentFrameIdx; i < this.frames.size(); i++)
        {
            this.frames.get(i).removeGameObject(gameObject);
        }
    }

    public void clearUnplacedGameObjects()
    {
        HashSet<GameObject> newGameObjectSet = new HashSet<GameObject>();
        Set<GameObject> currentFrameGameObjects = this.getCurrentFrame().getGameObjects();
        for (GameObject go : this.gameObjects)
        {
            if (currentFrameGameObjects.contains(go))
            {
                newGameObjectSet.add(go);
            }
        }
        this.gameObjects = newGameObjectSet;
    }

    public boolean collide(Vector position, GameObject gameObject)
    {
       return this.frames.get(this.currentFrameIdx).collide(position, gameObject);
    }

    /*
     * Autre méthodes
     */
    public boolean validatePosition(Vector position)
    {
        return this.sport.validatePosition(position);
    }

    public Set<GameObject> getGameObjects()
    {
        return this.gameObjects;
    }

    public GameObject getGameObjectByCoordinate(Vector coordinate)
    {
        return this.getCurrentFrame().getGameObjectByCoordinate(coordinate);
    }

    public GameObject getGameObjectByUUID(String uuid) throws GameObjectNotFound
    {
        for (GameObject gameObject : this.gameObjects)
        {
            if (gameObject.getId().equals(uuid))
            {
                return gameObject;
            }
        }
        throw new GameObjectNotFound(String.format("Le GameObject avec le uuid %s n'a pas ete trouve.", uuid));
    }

    public Vector getFieldDimensions()
    {
        return sport.getFieldDimensions();
    }

    public String getFieldImagePath()
    {
        return this.sport.getFieldImagePath();
    }

    @Override
    public String toString()
    {
        String repr = "Strategie \n";
        int i = 0;
        for (Frame f : this.frames)
        {
            repr += "FID: " + i + " -- " + f.toString();
            i++;
        }

        return repr;
    }

    public void takeProjectile(GameObject player)
    {
        Set<GameObject> gameObjectsInCollision = this.frames.get(this.currentFrameIdx).detectCollisionsIgnoreCollidable(player);
        for (GameObject gameObject: gameObjectsInCollision)
        {
            if (gameObject instanceof Projectile)
            {
                ((Projectile) gameObject).setController((Player) player);
                ((Player) player).takeProjectile((Projectile) gameObject);
                break;
            }
        }
    }

    public void save(String stratPath)
    {

        String strategyFilename = String.format("%s/%s.ser", stratPath, this.name);
        File f = new File(strategyFilename);
        try
        {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try (FileOutputStream fileOut = new FileOutputStream(strategyFilename);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut))
        {
            objOut.writeObject(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Strategy load(String stratPath, String stratFilename)
    {
        String stratFilenamePath = String.format("%s/%s.ser", stratPath, stratFilename);
        Strategy loadedStrat = null;
        try (FileInputStream fileIn = new FileInputStream(stratFilenamePath);
             ObjectInputStream objIn = new ObjectInputStream(fileIn))
        {
            loadedStrat = (Strategy) objIn.readObject();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Le fichier de sauvegarde n'a pas pu etre charge (not found): " + stratFilenamePath);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(String.format("La classe de la Strategie %s n'a pas ete trouvee.", stratFilename));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return loadedStrat;
    }

    @Override
    public String getDisplayName()
    {
        return this.name;
    }

    @Override
    public String getImagePath()
    {
        //FIXME: retourner le path vers l'image exporter de la stratégie
        return this.sport.getImagePath();
    }
}
