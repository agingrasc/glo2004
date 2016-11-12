package org.glo.giftw.view;

import java.io.IOException;
import java.util.Observable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DefaultToolBarController extends Observable
{	
	@FXML
	void onActionNewObstacle(ActionEvent event) throws IOException
	{
		String handler = "onActionNewObstacle";
		System.out.println(handler);

		setChanged();
		notifyObservers(handler);
	}

	@FXML
	void onActionNewSport(ActionEvent event) throws IOException
	{
		String handler = "onActionNewSport";
		System.out.println(handler);

		setChanged();
		notifyObservers(handler);
	}

	@FXML
	void onActionNewStrategy(ActionEvent event) throws IOException
	{
		String handler = "onActionNewStrategy";
		System.out.println(handler);

		setChanged();
		notifyObservers(handler);
	}

	@FXML
	void onActionOpenObstacle(ActionEvent event) throws IOException
	{
		String handler = "onActionOpenObstacle";
		System.out.println(handler);

		setChanged();
		notifyObservers(handler);
	}

	@FXML
	void onActionOpenSport(ActionEvent event) throws IOException
	{
		String handler = "onActionOpenSport";
		System.out.println(handler);

		setChanged();
		notifyObservers(handler);
	}

	@FXML
	void onActionOpenStrategy(ActionEvent event) throws IOException
	{
		String handler = "onActionOpenStrategy";
		System.out.println(handler);

		setChanged();
		notifyObservers(handler);
	}

	@FXML
	void onActionSave(ActionEvent event)
	{
		System.out.println("onActionSave");
	}
}
