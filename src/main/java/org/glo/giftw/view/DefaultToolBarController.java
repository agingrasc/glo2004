package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;
import org.glo.giftw.domain.Controller;

import java.io.IOException;

public class DefaultToolBarController
{
    @FXML
    private ToolBar rootToolBar;

    @FXML
    void onActionNewObstacle(ActionEvent event) throws IOException
    {
        RootLayoutController.getInstance().newObstacle();
    }

    @FXML
    void onActionNewSport(ActionEvent event) throws IOException
    {
        RootLayoutController.getInstance().newSport();
    }

    @FXML
    void onActionNewStrategy(ActionEvent event) throws IOException
    {
        RootLayoutController.getInstance().newStrategy();
    }

    @FXML
    void onActionOpenObstacle(ActionEvent event) throws IOException
    {
        RootLayoutController.getInstance().openObstacle();
    }

    @FXML
    void onActionOpenSport(ActionEvent event) throws IOException
    {
        RootLayoutController.getInstance().openStrategy();
    }

    @FXML
    void onActionOpenStrategy(ActionEvent event) throws IOException
    {
        RootLayoutController.getInstance().openStrategy();
    }

    @FXML
    void onActionSave(ActionEvent event)
    {
        Controller.getInstance().saveStrategies();
    }

    @FXML
    void onActionExport(ActionEvent event) throws IOException
    {
        RootLayoutController.getInstance().exportStrategy();
    }

    public ToolBar getRootToolBar()
    {
        return rootToolBar;
    }
}
