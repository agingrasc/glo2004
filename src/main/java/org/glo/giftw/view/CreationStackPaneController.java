package org.glo.giftw.view;

import java.io.File;
import java.io.IOException;

import javafx.scene.input.*;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.Dragable;
import org.glo.giftw.domain.strategy.GameObject;
import org.glo.giftw.domain.strategy.Obstacle;
import org.glo.giftw.domain.strategy.Player;
import org.glo.giftw.domain.strategy.Projectile;
import org.glo.giftw.domain.strategy.Team;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import org.glo.giftw.domain.util.Vector;

public class CreationStackPaneController
{
	@FXML
	private StackPane stackPane;
	
	@FXML
	private Group mainGroup;
	
	private Pane currentPane;
	
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
			if (event.getDragboard().hasString())
			{
				event.acceptTransferModes(TransferMode.ANY);//different pour autre cas? 
			}

			event.consume();
		});
		
		//TODO
		currentPane.setOnDragDropped((DragEvent event) -> {
			Dragboard db = event.getDragboard();
			boolean success = false;
			if (db.hasString())
			{
				Dragable item = Controller.getInstance().getDraggedObject(db.getString());
				Vector coord = Controller.getInstance().getFieldCoordinate(new Vector(event.getX(),event.getY()), ratioPixelToUnit);
				if(item instanceof Obstacle)
				{
					if(event.getGestureSource() instanceof TableView<?>)
					{
						
					}
					Controller.getInstance().addObstacle(db.getString(), coord, 0, new Vector(32,32));
					File imageFile = new File(((Obstacle)item).getImagePath());
	                Image image = new Image(imageFile.toURI().toString(), 32, 32, false, false);
					ImageView imageView = new ImageView(image);
					imageView.setX(event.getX());
					imageView.setY(event.getY());
					setDragDetected(imageView);
					currentPane.getChildren().add(imageView);
					
				}
				else if(item instanceof Projectile)
				{
					Controller.getInstance().addProjectile(coord, 0, new Vector(ratioPixelToUnit.getX()/16,ratioPixelToUnit.getY()/16));
					File imageFile = new File(((Projectile)item).getImagePath());
	                Image image = new Image(imageFile.toURI().toString(), 16, 16, false, false);
					ImageView imageView = new ImageView(image);
					imageView.setX(event.getX());
					imageView.setY(event.getY());
					setDragDetected(imageView);
					currentPane.getChildren().add(imageView);
				}
				else if(item instanceof Team)
				{
					try
					{
						Controller.getInstance().addPlayer(coord, 0, new Vector(ratioPixelToUnit.getX()/32,ratioPixelToUnit.getY()/32), db.getString());
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource(FXMLPaths.PLAYER_DISPLAY_PATH.toString()));
						VBox playerDisplay = loader.load();
						PlayerDisplayController pdc = loader.getController();
						Canvas canvas = pdc.getCanvas();
	                	GraphicsContext gc = canvas.getGraphicsContext2D();
	                	gc.setFill(Color.web(((Team) item).getColour()));
	                	gc.fillOval(0, 0, 32, 32);
						playerDisplay.relocate(event.getX(), event.getY());
						setDragDetected(playerDisplay);
						currentPane.getChildren().add(playerDisplay);
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
	
	//TODO
	private void setDragDetected(Node node)
	{
		node.setOnDragDetected(new EventHandler<MouseEvent>() { //drag
	        @Override
	        public void handle(MouseEvent event) {
	            // drag was detected, start drag-and-drop gesture
	        	System.out.println(event.getX()/ratioPixelToUnit.getX());
	        	System.out.println(event.getY()/ratioPixelToUnit.getY());
	            GameObject selected = Controller.getInstance().getGameObjectByCoordinate(new Vector(event.getX(),event.getY()), ratioPixelToUnit);
	            System.out.println(selected);
	            if(selected != null)
	            {	
	            	Dragboard db = currentPane.startDragAndDrop(TransferMode.MOVE);
                	ClipboardContent content = new ClipboardContent();
                	Image image = null;
	            	if(selected instanceof Obstacle)
	            	{
	            		File imageFile = new File(((Obstacle) selected).getImagePath());
	            		image = new Image(imageFile.toURI().toString());
	            	}
	            	else if(selected instanceof Projectile)
	            	{
	            		File imageFile = new File(((Projectile) selected).getImagePath());
	            		image = new Image(imageFile.toURI().toString());
	            	}
	            	else if(selected instanceof Player)
	            	{
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource(FXMLPaths.PLAYER_DISPLAY_PATH.toString()));
						GridPane playerDisplay = null;
						try
						{
							playerDisplay = loader.load();
							PlayerDisplayController pdc = loader.getController();
							Canvas canvas = pdc.getCanvas();
		                	GraphicsContext gc = canvas.getGraphicsContext2D();
		                	String teamName = Controller.getInstance().getPlayerTeam(selected);
		                	gc.setFill(Color.web(Controller.getInstance().getTeamColour(teamName)));
		                	double x = Controller.getInstance().getCurrentFrame().getDimensions(selected).getX()*ratioPixelToUnit.getX();
		                	double y = Controller.getInstance().getCurrentFrame().getDimensions(selected).getY()*ratioPixelToUnit.getY();
		                	gc.fillOval(0, 0, x, y);
		                	WritableImage writableImage = playerDisplay.snapshot(new SnapshotParameters(), null);
		                	image = writableImage;
		                	
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            	}
	            	db.setDragView(image);
 	                content.putString(String.valueOf(selected.getId()));
 	                db.setContent(content);
 	                event.consume(); 
	               
	            }
	        }
	    });
	}
	
	public void zoomIn()
	{
		Scale scaleTransform = new Scale(1.25, 1.25, 0, 0);
		stackPane.getTransforms().add(scaleTransform);
	}
	
	public void zoomOut()
	{
		Scale scaleTransform = new Scale(0.8, 0.8, 0, 0);
		stackPane.getTransforms().add(scaleTransform);
	}
	
	@FXML
    void onKeyPressed(KeyEvent event) 
	{
		if(event.getCode() == KeyCode.CONTROL)
		{
			ctrlPressed = true;
		}
    }

    @FXML
    void onKeyReleased(KeyEvent event) 
    {
    	if(event.getCode() == KeyCode.CONTROL)
		{
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
