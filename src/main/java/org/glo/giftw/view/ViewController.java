package org.glo.giftw.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

public class ViewController
{
	public void newObstacle() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/NewObstacle.fxml"));
		DialogPane newObstacle = loader.load();
		Dialog<Object> dialog = new Dialog<Object>();
		dialog.setDialogPane(newObstacle);
		dialog.showAndWait();
	}
}
