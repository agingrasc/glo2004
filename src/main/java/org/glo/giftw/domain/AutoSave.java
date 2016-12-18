package org.glo.giftw.domain;

import java.util.TimerTask;
import org.glo.giftw.domain.Controller;

public class AutoSave extends TimerTask
{
    public void run()
    {
        System.out.println("Saving");
        if (Controller.getInstance().isAutoSaveEnabled())
        {
            Controller.getInstance().saveStrategies();
        }
    }
}
