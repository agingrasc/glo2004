package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;

public class MediaToolBarController
{
	@FXML
	private ToolBar rootToolBar;

	@FXML
	void onActionPause(ActionEvent event)
	{
		System.out.println("onActionPause");
	}

	@FXML
	void onActionPlay(ActionEvent event)
	{
		System.out.println("onActionPlay");
	}
	
	@FXML 
	void onActionReplay(ActionEvent event)
	{
		System.out.println("onActionReplay");
	}

	public ToolBar getRootToolBar()
	{
		return rootToolBar;
	}
}
