package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.File;

public class NewSportController
{
	@FXML
	void onActionDelete(ActionEvent event)
	{
		System.out.println("onActionDelete");
		OpenImageFileController openImageFileController = new OpenImageFileController();

		File imageToOpen = openImageFileController.startDialog();
	}

	@FXML
	void onActionModify(ActionEvent event)
	{
		System.out.println("onActionModify");
	}
}