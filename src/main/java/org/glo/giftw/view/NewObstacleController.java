package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.glo.giftw.controller.Controller;

public class NewObstacleController
{	
	@FXML
	private ImageView imagePreview;

	@FXML
	private DialogPane rootDialogPane;
	
	@FXML
	private CheckBox isCollision;
	
	@FXML
	private TextField obstacleName;
	
	private File imageToOpen;

	@FXML
	void onActionBrowse(ActionEvent event)
	{
		Window parentWindow = rootDialogPane.getScene().getWindow();
		ImageFileController imageFileController = new ImageFileController();

		File imageToOpen = imageFileController.startOpenFileDialog(parentWindow);

		imagePreview.setImage(new Image(imageToOpen.toURI().toString()));
		System.out.println("onActionBrowse");
	}
	
	public void showDialog() throws IOException
	{
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setDialogPane(rootDialogPane);
		Optional<ButtonType> result = dialog.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.FINISH)
		{
			//TODO ajouter le checkBox collision
			Controller.getInstance().createObstacle(obstacleName.getText(),imageToOpen.getPath());
			RootLayoutController.getInstance().getOpenObstacleController().updateTable();
		}
	}
}