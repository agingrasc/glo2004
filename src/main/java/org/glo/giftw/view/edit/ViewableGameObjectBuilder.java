package org.glo.giftw.view.edit;

import org.glo.giftw.domain.strategy.GameObject;
import org.glo.giftw.domain.strategy.Player;

/**
 *
 */
public class ViewableGameObjectBuilder
{
    public static ViewableGameObject buildViewableGameObject(GameObject gameObject)
    {
        if (gameObject instanceof Player)
        {
            return new ViewablePlayer(gameObject.getId());
        }
        else
        {
            return new ViewableGameObject(gameObject.getId());
        }
    }
}
