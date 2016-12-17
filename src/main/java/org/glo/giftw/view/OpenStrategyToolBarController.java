package org.glo.giftw.view;

import java.io.IOException;

import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.TreeViewable;
import org.glo.giftw.domain.strategy.Sport;
import org.glo.giftw.domain.strategy.Strategy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;

public class OpenStrategyToolBarController
{
    @FXML
    private ToolBar rootToolBar;

    @FXML
    void onActionDelete(ActionEvent event) throws IOException
    {
    	TreeViewable selected = RootLayoutController.getInstance().getOpenStrategyController().getTreeTableView().getSelectionModel().getSelectedItem().getValue();
        if (selected != null)
        {
        	if(selected instanceof Sport)
        	{
        		Controller.getInstance().deleteSport(selected.getDisplayName());
        	}
        	else if(selected instanceof Strategy)
        	{
        		Controller.getInstance().deleteStrategy(selected.getDisplayName());
        	}
        }
        RootLayoutController.getInstance().getOpenStrategyController().updateTree();
    }

    public ToolBar getRootToolBar()
    {
        return rootToolBar;
    }
}
