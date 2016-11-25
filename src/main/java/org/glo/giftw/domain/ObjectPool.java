package org.glo.giftw.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Classe mère de tous les types de Pools
 */
public abstract class ObjectPool implements Serializable
{
    public static final long serialVersionUID = 1L;
    
    protected boolean persistent;
    
    public ObjectPool()
    {
        this(true);
    }
    
    public ObjectPool(boolean persistent)
    {
        this.persistent = persistent;
    }
    
    protected abstract void copy(ObjectPool op);
    
    protected void loadObjectPool(String poolPath)
    {
        try (FileInputStream fileIn = new FileInputStream(poolPath); ObjectInputStream in = new ObjectInputStream(fileIn))
        {
            ObjectPool tmp = (ObjectPool) in.readObject();
            this.copy(tmp);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println(String.format("Il n'y a pas de fichier existant au path: %s, aucun chargement.", poolPath));
        }
    }

    protected void saveObjectPool(String poolPath)
    {
        System.out.println("Sauvegarde au path: " + poolPath);
        File f = new File(poolPath);
        //noinspection ResultOfMethodCallIgnored
        f.getParentFile().mkdirs();
        try
        {
            //noinspection ResultOfMethodCallIgnored
            f.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try (FileOutputStream fileOut = new FileOutputStream(poolPath); ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            out.writeObject(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
