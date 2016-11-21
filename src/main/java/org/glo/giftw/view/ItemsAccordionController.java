package org.glo.giftw.view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class ItemsAccordionController
{
	@FXML
	private Accordion rootAccordion;
	
	@FXML
	private ListView<String> playersListView;

	@FXML
	private ListView<String> obstaclesListView;

	@FXML
	private ListView<String> projectilesListView;
	
	private ObservableList<String> players = FXCollections.observableArrayList("joueur1");

	private ObservableList<Image> playersImages = FXCollections
	        .observableArrayList(new Image("/images/chevron-sign-left.png"));

	@FXML
	private void initialize() throws IOException
	{
		playersListView.setItems(players);

		playersListView.setCellFactory(param -> new PlayerCell());
	}

	public Accordion getRootAccordion()
	{
		return rootAccordion;
	}

	private class PlayerCell extends ListCell<String>
	{
		private final ImageView imageView = new ImageView();

		public PlayerCell()
		{
			setOnDragDetected(event ->
			{
				if (getItem() == null)
				{
					return;
				}

				ObservableList<String> items = getListView().getItems();

				Dragboard dragboard = startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				content.putImage(playersImages.get(items.indexOf(getItem())));
				dragboard.setDragView(playersImages.get(items.indexOf(getItem())));
				dragboard.setContent(content);

				event.consume();
			});
		}

		@Override
		protected void updateItem(String item, boolean empty)
		{
			super.updateItem(item, empty);

			if (empty || item == null)
			{
				setGraphic(null);
			} else
			{
				imageView.setImage(playersImages.get(getListView().getItems().indexOf(item)));
				setText(item);
				setGraphic(imageView);
			}
		}
	}
}
