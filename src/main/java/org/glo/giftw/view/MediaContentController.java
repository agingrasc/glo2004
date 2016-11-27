package org.glo.giftw.view;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.glo.giftw.domain.Controller;

import java.io.File;

public class MediaContentController
{
	@FXML
	private StackPane rootStackPane;

	public void displayNewFrame()
	{
		rootStackPane.getChildren().clear();
		Controller.getInstance().createNewFrame();
		File file = new File(Controller.getInstance().getSportFieldImagePath());
		Image sportFieldImage = new Image(file.toURI().toString());
		BackgroundSize bgSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		BackgroundPosition bgPos = BackgroundPosition.CENTER;
		rootStackPane.setBackground(new Background(new BackgroundImage(sportFieldImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, bgPos, bgSize)));
	}

	public StackPane getRootStackPane()
	{
		return rootStackPane;
	}
}
