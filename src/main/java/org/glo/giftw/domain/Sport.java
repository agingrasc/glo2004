package org.glo.giftw.domain;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Sport implements Serializable
{
    public static final long serialVersionUID = 1L;

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
}
