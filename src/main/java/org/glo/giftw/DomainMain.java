package org.glo.giftw;

import org.glo.giftw.domain.Field;
import org.glo.giftw.domain.PlayerPool;
import org.glo.giftw.domain.Sport;
import org.glo.giftw.domain.Vector;

import java.util.ArrayList;
import java.util.Arrays;

public class DomainMain {

    public static void main(String[] args)
    {
        Sport soccer = new Sport("Soccer", new ArrayList<String>(Arrays.asList("Allier", "Defenseur", "Gardien")), new Field(new Vector(9000, 6000)));
        System.out.println(soccer.toString());
    }
}
