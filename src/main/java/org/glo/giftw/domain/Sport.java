package org.glo.giftw.domain;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Sport implements Serializable
{
    public static final long serialVersionUID = 1L;
    public static final String SPORT_PATH = "./data/sports/";

    private String name;
    private List<String> roles;
    private Field field;

    public Sport()
    {
        this.name = "";
        this.roles = new ArrayList<String>();
        this.field = new Field();
    }

    public Sport(String name, List<String> roles, Field field)
    {
        this.name = name;
        this.roles = roles;
        this.field = field;
        this.save(SPORT_PATH);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<String> getRoles()
    {
        return roles;
    }

    public void setRoles(List<String> roles)
    {
        this.roles = roles;
    }

    public Field getField()
    {
        return field;
    }

    public void setField(Field field)
    {
        this.field = field;
    }

    @Override
    public String toString()
    {
        String ret = "Sport: " + this.name + "\nRoles\n";
        for (String r : this.roles)
        {
            ret += "- " + r + "\n";
        }
        ret += "Terrain\n" + this.field.toString();

        return ret;
    }

    public List<Sport> load(String sportPath)
    {
        ArrayList<Sport> sports = new ArrayList<>();

        File sportDirectory = new File(sportPath);
        if (sportDirectory.listFiles() != null)
        {
            for (File sportFile : sportDirectory.listFiles())
            {
                //Valider si l'extension est .ser
                try (FileInputStream fileIn = new FileInputStream(sportFile);
                     ObjectInputStream objIn = new ObjectInputStream(fileIn))
                {
                    Sport sport = (Sport) objIn.readObject();
                    sports.add(sport);
                }
                catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    System.out.println("Impossible de lire le fichier d'un sport: " + sportFile.toString());
                }
            }
        }

        return sports;
    }

    private void save(String sportPath)
    {

    }
}
