package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.glo.giftw.controller.Controller;

public class NewSportController
{
	private File sportFieldImageFile;

	@FXML
	private DialogPane rootDialogPane;
	
	@FXML
    private TextField sportName;

    @FXML
    private TextField fieldX;

    @FXML
    private TextField fieldY;

    @FXML
    private ComboBox<String> roles;

    @FXML
    private TextField maxPlayers;

    @FXML
    private TextField maxTeams;

    @FXML
    private TextField projectileName;

	File getSportFieldImageFile() { return sportFieldImageFile; }

	@FXML
	void onActionDraw(ActionEvent event) throws IOException
	{
		System.out.println("onActionDraw");

		Dialog<Object> dialog = new Dialog<Object>();

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource(FXMLPaths.FIELD_EDITOR_PATH.toString()));
		DialogPane fieldEditorDialogPane = loader.load();

		dialog.setDialogPane(fieldEditorDialogPane);
		dialog.showAndWait();

	}

	@FXML
	void onActionBrowseField(ActionEvent event)
	{
		System.out.println("onActionBrowseField");
		Window parentWindow = rootDialogPane.getScene().getWindow();
		OpenImageFileController openImageFileController = new OpenImageFileController();

		File imageToOpen = openImageFileController.startDialog(parentWindow);
		sportFieldImageFile = imageToOpen;
	}
	
	@FXML
	void onActionBrowseProjectile(ActionEvent event)
	{
		System.out.println("onActionBrowseField");
	}
	
	public void showDialog() throws IOException
	{
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setDialogPane(rootDialogPane);
		Optional<ButtonType> result = dialog.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.FINISH)
		{
			//TODO en attente du projectileImageFileFile et de l'ajout du parametre pour le sportFieldImageFile 
			/*Controller.getInstance().createSport(sportName.getText(), sportFieldImageFile.getPath(), roles.getItems(),
			Integer.parseInt(fieldX.getText()), Integer.parseInt(fieldY.getText()), projectileName.getText(),
			projectileImageFile.getPath(), Integer.parseInt(maxPlayers.getText()), Integer.parseInt(maxTeams.getText()));*/
			
			RootLayoutController.getInstance().getOpenSportController().updateTable();
		}
	}
}