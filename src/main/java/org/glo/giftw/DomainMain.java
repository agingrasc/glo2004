package org.glo.giftw;

import org.glo.giftw.domain.*;

import java.util.Arrays;
import java.util.Random;

public class DomainMain
{

    public static void main(String[] args)
    {
        System.out.println("Création d'une stratégie.");
        Strategy ultimateStrategy = new Strategy("Ultimate Strategy",
                                                 new Sport("Hockey", Arrays.asList("Allier", "Centre", "Goaler"),
                                                           new Field(new Vector(9000, 6000))));

        PlayerPool pp = new PlayerPool();
        Integer[] id = new Integer[6];
        String[] names = {"Foo", "Bar", "Baz", "Blue", "Red", "Gold"};
        for (int i = 0; i < 6; i++)
        {
            id[i] = pp.addPlayer(new Vector());
            Player tmpPlayer = pp.getPlayer(id[i]);
            tmpPlayer.setName(names[i]);
            tmpPlayer.setJerseyNumber(i);
        }

        Team us = new Team(pp.getAllPlayer(), "Awesome", "Gold");
        ultimateStrategy.addTeam(us);

        for (int i = 0; i < 30; i++)
        {
            Frame f;
            if (i == 0)
            {
                f = new Frame();
                for (Player p : us.getPlayers())
                {
                    f.addGameObject(p);
                }
                //squash
            }
            else
            {
                f = new Frame(ultimateStrategy.getFrame(i - 1));
            }

            for (Player p : us.getPlayers())
            {
                double x, y, theta;
                x = new Random().nextDouble() * 9000 - 1;
                y = new Random().nextDouble() * 6000 - 1;
                theta = new Random().nextDouble();

                f.placeGameObject(p.getId(), new Vector(x, y), (float)theta, new Vector());
            }

            ultimateStrategy.addFrame(i, f);
        }

        System.out.println(ultimateStrategy.toString());
    }
}
