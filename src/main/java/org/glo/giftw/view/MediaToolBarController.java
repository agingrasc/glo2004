package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MediaToolBarController
{
	@FXML
	void onActionForward(ActionEvent event)
	{
		System.out.println("onActionForward");
	}

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
	void onActionRewind(ActionEvent event)
	{
		System.out.println("onActionRewind");
	}
}
