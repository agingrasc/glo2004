package org.glo.giftw.view;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CreationStackPaneController
{
	@FXML
	private StackPane rootStackPane;
	
	@FXML
	private VBox vBox;
	
    @FXML
    private Pane pane;

	@FXML
	void onDragOver(DragEvent event)
	{
		if (event.getDragboard().hasImage())
		{
			event.acceptTransferModes(TransferMode.COPY);
		}

		event.consume();
	}

	@FXML
	void onDragDropped(DragEvent event)
	{
		Dragboard db = event.getDragboard();
		boolean success = false;
		if (db.hasImage())
		{
			ImageView imageView = new ImageView(db.getImage());
			imageView.setX(event.getX());
			imageView.setY(event.getY());
			pane.getChildren().add(imageView);
			success = true;
		}
		event.setDropCompleted(success);

		event.consume();
	}

	@FXML
	void onDragEntered(DragEvent event)
	{
		vBox.setVisible(false);
	}

	public StackPane getRootStackPane()
	{
		return rootStackPane;
	}
}
