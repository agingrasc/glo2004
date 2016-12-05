package org.glo.giftw.domain.strategy;

import org.glo.giftw.domain.TreeViewable;
import org.glo.giftw.domain.util.Vector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sport implements Serializable, TreeViewable
{
    public static final long serialVersionUID = 1L;

    private String name;
    private List<String> roles;
    private Field field;
    private Projectile projectile;
    private int maxPLayersPerTeam;
    private int maxTeams;

    public Sport()
    {
        this.name = "";
        this.roles = new ArrayList<>();
        this.field = new Field();
        this.projectile = new Projectile("", "", new Vector(1, 1));
        this.maxPLayersPerTeam = 6; //valeur par défaut pour le hockey
        this.maxTeams = 2;
    }

    public Sport(String name, List<String> roles, Field field, Projectile projectile, int maxPlayersPerTeam,
            int maxTeams)
    {
        this.name = name;
        this.roles = new ArrayList<String>(roles);
        this.field = field;
        this.projectile = projectile;
        this.maxPLayersPerTeam = maxPlayersPerTeam;
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
        return this.roles;
    }
    
    public Vector getPixelToUnitRatio()
    {
        return this.field.getPixelToUnitRatio();
    }

    public void setPixelToUnitRatio(Vector ratio)
    {
        this.field.setPixelToUnitRatio(ratio);
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
        return this.field;
    }

    public Vector getFieldDimensions()
    {
        return this.field.getDimensions();
    }

    public void setField(Field field)
    {
        this.field = field;
    }

    public Projectile getProjectile()
    {
        return this.projectile;
    }

    public boolean validatePosition(Vector position)
    {
        return this.field.validatePosition(position);
    }

    public int getMaxPlayersPerTeam()
    {
        return this.maxPLayersPerTeam;
    }

    public void setMaxPLayersPerTeam(int maxPLayersPerTeam)
    {
        this.maxPLayersPerTeam = maxPLayersPerTeam;
    }

    public int getMaxTeams()
    {
        return this.maxTeams;
    }

    public void setMaxTeams(int maxTeams)
    {
        this.maxTeams = maxTeams;
    }

    public String getFieldImagePath()
    {
        return this.field.getImagePath();
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
        ret += "Projectile: " + this.projectile + "\n";
        ret += "Joué par " + Integer.toString(this.maxTeams) + " équipes de " + Integer.toString(this.maxPLayersPerTeam)
                + " joueurs";
        return ret;
    }

    @Override
    public String getDisplayName()
    {
        return this.name;
    }

    public Vector getFieldCoordinate(Vector adjustedCoordinate)
    {
        return this.field.getFieldCoordinate(adjustedCoordinate);
    }

    @Override
    public String getImagePath()
    {
        return this.field.getImagePath();
    }
}
