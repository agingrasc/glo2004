package org.glo.giftw.view;

import java.io.IOException;

import org.glo.giftw.domain.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;

public class OpenObstacleToolBarController
{
    @FXML
    private ToolBar rootToolBar;

    @FXML
    void onActionDelete(ActionEvent event) throws IOException
    {
    	String selected = RootLayoutController.getInstance().getOpenObstacleController().getTableView().getSelectionModel().getSelectedItem().getName();
        if (selected != null)
        {
        	Controller.getInstance().deleteObstacle(selected);
        }
        RootLayoutController.getInstance().getOpenObstacleController().updateTable();
    }

    public ToolBar getRootToolBar()
    {
        return rootToolBar;
    }
}