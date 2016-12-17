package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;

public class OpenSportToolBarController
{
    @FXML
    private ToolBar rootToolBar;

    @FXML
    void onActionDelete(ActionEvent event)
    {
        System.out.println("onActionDelete");
    }

    public ToolBar getRootToolBar()
    {
        return rootToolBar;
    }
}