package org.glo.giftw.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;

public class ModeToolBarController
{
	@FXML
	private ToolBar rootToolBar;
	
	private RootLayoutController rootLayoutController;
	
	@FXML
	void onActionImageByImage(ActionEvent event) throws IOException 
	{
		rootLayoutController.imageByImage();
	}
	
	@FXML
	void onActionRealTime(ActionEvent event) throws IOException 
	{
		rootLayoutController.realTime();
	}
	
	@FXML
	void onActionWatch(ActionEvent event) throws IOException 
	{
		rootLayoutController.watch();
	}

	public ToolBar getRootToolBar()
	{
		return rootToolBar;
	}

	public void setRootLayoutController(RootLayoutController rootLayoutController)
	{
		this.rootLayoutController = rootLayoutController;
	}
}
