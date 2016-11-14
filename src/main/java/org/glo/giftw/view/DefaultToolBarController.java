package org.glo.giftw.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;

public class DefaultToolBarController
{	
	@FXML 
	private ToolBar rootToolBar;
	
	private RootLayoutController rootLayoutController;
	
	@FXML
	void onActionNewObstacle(ActionEvent event) throws IOException
	{
		rootLayoutController.newObstacle();
	}

	@FXML
	void onActionNewSport(ActionEvent event) throws IOException
	{
		rootLayoutController.newSport();
	}

	@FXML
	void onActionNewStrategy(ActionEvent event) throws IOException
	{
		rootLayoutController.newStrategy();
	}

	@FXML
	void onActionOpenObstacle(ActionEvent event) throws IOException
	{
		rootLayoutController.openObstacle();
	}

	@FXML
	void onActionOpenSport(ActionEvent event) throws IOException
	{
		rootLayoutController.openSport();
	}

	@FXML
	void onActionOpenStrategy(ActionEvent event) throws IOException
	{
		rootLayoutController.openStrategy();
	}

	@FXML
	void onActionSave(ActionEvent event)
	{
		System.out.println("onActionSave");
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
