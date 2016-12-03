package org.glo.giftw.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PlayerDisplay extends VBox
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

	public void setCanvasColor(String color)
	{
		this.canvas.getGraphicsContext2D().setFill(Color.web(color));
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
