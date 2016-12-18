package org.glo.giftw.domain;

import java.util.TimerTask;

public class AutoSave extends TimerTask
{
    public void run()
    {
        if (Controller.getInstance().isAutoSaveEnabled())
        {
            Controller.getInstance().saveStrategies();
        }
    }
}
