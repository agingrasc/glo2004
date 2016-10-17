package org.glo.giftw;

import org.glo.giftw.domain.Field;
import org.glo.giftw.domain.Sport;
import org.glo.giftw.domain.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DomainMain {

    public static void main(String[] args)
    {
        Sport soccer = new Sport("Soccer", new ArrayList<>(Arrays.asList("Allier", "Defenseur", "Gardien")), new Field(new Vector(9000, 6000)));
        Sport hockey = new Sport("Hockey", new ArrayList<>(Arrays.asList("Centre", "Allier", "Defenseur", "Gardien")), new Field(new Vector(6000, 4000)));
        List<Sport> sports = Sport.load(Sport.SPORT_PATH);
        System.out.println(sports.size());
    }
}
