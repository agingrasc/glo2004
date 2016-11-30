package org.glo.giftw.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.util.Vector;

import java.io.File;
import java.io.IOException;

public class CreationStackPaneController
{
	@FXML
	private StackPane stackPane;
	
	@FXML
	private Group mainGroup;
	
	private Pane currentPane;
	
	private double zoomFactor = 1;
	
	boolean ctrlPressed = false;
	
	@FXML
    private ScrollPane scrollPane;

	private Vector ratioPixelToUnit;

	@FXML
	void onMouseMoved(MouseEvent event) throws IOException
	{
	    BottomToolBarController bottomToolBarController = RootLayoutController.getInstance().getBottomToolBarController();
	    Vector adjCoord = new Vector(event.getX(), event.getY());
        bottomToolBarController.updateCoordinate(adjCoord, this.ratioPixelToUnit);
	}

	public void init()
	{
		 scrollPane.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
		        @Override
		        public void handle(ScrollEvent event) 
		        {
		        	if(ctrlPressed == true)
		    		{
		    			System.out.println("Scrolling");
		    			if (event.getDeltaY() == 0) 
		    			{
		    				return;
		    			}
		    			
		    			if(event.getDeltaY() < 0)
		    			{
		    				zoomOut();
		    			}
		    			else
		    			{
		    				zoomIn();
		    			}
		    		}
		        	event.consume();
		        }
		    });
		
		File file = new File(Controller.getInstance().getSportFieldImagePath());
		Image sportFieldImage = new Image(file.toURI().toString());
		ImageView fieldBackground = new ImageView(sportFieldImage);
		fieldBackground.setPreserveRatio(true);
		fieldBackground.setFitHeight(scrollPane.getViewportBounds().getHeight());
		fieldBackground.setFitWidth(scrollPane.getViewportBounds().getWidth());
		stackPane.getChildren().add(fieldBackground);
		
		createNewFrame();
		
		//FIXME: trouver dynamiquement la taille restreignante
		Vector fieldDimensions = Controller.getInstance().getFieldDimensions();
		double adjustedHeight = stackPane.getPrefHeight();
		double imgHeight = sportFieldImage.getHeight();
		double ratio = adjustedHeight/imgHeight;
		double adjustedWidth = sportFieldImage.getWidth() * ratio;
		this.ratioPixelToUnit = new Vector(adjustedWidth/fieldDimensions.getX(), adjustedHeight/fieldDimensions.getY());
	}
	
	private void createNewFrame()
	{
		Controller.getInstance().createNewFrame();
		currentPane = new Pane();
		currentPane.setBackground(Background.EMPTY);
		stackPane.getChildren().add(currentPane);
		
		currentPane.setOnDragOver((DragEvent event) -> {
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
		stackPane.getTransforms().add(scaleTransform);
		zoomFactor *= 1.25;
	}
	
	public void zoomOut()
	{
		Scale scaleTransform = new Scale(0.75, 0.75, 0, 0);
		stackPane.getTransforms().add(scaleTransform);
		zoomFactor *= 0.75;
	}
	
	@FXML
    void onKeyPressed(KeyEvent event) 
	{
		if(event.getCode() == KeyCode.CONTROL)
		{
			System.out.println("pressed");
			ctrlPressed = true;
		}
    }

    @FXML
    void onKeyReleased(KeyEvent event) 
    {
    	if(event.getCode() == KeyCode.CONTROL)
		{
    		System.out.println("unpressed");
			ctrlPressed = false;
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
