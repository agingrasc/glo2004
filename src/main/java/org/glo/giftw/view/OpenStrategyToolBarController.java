package org.glo.giftw.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;

public class OpenStrategyToolBarController
{	
	@FXML
	private ToolBar rootToolBar;
	
	private RootLayoutController rootLayoutController;
	
	@FXML
	void onActionDelete(ActionEvent event)
	{
		System.out.println("onActionDelete");
	}
	
	@FXML
	void onActionConfigureStrategy(ActionEvent event) throws IOException
	{
		rootLayoutController.configureStrategy();
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
