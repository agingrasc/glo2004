package org.glo.giftw.view;

import java.util.Observable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OpenStrategyToolBarController extends Observable
{	
	@FXML
	void onActionDelete(ActionEvent event)
	{
		System.out.println("onActionDelete");
	}
	
	@FXML
	void onActionConfigure(ActionEvent event)
	{
		System.out.println("onActionConfigure");
	}
}
