package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

public class NewSportController
{
	private File sportFieldImageFile;

	@FXML
	private DialogPane newSportDialog;

	File getSportFieldImageFile() { return sportFieldImageFile; }

	@FXML
	void onActionDelete(ActionEvent event)
	{
		Window parentWindow = newSportDialog.getScene().getWindow();
		System.out.println("onActionDelete");
		OpenImageFileController openImageFileController = new OpenImageFileController();

		File imageToOpen = openImageFileController.startDialog(parentWindow);
		sportFieldImageFile = imageToOpen;
	}

	@FXML
	void onActionModify(ActionEvent event) throws IOException
	{
		System.out.println("onActionModify");

		Dialog<Object> dialog = new Dialog<Object>();

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("/fxml/FieldEditor.fxml"));
		DialogPane fieldEditorDialogPane = loader.load();

		//fieldEditorController.initialize();

		dialog.setDialogPane(fieldEditorDialogPane);
		dialog.showAndWait();
	}
}