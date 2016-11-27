package org.glo.giftw.view;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.*;
import org.glo.giftw.domain.Controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import org.glo.giftw.domain.util.Vector;

public class CreationStackPaneController
{
	@FXML
	private StackPane stackPane;
	
	@FXML
	private Group mainGroup;
	
	@FXML
	private Group subGroup;
	
	private Pane currentPane;
	
	private double zoomFactor = 1;
	
	@FXML
    private ScrollPane scrollPane;

	private Vector ratioPixelToUnit;

	@FXML
	void onMouseMoved(MouseEvent event) throws IOException
	{
	    BottomToolBarController bottomToolBarController = RootLayoutController.getInstance().getBottomToolBarController();
        bottomToolBarController.updateCoordinate(event, this.ratioPixelToUnit);
	}

	public void displayNewFrame()
	{
		Controller.getInstance().createNewFrame();
		File file = new File(Controller.getInstance().getSportFieldImagePath());
		Image sportFieldImage = new Image(file.toURI().toString());
		BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		stackPane.setBackground(new Background(new BackgroundImage(sportFieldImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,null,bgSize)));
		stackPane.setPrefSize(scrollPane.getWidth(),scrollPane.getHeight());

		Vector fieldDimensions = Controller.getInstance().getFieldDimensions();
		double width = sportFieldImage.getWidth();
		double height = stackPane.getHeight();
		System.out.println(String.format("RatioPixelTounit -- width: %f -- height: %f", width, height));
		this.ratioPixelToUnit = new Vector(width/fieldDimensions.getX(), height/fieldDimensions.getY());

		createCurrentGroup();
	}
	
	private void createCurrentGroup()
	{
		currentPane = new Pane();
		currentPane.setBackground(Background.EMPTY);
		stackPane.getChildren().add(currentPane);
		currentPane.prefWidthProperty().bind(stackPane.widthProperty());
		currentPane.prefHeightProperty().bind(stackPane.heightProperty());
		
		currentPane.setOnDragOver((DragEvent event) -> {
			System.out.println("hey2");
			if (event.getDragboard().hasImage())
			{
				event.acceptTransferModes(TransferMode.COPY);
			}

			event.consume();
		});
		
		currentPane.setOnDragDropped((DragEvent event) -> {
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
		});
	}
	
	public void zoomIn()
	{
		Scale scaleTransform = new Scale(1.25, 1.25, 0, 0);
		subGroup.getTransforms().add(scaleTransform);
		zoomFactor *= 1.25;
	}
	
	public void zoomOut()
	{
		Scale scaleTransform = new Scale(0.75, 0.75, 0, 0);
		subGroup.getTransforms().add(scaleTransform);
		zoomFactor *= 0.75;
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
