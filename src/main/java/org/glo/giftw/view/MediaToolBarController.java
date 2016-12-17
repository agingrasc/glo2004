package org.glo.giftw.view;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;
import org.glo.giftw.domain.Controller;

import java.io.IOException;

public class MediaToolBarController extends AnimationTimer
{
    @FXML
    private ToolBar rootToolBar;

    private final static long FPS = 1000 * 1000 * 1000 / 30; //en nanoseconde
    private long lastTimeStamp;
    private CreationStackPaneController field;

    @FXML
    public void initialize()
    {
        try
        {
            this.field = RootLayoutController.getInstance().getCreationStackPaneController();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * Main loop pour la visualisation
     * @param long timestamp temps systeme au moment de l'appel en nanoseconde
     */
    public void handle(long timestamp)
    {
        long delta_t = timestamp - this.lastTimeStamp;
        boolean isLastFrame = Controller.getInstance().isLastFrame();

        System.out.println("Call play");
        if (delta_t >= FPS && !isLastFrame)
        {
            this.lastTimeStamp = timestamp;
            System.out.println("fubar: " + timestamp);
            this.field.resetDisplay();
            this.field.displayStrategy();
            Controller.getInstance().nextFrame();
            isLastFrame = Controller.getInstance().isLastFrame();
            System.out.println("Last frame: " + isLastFrame);
        }
    }


    @FXML
    void onActionPause(ActionEvent event)
    {
        this.stop();
    }

    @FXML
    void onActionStop(ActionEvent event)
    {
        this.stop();
        Controller.getInstance().goToBeginning();
        this.field.resetDisplay();
        this.field.displayStrategy();
    }

    @FXML
    void onActionPlay(ActionEvent event)
    {
        this.start();
    }

    @FXML
    void onActionReplay(ActionEvent event)
    {
        Controller.getInstance().goToBeginning();
        this.start();
    }

    public ToolBar getRootToolBar()
    {
        return rootToolBar;
    }
}
