package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.stage.Window;

import java.io.File;

public class NewSportController
{
	@FXML
	private DialogPane newSportDialog;

	@FXML
	void onActionDelete(ActionEvent event)
	{
		Window parentWindow = newSportDialog.getScene().getWindow();
		System.out.println("onActionDelete");
		OpenImageFileController openImageFileController = new OpenImageFileController();

		File imageToOpen = openImageFileController.startDialog(parentWindow);
	}

	@FXML
	void onActionModify(ActionEvent event)
	{
		System.out.println("onActionModify");
	}
}