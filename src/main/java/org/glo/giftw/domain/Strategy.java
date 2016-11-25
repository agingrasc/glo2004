package org.glo.giftw.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Contient les frames et les appels necessaires pour les joueurs
 */
public class Strategy implements Serializable
{
    public static final long serialVersionUID = 1L;
    public static final String STRATEGY_PATH = "data/strategies";
    private static int framePerSecond = 30;
    private static int keyFramePerSecond = 2;

    private String name;
    private Sport sport;
    private int currentFrameIdx;    
    private HashMap<String, ArrayList<Player>> teams; //Associe chaque équipe impliquée dans une strategie avec son nom
    private ArrayList<GameObject> gameObjects;        //Liste contenant les instances des gameObjects de la stratégie
    private ArrayList<Frame> frames;
    
    public Strategy(String name, Sport sport)
    {
        this.name = name;
        this.sport = sport;
        this.currentFrameIdx = 0;
        this.teams = new HashMap<>();
        this.gameObjects = new ArrayList<>();
        this.frames = new ArrayList<>();
        this.frames.add(new Frame(true));
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
    
    public List<Player> getTeam(String teamName)
    {
        return this.teams.get(teamName);
    }

    
    /*
     * Logique de frame
     */
    public void addFrame(int frameId, Frame frame)
    {
        this.frames.add(frameId, frame);
    }
    
    public void createNewFrame()
    {
        if(this.frames.isEmpty())
        {
            this.frames.add(new Frame(true));
        }
        else
        {
            Frame lastKeyFrame = this.frames.get(this.frames.size() - 1);
            
            //ajout des subFrames
            for(int i = 1; i < (Strategy.framePerSecond / Strategy.keyFramePerSecond); i++)
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
    
    public Frame getCurrentFrame()
    {
        return this.frames.get(this.currentFrameIdx);
    }
    
    /**
     * Retourne la frame précédant la frame courrante. Si la frame courante est la première frame, retourne celle-ci.
     * @return La frame précédente.
     */
    public Frame previousFrame()
    {
        if(this.currentFrameIdx != 0)
        {
            this.currentFrameIdx--;
        }
        return this.frames.get(this.currentFrameIdx);
    }
    
    /**
     * Retourne la frame suivant la frame courante. Si la frame courante est la dernière frame, retourne celle-ci.
     * @return La frame suivante.
     */
    public Frame nextFrame()
    {
        if(!this.isLastFrame())
        {
            this.currentFrameIdx++;
        }
        return this.frames.get(this.currentFrameIdx);
    }
    
    /**
     * Permet de modifier la frame courant selon un delta de temps, en secondes, précis aux dixièmes de secondes.
     * @param delta La longueur du saut entre les frames, en secondes.
     */
    public void changeCurrentFrame(float delta)
    {
        this.currentFrameIdx += Math.round(delta * 10) * Strategy.framePerSecond / 10; //bond précis au 1/10 de secondes 
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
    public void addTeam(String teamName)
    {
        this.teams.put(teamName, new ArrayList<>());
    }
    
    public void removeTeam(String teamName)
    {
        this.teams.remove(teamName);
    }

    public void addTeamPlayer(String teamName, Player player)
    {
        this.teams.get(teamName).add(player);
    }
    
    public void removeTeamPlayer(String teamName, Player player)
    {
        this.teams.get(teamName).remove(player);
    }
    
    public void switchTeamPlayer(String oldTeamName, String newTeamName, Player player)
    {
        this.addTeamPlayer(newTeamName, player);
        this.removeTeamPlayer(oldTeamName, player);
    }
    
    
    /*
     * Gestion des GameObjects
     */
    private Integer addGameObject(GameObject gameObject, Vector position, float orientation, Vector dimensions)
    {
        GameObjectState gameObjectState = new GameObjectState(position, orientation, dimensions);
        this.gameObjects.add(gameObject);
        this.getCurrentFrame().addGameObject(gameObject, gameObjectState);
        return gameObject.getId();
    }
    
    public Integer addPlayer(Vector position, float orientation, Vector dimensions)
    {
        Player player = new Player();
        return this.addGameObject(player, position, orientation, dimensions);
    }
    
    public Integer addProjectile(Vector position, float orientation, Vector dimensions)
    {
        Projectile projectile = new Projectile();
        return this.addGameObject(projectile, position, orientation, dimensions);
    }
    
    public Integer addObstacle(Obstacle obstacle, Vector position, float orientation, Vector dimensions)
    {
        return this.addGameObject(obstacle, position, orientation, dimensions);
    }
    
    public void placeGameObject(GameObject gameObject, Vector position, float orientation, Vector dimensions)
    {
        this.getCurrentFrame().placeGameObject(gameObject, position, orientation, dimensions);
        if(this.currentFrameIdx != 0)
        {
            int nbFrames = Strategy.framePerSecond / Strategy.keyFramePerSecond;
            int previousKeyFrameId = this.currentFrameIdx - nbFrames;
            Frame previousKeyFrame = this.getFrame(previousKeyFrameId);
            
            double posDeltaX = (position.getX() - previousKeyFrame.getPosition(gameObject).getX()) / nbFrames;
            double posDeltaY = (position.getY() - previousKeyFrame.getPosition(gameObject).getY()) / nbFrames;
            float deltaOrientation = (orientation - previousKeyFrame.getOrientation(gameObject)) / nbFrames;
            double dimDeltaX = (dimensions.getX() - previousKeyFrame.getDimensions(gameObject).getX()) / nbFrames;
            double dimDeltaY = (dimensions.getY() - previousKeyFrame.getDimensions(gameObject).getY()) / nbFrames;
            
            for(int i = 1; i < nbFrames; i++)
            {
                Frame subFrame = this.getFrame(this.currentFrameIdx + i);
                Vector p = subFrame.getPosition(gameObject);
                float o = subFrame.getOrientation(gameObject);
                Vector d = subFrame.getDimensions(gameObject);
                subFrame.placeGameObject(gameObject, new Vector(p.getX() + i*posDeltaX, p.getY() + i*posDeltaY),
                        o + i*deltaOrientation, new Vector(d.getX() + i*dimDeltaX, d.getY() + i*dimDeltaY));
            }
        }
    }
    
    
    /*
     * Autre méthodes
     */
    public boolean validatePosition(Vector position)
    {
        return this.sport.validatePosition(position);
    }
    
    public GameObject getGameObjectByCoordinate(Vector coordinate)
    {
        return this.getCurrentFrame().getGameObjectByCoordinate(coordinate);
    }
    
    @Override
    public String toString()
    {
        String repr = "Strategie \n";
        int i = 0;
        for (Frame f : this.frames)
        {
            repr += "FID: " + i + " -- " +  f.toString();
            i++;
        }

        return repr;
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

    private void copy(Strategy strat)
    {
        this.name = strat.name;
        this.sport = strat.sport;
        this.currentFrameIdx = strat.currentFrameIdx;
        this.teams = strat.teams;
        this.gameObjects = strat.gameObjects;
        this.frames = strat.frames;
    }
}
