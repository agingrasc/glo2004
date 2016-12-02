package org.glo.giftw.view;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ObstacleDisplay extends ImageView
{
	public ObstacleDisplay(String path, double xPos, double yPos)
	{
		File imageFile = new File(path);
        Image image = new Image(imageFile.toURI().toString(), 32, 32, true, false);
		this.setImage(image);
		this.setX(xPos);
		this.setY(yPos);
	}
	
	public void setPosition(double xPos, double yPos)
	{
		this.setX(xPos);
		this.setY(yPos);
	}
}
