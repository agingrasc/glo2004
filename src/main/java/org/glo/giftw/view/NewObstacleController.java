package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import java.io.File;

public class NewObstacleController
{
	@FXML
	private ImageView imagePreview;

	@FXML
	private DialogPane newObstacleDialog;

	@FXML
	void onActionBrowse(ActionEvent event)
	{
		Window parentWindow = newObstacleDialog.getScene().getWindow();
		ImageFileController imageFileController = new ImageFileController();

		File imageToOpen = imageFileController.startOpenFileDialog(parentWindow);
		imagePreview.setImage(new Image(imageToOpen.toURI().toString()));
		System.out.println("onActionBrowse");
	}
}