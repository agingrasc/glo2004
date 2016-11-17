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

    private String name;
    private Sport sport;
    private int currentFrameIdx;    
    private HashMap<String, ArrayList<Player>> team; //Associe chaque équipe impliquée dans une strategie avec son nom
    private ArrayList<Obstacle> obstacles;           //Liste contenant les instances des obstacles de la stratégie
    private ArrayList<Projectile> projectiles;       //Liste contenant les instances des projectiles de la stratégie
    private ArrayList<Frame> frames;

    public Strategy(String name, Sport sport)
    {
        this.name = name;
        this.sport = sport;
        this.currentFrameIdx = 0;
        this.team = new HashMap<>();
        this.obstacles = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.frames = new ArrayList<>();
    }

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

    public void addFrame(int frameId, Frame frame)
    {
        this.frames.add(frameId, frame);
    }

    public void addTeam(String teamName)
    {
        this.team.put(teamName, new ArrayList<>());
    }

    public List<Player> getTeam(String teamName)
    {
        return this.team.get(teamName);
    }

    public void addTeamPlayer(String teamName, Player player)
    {
        this.team.get(teamName).add(player);
    }
    
    public boolean validatePosition(Vector position)
    {
        return this.sport.validatePosition(position);
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
        this.frames = strat.frames;
    }
}
