package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class NewSportController
{
	private File sportFieldImageFile;

	@FXML
	private DialogPane newSportDialog;

	File getSportFieldImageFile() { return sportFieldImageFile; }

	@FXML
	void onActionDraw(ActionEvent event) throws IOException
	{
		System.out.println("onActionDraw");

		Dialog<Object> dialog = new Dialog<Object>();

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("/fxml/FieldEditor.fxml"));
		DialogPane fieldEditorDialogPane = loader.load();

		dialog.setDialogPane(fieldEditorDialogPane);
		dialog.showAndWait();

	}

	@FXML
	void onActionBrowseField(ActionEvent event)
	{
		System.out.println("onActionBrowseField");
		Window parentWindow = newSportDialog.getScene().getWindow();
		OpenImageFileController openImageFileController = new OpenImageFileController();

		File imageToOpen = openImageFileController.startDialog(parentWindow);
		sportFieldImageFile = imageToOpen;
	}
	
	@FXML
	void onActionBrowseProjectile(ActionEvent event)
	{
		System.out.println("onActionBrowseField");
	}
	
	public void showDialog(DialogPane dialogPane)
	{
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setDialogPane(dialogPane);
		Optional<ButtonType> result = dialog.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.FINISH)
		{
			//TODO Mettre a jour le domaine via le controleur
			//Et mettre a jour la vue...
		}
	}
}