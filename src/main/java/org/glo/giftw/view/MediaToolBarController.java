package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;
import org.glo.giftw.domain.Controller;

import java.io.IOException;

public class MediaToolBarController
{
    @FXML
    private ToolBar rootToolBar;

    @FXML
    void onActionPause(ActionEvent event)
    {
        try
        {
            RootLayoutController.getInstance().getMediaContentController().stop();
            Controller.getInstance().goToBeginning();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionStop(ActionEvent event)
    {
        try
        {
            RootLayoutController.getInstance().getMediaContentController().stop();
            Controller.getInstance().goToBeginning();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionPlay(ActionEvent event)
    {
        try
        {
            RootLayoutController.getInstance().getMediaContentController().start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionReplay(ActionEvent event)
    {
        Controller.getInstance().goToBeginning();
    }

    public ToolBar getRootToolBar()
    {
        return rootToolBar;
    }
}
