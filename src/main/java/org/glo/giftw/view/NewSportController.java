package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
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

	@FXML
	private Spinner fieldLength;

	@FXML
	private Spinner fieldWidth;


	File getSportFieldImageFile() { return new File(fieldImage.getImage().toString()); }

	private void initSpinners(double initialLength, double initialWidth)
	{
		fieldLength.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, initialLength));
		fieldWidth.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, initialWidth));

		fieldLength.setEditable(true);
		fieldWidth.setEditable(true);
	}

	@FXML
	public void initialize() {
		System.out.println("initializeNewSport");
		initSpinners(0, 0);
	}

	@FXML
	void onActionDraw(ActionEvent event) throws IOException
	{
		System.out.println("onActionDraw");

		Dialog<Object> dialog = new Dialog<Object>();

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("/fxml/FieldEditor.fxml"));
		DialogPane fieldEditorDialogPane = loader.load();

		dialog.setDialogPane(fieldEditorDialogPane);
		FieldEditorController fieldEditorController = loader.<FieldEditorController>getController();

		fieldEditorController.initSpinners((double)fieldLength.getValue(), (double)fieldWidth.getValue());

		dialog.showAndWait();

		setImage(fieldEditorController.getDrawnFieldFilePath());
		if(fieldEditorController.getDrawnFieldFilePath()!=null) {
			double newLength = fieldEditorController.getLength();
			double newWidth = fieldEditorController.getWidth();
			if( newLength > 0 && newWidth > 0 ) { initSpinners(newLength, newWidth); }
		}

	}

	@FXML
	void onActionBrowse(ActionEvent event)
	{
		System.out.println("onActionBrowse");
		Window parentWindow = newSportDialog.getScene().getWindow();
		ImageFileController imageFileController = new ImageFileController();

		File imageToOpen = imageFileController.startOpenFileDialog(parentWindow);
		setImage(imageToOpen);
	}

	private void setImage(File imageToOpen)
	{
		if(imageToOpen != null)
		{
			if ( (double)fieldLength.getValue() > 0 && (double)fieldWidth.getValue() > 0) {
				fieldImage.setFitHeight((double)fieldWidth.getValue() * 100);
				fieldImage.setFitWidth((double)fieldLength.getValue() * 100);
			}
			sportFieldImageFile = imageToOpen;
			fieldImage.setImage(new Image(imageToOpen.toURI().toString()));
		}
	}
}