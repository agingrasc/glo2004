package org.glo.giftw.view;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToolBar;
import org.glo.giftw.domain.Controller;

import java.io.IOException;

public class MediaToolBarController extends AnimationTimer
{
    @FXML
    private ToolBar rootToolBar;
    @FXML
    private SplitMenuButton playButton;

    private double speed;

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
        if (delta_t >= (FPS * this.speed) && !isLastFrame)
        {
            this.lastTimeStamp = timestamp;
            this.field.resetDisplay();
            this.field.displayStrategy();
            Controller.getInstance().nextFrame();
            isLastFrame = Controller.getInstance().isLastFrame();
        }
        else if (isLastFrame)
        {
            this.stop();
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

    @FXML
    void onSpeedSelected(ActionEvent event)
    {
        String speed = ((MenuItem) event.getSource()).getText();
        switch (speed)
        {
            case "-8x":
                this.speed = 8;
                break;
            case "-4x":
                this.speed = 4;
                break;
            case "-2x":
                this.speed = 2;
                break;
            case "1x":
                this.speed = 1;
                break;
            case "2x":
                this.speed = 1/2;
                break;
            case "4x":
                this.speed = 1/4;
                break;
            case "8x":
                this.speed = 1/8;
                break;
            default:
                this.speed = 1;
                System.out.println("Should not happen");
                break;
        }
    }

    public ToolBar getRootToolBar()
    {
        return rootToolBar;
    }
}
