package org.glo.giftw.view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class OpenSportController
{
	@FXML
	private VBox rootVBox;

	@FXML
	private ListView<String> listView;

	ObservableList<String> data = FXCollections.observableArrayList("path");

	@FXML
	private void initialize() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(FXMLPaths.LIST_PLACE_HOLDER.toString()));
		StackPane listPlaceHolder = loader.load();
		listView.setPlaceholder(listPlaceHolder);

		listView.setItems(data);

		listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>()
		{
			@Override
			public ListCell<String> call(ListView<String> listView)
			{
				return new SportCell();
			}
		});
	}

	static class SportCell extends ListCell<String>
	{
		@Override
		public void updateItem(String item, boolean empty)
		{
			try
			{
				super.updateItem(item, empty);
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(FXMLPaths.OPEN_LIST_ITEM.toString()));
				GridPane gridPane;
				gridPane = (GridPane) loader.load();

				OpenListItemController openListItemController = loader.getController();

				if (item != null)
				{
					// openListItemController.getImageView().setImage(new Image(item));
					setGraphic(gridPane);
				}
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public VBox getRootVBox()
	{
		return rootVBox;
	}
}