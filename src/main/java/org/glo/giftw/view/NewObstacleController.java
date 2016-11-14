package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import java.io.File;
import java.util.Optional;

public class NewObstacleController
{
	@FXML
	private ImageView imagePreview;

	@FXML
	private DialogPane rootDialogPane;

	@FXML
	void onActionBrowse(ActionEvent event)
	{
		Window parentWindow = rootDialogPane.getScene().getWindow();
		OpenImageFileController openImageFileController = new OpenImageFileController();

		File imageToOpen = openImageFileController.startDialog(parentWindow);
		imagePreview.setImage(new Image(imageToOpen.toURI().toString()));
		System.out.println("onActionBrowse");
	}
	
	public void showDialog()
	{
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setDialogPane(rootDialogPane);
		Optional<ButtonType> result = dialog.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.FINISH)
		{
			//TODO Mettre a jour le domaine via le controleur
			//Et mettre a jour la vue...
		}
	}
}