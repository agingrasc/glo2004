package org.glo.giftw.view;

import java.io.IOException;

import org.glo.giftw.controller.Controller;
import org.glo.giftw.domain.Strategy;
import org.glo.giftw.domain.TreeViewable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;

public class ModeToolBarController
{
	@FXML
	private ToolBar rootToolBar;
	
	@FXML
	void onActionImageByImage(ActionEvent event) throws IOException 
	{
		TreeViewable strategy = RootLayoutController.getInstance().getOpenStrategyController().getTreeTableView().getSelectionModel().getSelectedItem().getValue();
		if(strategy != null)
		{
			Controller.getInstance().openStrategy((Strategy)strategy);
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
		RootLayoutController.getInstance().watch();
	}

	public ToolBar getRootToolBar()
	{
		return rootToolBar;
	}
}
