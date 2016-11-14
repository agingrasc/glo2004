package org.glo.giftw.view;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

public class NewStrategyController
{
	@FXML
	private DialogPane rootDialogPane;
	
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
