package org.glo.giftw.domain;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Sport implements Serializable
{
    public static final long serialVersionUID = 1L;

    private String name;
    private List<String> roles;
    private Field field;
    private String projectileName;
    private String projectileImagePath;
    private int maxPLayersPerTeam;
    private int maxTeams;

    public Sport()
    {
        this.name = "";
        this.roles = new ArrayList<>();
        this.field = new Field();
        this.projectileName = "";
        this.projectileImagePath = "";
        this.maxPLayersPerTeam = 6; //valeur par défaut pour le hockey
        this.maxTeams = 2;
    }

    public Sport(String name, List<String> roles, Field field, String projectileName, String projectileImagePath, 
            int maxPLayersPerTeam, int maxTeams)
    {
        this.name = name;
        this.roles = roles;
        this.field = field;
        this.projectileName = projectileName;
        this.projectileImagePath = projectileImagePath;
        this.maxPLayersPerTeam = maxPLayersPerTeam;
        this.maxTeams = maxTeams;
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
    
    public void addRole(String role)
    {
        this.roles.add(role);
    }
    
    public void removeRole(String role)
    {
        this.roles.remove(role);
    }

    public Field getField()
    {
        return field;
    }

    public void setField(Field field)
    {
        this.field = field;
    }
    
    public boolean validatePosition(Vector position)
    {
        return this.field.validatePosition(position);
    }

    public String getProjectileName()
    {
        return projectileName;
    }

    public void setProjectileName(String projectileName)
    {
        this.projectileName = projectileName;
    }

    public String getProjectileImagePath()
    {
        return projectileImagePath;
    }

    public void setProjectileImagePath(String projectileImagePath)
    {
        this.projectileImagePath = projectileImagePath;
    }

    public int getMaxPLayersPerTeam()
    {
        return maxPLayersPerTeam;
    }

    public void setMaxPLayersPerTeam(int maxPLayersPerTeam)
    {
        this.maxPLayersPerTeam = maxPLayersPerTeam;
    }

    public int getMaxTeams()
    {
        return maxTeams;
    }

    public void setMaxTeams(int maxTeams)
    {
        this.maxTeams = maxTeams;
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
        ret += "Projectile: " + this.projectileName + " " + this.projectileImagePath + "\n";
        ret += "Joué par " + Integer.toString(this.maxTeams) + " équipes de " + Integer.toString(this.maxPLayersPerTeam)
                + " joueurs";
        return ret;
    }
}
