package org.glo.giftw.view;

import java.io.IOException;

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
		RootLayoutController.getInstance().imageByImage();
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
