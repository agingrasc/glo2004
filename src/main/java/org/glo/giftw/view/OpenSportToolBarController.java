package org.glo.giftw.view;

import java.util.Observable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OpenSportToolBarController extends Observable
{
	@FXML
	void onActionDelete(ActionEvent event)
	{
		System.out.println("onActionDelete");
	}

	@FXML
	void onActionConfigureSport(ActionEvent event)
	{
		String handler = "onActionConfigureSport";
		System.out.println(handler);

		setChanged();
		notifyObservers(handler);
	}
}