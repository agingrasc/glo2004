package org.glo.giftw.view;

import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.Dragable;
import org.glo.giftw.domain.strategy.Obstacle;
import org.glo.giftw.domain.strategy.Projectile;
import org.glo.giftw.domain.strategy.Team;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

public class FrameView extends Pane
{
	public FrameView()
	{
		this.setBackground(Background.EMPTY);
		
		this.setOnDragOver((DragEvent event) -> {
			if (event.getDragboard().hasString())
			{
				event.acceptTransferModes(TransferMode.ANY);//different pour autre cas? 
			}

			event.consume();
		});
		
		this.setOnDragDropped(new EventHandler<DragEvent>() 
		{
		    public void handle(DragEvent event) 
		    {
		        Dragboard db = event.getDragboard();
		        boolean success = false;
		        if (db.hasString()) 
		        {
		        	Dragable dragObject = Controller.getInstance().getDraggedObject(db.getString());
		        	double mouseX = event.getX();
		        	double mouseY = event.getY();
		        	ViewableGameObject viewObject;
		        	if(dragObject instanceof Obstacle)
		        	{
		        		viewObject = new ObstacleDisplay(((Obstacle) dragObject).getImagePath(), mouseX, mouseY);
		        	}
		        	else if(dragObject instanceof Projectile)
		        	{
		        		viewObject = new ProjectileDisplay(((Projectile) dragObject).getImagePath(), mouseX, mouseY);
		        	}
		        	else if(dragObject instanceof Team)
		        	{
		        		viewObject = new PlayerDisplay(((Team) dragObject).getColour(), mouseX, mouseY);
		        	}
		        	success = true;
		        }
		        event.setDropCompleted(success);
		        
		        event.consume();
		    }
		});
	}
	
	public void addViewObject(ViewableGameObject viewableGameObject)
	{
		//this.getChildren().add(viewableGameObject);
	}
}
