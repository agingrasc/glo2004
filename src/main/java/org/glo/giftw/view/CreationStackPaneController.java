package org.glo.giftw.view;

import java.io.File;
import java.io.IOException;

import javafx.scene.input.*;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.strategy.Obstacle;
import org.glo.giftw.domain.strategy.Projectile;
import org.glo.giftw.domain.strategy.Team;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
	    Vector adjCoord = new Vector(event.getX(), event.getY());
        bottomToolBarController.updateCoordinate(adjCoord, this.ratioPixelToUnit);
	}

	public void displayNewFrame()
	{
		Controller.getInstance().createNewFrame();
		File file = new File(Controller.getInstance().getSportFieldImagePath());
		Image sportFieldImage = new Image(file.toURI().toString());
		BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		stackPane.setBackground(new Background(new BackgroundImage(sportFieldImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,null,bgSize)));
		stackPane.setPrefSize(scrollPane.getWidth(),scrollPane.getHeight());

		//FIXME: trouver dynamiquement la taille restreignante
		Vector fieldDimensions = Controller.getInstance().getFieldDimensions();
		double adjustedHeight = stackPane.getPrefHeight();
		double imgHeight = sportFieldImage.getHeight();
		double ratio = adjustedHeight/imgHeight;
		double adjustedWidth = sportFieldImage.getWidth() * ratio;
		this.ratioPixelToUnit = new Vector(adjustedWidth/fieldDimensions.getX(), adjustedHeight/fieldDimensions.getY());

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
			if (event.getDragboard().hasString())
			{
				event.acceptTransferModes(TransferMode.ANY);
			}

			event.consume();
		});
		
		currentPane.setOnDragDropped((DragEvent event) -> {
			Dragboard db = event.getDragboard();
			boolean success = false;
			if (db.hasString())
			{
				//Draggable item = Controller.getInstance().getDraggableItem(db.getString());
				Vector coord = Controller.getInstance().getFieldCoordinate(new Vector(event.getX(),event.getY()), ratioPixelToUnit);
				if(item instanceof )
				{
					Controller.getInstance().addObstacle(db.getString(), coord, 0, new Vector(32,32));
					Object obj = db.getContent(new DataFormat("Obstacle"));
					Obstacle obstacle = (Obstacle)obj;
					File imageFile = new File(obstacle.getImagePath());
	                Image image = new Image(imageFile.toURI().toString(), 32, 32, false, false);
					ImageView imageView = new ImageView(image);
					imageView.setX(event.getX());
					imageView.setY(event.getY());
					currentPane.getChildren().add(imageView);
				}
				else if(db.getContent(new DataFormat("Projectile")) instanceof Projectile)
				{
					Controller.getInstance().addProjectile(coord, 0, new Vector(32,32));
					Object obj = db.getContent(new DataFormat("Obstacle"));
					Projectile projectile = (Projectile)obj;
					File imageFile = new File(projectile.getImagePath());
	                Image image = new Image(imageFile.toURI().toString(), 32, 32, false, false);
					ImageView imageView = new ImageView(image);
					imageView.setX(event.getX());
					imageView.setY(event.getY());
					currentPane.getChildren().add(imageView);
				}
				else if(db.getContent(new DataFormat("Team")) instanceof Team)
				{
					try
					{
						Controller.getInstance().addPlayer(coord, 0, new Vector(32,32), db.getString());
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource(FXMLPaths.PLAYER_DISPLAY_PATH.toString()));
						GridPane playerDisplay = loader.load();
						currentPane.getChildren().add(playerDisplay);
						playerDisplay.relocate(event.getX(), event.getY());
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
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
