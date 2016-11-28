package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToolBar;

import java.io.IOException;

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
	void onActionStop(ActionEvent event)
	{
		try
		{
			RootLayoutController.getInstance().getMediaContentController().stop();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@FXML
	void onActionPlay(ActionEvent event)
	{
		try
		{
			RootLayoutController.getInstance().getMediaContentController().start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
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
