package org.glo.giftw.view;

import java.util.Observable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ModeToolBarController extends Observable
{
	@FXML
	void onActionImageByImage(ActionEvent event) 
	{
		String handler = "onActionImageByImage";
		System.out.println(handler);

		setChanged();
		notifyObservers(handler);
	}
	
	@FXML
	void onActionRealTime(ActionEvent event) 
	{
		String handler = "onActionRealTime";
		System.out.println(handler);

		setChanged();
		notifyObservers(handler);
	}
	
	@FXML
	void onActionWatch(ActionEvent event) 
	{
		String handler = "onActionWatch";
		System.out.println(handler);

		setChanged();
		notifyObservers(handler);
	}
}
