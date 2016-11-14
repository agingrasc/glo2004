package org.glo.giftw.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class OpenStrategyController
{
	@FXML
	private VBox rootVBox;
	
	@FXML
	private ListView<?> listView;

	@FXML
	private void initialize() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/ListPlaceHolder.fxml"));
		StackPane listPlaceHolder = loader.load();
		listView.setPlaceholder(listPlaceHolder);
	}

	public VBox getRootVBox()
	{
		return rootVBox;
	}
}
