package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;

import java.io.IOException;

public class OpenSportToolBarController
{
    @FXML
    private ToolBar rootToolBar;

    @FXML
    void onActionDelete(ActionEvent event)
    {
        System.out.println("onActionDelete");
    }

    @FXML
    void onActionConfigureSport(ActionEvent event) throws IOException
    {
        RootLayoutController.getInstance().configureSport();
    }

    public ToolBar getRootToolBar()
    {
        return rootToolBar;
    }
}