package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;

import java.io.IOException;

public class OpenStrategyToolBarController
{
    @FXML
    private ToolBar rootToolBar;

    @FXML
    void onActionDelete(ActionEvent event)
    {
        System.out.println("onActionDelete");
    }

    @FXML
    void onActionConfigureStrategy(ActionEvent event) throws IOException
    {
        RootLayoutController.getInstance().configureStrategy();
    }

    public ToolBar getRootToolBar()
    {
        return rootToolBar;
    }
}
