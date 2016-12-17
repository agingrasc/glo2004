package org.glo.giftw.view.edit;

import org.glo.giftw.domain.strategy.GameObject;
import org.glo.giftw.domain.strategy.Player;

/**
 *
 */
public class ViewableGameObjectBuilder
{
    public static ViewableGameObject buildViewableGameObject(GameObject gameObject, boolean isDisplayName,
                                                             boolean isDisplayRole, boolean isSelected)
    {
        if (gameObject instanceof Player)
        {
            return new ViewablePlayer(gameObject.getId(), isDisplayName, isDisplayRole, isSelected);
        }
        else
        {
            return new ViewableGameObject(gameObject.getId(), isSelected);
        }
    }
}
