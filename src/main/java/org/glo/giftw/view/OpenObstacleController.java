package org.glo.giftw.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

public class OpenObstacleController
{

	@FXML
	private ListView<?> listView;

	@FXML
	private void initialize() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/ListPlaceHolder.fxml"));
		StackPane listPlaceHolder = loader.load();
		System.out.println(listView);
		listView.setPlaceholder(listPlaceHolder);
	}

}
