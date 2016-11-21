package org.glo.giftw.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;

public class OpenObstacleToolBarController
{
	@FXML
	private ToolBar rootToolBar;
	
	@FXML
	void onActionDelete(ActionEvent event)
	{
		System.out.println("onActionDelete");
	}

	@FXML
	void onActionConfigureObstacle(ActionEvent event) throws IOException
	{
		RootLayoutController.getInstance().configureObstacle();
	}

	public ToolBar getRootToolBar()
	{
		return rootToolBar;
	}
}