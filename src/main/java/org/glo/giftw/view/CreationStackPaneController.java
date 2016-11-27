package org.glo.giftw.view;

import java.io.IOException;

import org.glo.giftw.controller.Controller;
import org.glo.giftw.domain.Strategy;

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
    
    private Strategy strategy;
    
    @FXML
	private void initialize() throws IOException
	{
    	displayNewFrame();
	}

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
	
	private void displayNewFrame()
	{
		Controller.getInstance().createNewFrame();
	}

	public StackPane getRootStackPane()
	{
		return rootStackPane;
	}

	public void setStrategy(Strategy strategy)
	{
		this.strategy = strategy;
	}
}
