package org.glo.giftw;

import org.glo.giftw.domain.PlayerPool;
import org.glo.giftw.domain.Vector;

public class DomainMain {

    public static void main(String[] args)
    {
        PlayerPool pp = new PlayerPool();
        Integer id = pp.addPlayer(new Vector(0, 0));

        pp.setPlayerInfo(id, "Foo", 13, "Fubar");
        System.out.println(pp.toString());
    }
}
