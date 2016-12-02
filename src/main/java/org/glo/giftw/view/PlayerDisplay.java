package org.glo.giftw.view;

import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PlayerDisplay extends VBox implements ViewableGameObject
{
	private Label name;
	private Label role;
	private Canvas canvas;
	
	public PlayerDisplay(String color, double xPos, double yPos)
	{
		this.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");//VBox transparent
		name = new Label("Nom");
		role = new Label("Role");
		canvas = new Canvas(32,32);
		GraphicsContext gc = canvas.getGraphicsContext2D();
    	gc.setFill(Color.web(color));
    	gc.fillOval(0, 0, 32, 32);
    	this.getChildren().add(name);
    	this.getChildren().add(role);
    	this.getChildren().add(canvas);
    	this.relocate(xPos, yPos);
	}
	
	public void setName(String name)
	{
		this.name.setText(name);
	}
	
	public void setRole(String role)
	{
		this.role.setText(role);
	}
	
	public void setCanvasColor(String color)
	{
		this.canvas.getGraphicsContext2D().setFill(Color.web(color));
	}
	
	public Image getSnapshot()
	{
		new Scene(this);//Pas le choix pour snapshot
    	SnapshotParameters parameters = new SnapshotParameters();
    	parameters.setFill(Color.TRANSPARENT);
    	WritableImage snapshot = this.snapshot(parameters, null);
    	return snapshot;
	}
	
	public void setShowName(boolean show)
	{
		if(show)
		{
			name.setVisible(true);
		}
		else
		{
			name.setVisible(false);
		}
	}
	
	public void setShowRole(boolean show)
	{
		if(show)
		{
			role.setVisible(true);
		}
		else
		{
			role.setVisible(false);
		}
	}
	
	public void setPosition(double xPos, double yPos)
	{
		this.relocate(xPos, yPos);
	}
	
	public Canvas getCanvas()
	{
		return this.canvas;
	}
}
