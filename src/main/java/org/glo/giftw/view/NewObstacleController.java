package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
	void onActionModify(ActionEvent event)
	{
		System.out.println("onActionModify");
	}

	@FXML
	void onActionOpen(ActionEvent event)
	{
		Window parentWindow = newObstacleDialog.getScene().getWindow();
		OpenImageFileController openImageFileController = new OpenImageFileController();

		File imageToOpen = openImageFileController.startDialog(parentWindow);
		imagePreview.setImage(new Image(imageToOpen.toURI().toString()));
	}
}