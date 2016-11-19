package org.glo.giftw.view;

import java.io.IOException;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class OpenSportController
{
	@FXML
	private VBox rootVBox;

	@FXML
	private TableView<HashMap.Entry<String, String>> tableView;

	@FXML
	private TableColumn<HashMap.Entry<String, String>, String> imageColumn;

	@FXML
	private TableColumn<HashMap.Entry<String, String>, String> nameColumn;

	ObservableList<HashMap.Entry<String, String>> data = FXCollections.observableArrayList();

	@FXML
	private void initialize() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(FXMLPaths.LIST_PLACE_HOLDER.toString()));
		Label listPlaceHolder = loader.load();
		tableView.setPlaceholder(listPlaceHolder);
	}

	public void updateList()
	{
		// TODO
		// Controller.getInstance().getSportPool()
		// update la liste
	}

	public VBox getRootVBox()
	{
		return rootVBox;
	}
}