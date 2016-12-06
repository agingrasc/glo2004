package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.TreeViewable;
import org.glo.giftw.domain.exceptions.StrategyNotFound;

import java.io.IOException;

public class ModeToolBarController
{
    @FXML
    private ToolBar rootToolBar;

    @FXML
    void onActionImageByImage(ActionEvent event) throws IOException
    {
        TreeViewable strategy = RootLayoutController.getInstance().getOpenStrategyController().getTreeTableView().getSelectionModel().getSelectedItem().getValue();
        if (strategy != null)
        {
            try
            {
                Controller.getInstance().openStrategy(strategy.getDisplayName());
            }
            catch (StrategyNotFound e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            RootLayoutController.getInstance().imageByImage();
        }
    }

    @FXML
    void onActionRealTime(ActionEvent event) throws IOException
    {
        RootLayoutController.getInstance().realTime();
    }

    @FXML
    void onActionWatch(ActionEvent event) throws IOException
    {
    	String strategy = Controller.getInstance().getStrategyName();
    	if(strategy == null)
    	{
    		TreeViewable strategySelected = RootLayoutController.getInstance().getOpenStrategyController().getTreeTableView().getSelectionModel().getSelectedItem().getValue();
    		if (strategySelected != null)
            {
                try
                {
                    Controller.getInstance().openStrategy(strategySelected.getDisplayName());
                }
                catch (StrategyNotFound e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
    	}
        
        RootLayoutController.getInstance().watch();
    }

    public ToolBar getRootToolBar()
    {
        return rootToolBar;
    }
}
