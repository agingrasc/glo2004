package org.glo.giftw.view.edit;

import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.GameObject;
import org.glo.giftw.domain.strategy.Player;

public class ViewableGameObjectBuilder
{
    public static ViewableGameObject buildViewableGameObject(String uuid) throws GameObjectNotFound
    {
        GameObject gameObject = Controller.getInstance().getGameObjectByUUID(uuid);
        if (gameObject instanceof Player)
        {
            return new ViewablePlayer(uuid);
        }
        else
        {
            return new ViewableGameObject(uuid);
        }
    }
}
