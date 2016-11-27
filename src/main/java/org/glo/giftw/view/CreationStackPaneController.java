package org.glo.giftw.view;

import java.io.File;

import org.glo.giftw.domain.Controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;

public class CreationStackPaneController
{
	@FXML
	private StackPane stackPane;
	
	@FXML
	private Group mainGroup;
	
	@FXML
	private Group subGroup;
	
	private Pane currentPane;
	
	@FXML
    private ScrollPane scrollPane;


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
			currentPane.getChildren().add(imageView);
			success = true;
		}
		event.setDropCompleted(success);

		event.consume();
	}
	
	public void displayNewFrame()
	{
		Controller.getInstance().createNewFrame();
		File file = new File(Controller.getInstance().getSportFieldImagePath());
		Image sportFieldImage = new Image(file.toURI().toString());
		BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		stackPane.setBackground(new Background(new BackgroundImage(sportFieldImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,null,bgSize)));
		stackPane.setPrefSize(scrollPane.getWidth(),scrollPane.getHeight());
		stackPane.getChildren().add(new Label("LABEL"));
	}
	
	public void zoomIn()
	{
		Scale scaleTransform = new Scale(1.25, 1.25, 0, 0);
		subGroup.getTransforms().add(scaleTransform);
	}
	
	public void zoomOut()
	{
		Scale scaleTransform = new Scale(0.75, 0.75, 0, 0);
		subGroup.getTransforms().add(scaleTransform);
	}
	
	@FXML
    void onScroll(ScrollEvent event) 
	{
		if (event.getDeltaY() == 0) 
		{
			return;
		}
		
		if(event.getDeltaY() < 0)
		{
			zoomIn();
		}
		else
		{
			zoomOut();
		}
	}

	public StackPane getStackPane()
	{
		return stackPane;
	}

	public ScrollPane getScrollPane()
	{
		return scrollPane;
	}
}
