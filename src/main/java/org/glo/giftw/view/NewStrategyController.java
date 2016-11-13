package org.glo.giftw.view;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

public class NewStrategyController
{
	public void showDialog(DialogPane dialogPane)
	{
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setDialogPane(dialogPane);
		Optional<ButtonType> result = dialog.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.FINISH)
		{
			//TODO Mettre a jour le domaine via le controleur
			//Et mettre à jour la vue...
		}
	}
}
