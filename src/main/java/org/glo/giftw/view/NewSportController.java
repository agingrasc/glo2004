package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

public class NewSportController
{
	private File sportFieldImageFile;

	@FXML
	private DialogPane newSportDialog;

	@FXML
	private ImageView fieldImage;

	File getSportFieldImageFile() { return new File(fieldImage.getImage().toString()); }

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
	void onActionBrowse(ActionEvent event)
	{
		System.out.println("onActionBrowse");
		Window parentWindow = newSportDialog.getScene().getWindow();
		ImageFileController imageFileController = new ImageFileController();

		File imageToOpen = imageFileController.startOpenFileDialog(parentWindow);
		sportFieldImageFile = imageToOpen;
		fieldImage.setImage(new Image(imageToOpen.toURI().toString()));
	}
}