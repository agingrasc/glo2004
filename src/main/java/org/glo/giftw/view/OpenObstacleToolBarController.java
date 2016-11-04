package org.glo.giftw.view;

import java.util.Observable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OpenObstacleToolBarController extends Observable
{
	@FXML
	void onActionDelete(ActionEvent event)
	{
		System.out.println("onActionDelete");
	}

	@FXML
	void onActionConfigureObstacle(ActionEvent event)
	{
		String handler = "onActionConfigureObstacle";
		System.out.println(handler);

		setChanged();
		notifyObservers(handler);
	}
}