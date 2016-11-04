package org.glo.giftw;

import org.glo.giftw.domain.*;

import java.util.Arrays;
import java.util.Random;

public class DomainMain
{

    public static void main(String[] args)
    {
        System.out.println("Creation d'une strategie.");
        Strategy ultimateStrategy = new Strategy("Ultimate Strategy",
                                                 new Sport("Hockey", Arrays.asList("Allier", "Centre", "Goaler"),
                                                           new Field(new Vector(9000, 6000))));

        PlayerPool pp = new PlayerPool(false);
        Integer[] id = new Integer[6];
        String[] names = {"Foo", "Bar", "Baz", "Blue", "Red", "Gold"};
        for (int i = 0; i < 6; i++)
        {
            id[i] = pp.addPlayer(new Vector(), false);
            Player tmpPlayer = pp.getPlayer(id[i]);
            tmpPlayer.setName(names[i]);
            tmpPlayer.setJerseyNumber(i);
        }

        ultimateStrategy.addTeam("Blue");
        pp.getAllPlayer().forEach(player -> ultimateStrategy.addTeamPlayer("Blue", player));

        for (int i = 0; i < 30; i++)
        {
            Frame f;
            if (i == 0)
            {
                f = new Frame();
                ultimateStrategy.getTeam("Blue").forEach(f::addGameObject);
            }
            else
            {
                f = new Frame(ultimateStrategy.getFrame(i - 1));
            }

            for (Player p : ultimateStrategy.getTeam("Blue"))
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
        ultimateStrategy.save(Strategy.STRATEGY_PATH);
        Strategy copyCat = Strategy.load(Strategy.STRATEGY_PATH, "Ultimate Strategy");
        System.out.printf(copyCat.toString());
    }
}
