package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
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
    private ComboBox<String> roles;

    @FXML
    private TextField maxPlayers;

    @FXML
    private TextField maxTeams;

    @FXML
    private TextField projectileName;

	@FXML
	private ImageView fieldImage;

	@FXML
	private ImageView fieldProjectile;

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

		loader.setLocation(getClass().getResource(FXMLPaths.FIELD_EDITOR_PATH.toString()));
		DialogPane fieldEditorDialogPane = loader.load();

		dialog.setDialogPane(fieldEditorDialogPane);
		FieldEditorController fieldEditorController = loader.<FieldEditorController>getController();

		fieldEditorController.initSpinners((double)fieldLength.getValue(), (double)fieldWidth.getValue());

		dialog.showAndWait();

		setImage(fieldEditorController.getDrawnFieldFilePath(), fieldImage);
		if(fieldEditorController.getDrawnFieldFilePath()!=null) {
			double newLength = fieldEditorController.getLength();
			double newWidth = fieldEditorController.getWidth();
			if( newLength > 0 && newWidth > 0 ) { initSpinners(newLength, newWidth); }
		}

	}

	@FXML
	void onActionBrowseField(ActionEvent event)
	{
		System.out.println("onActionBrowseField");
		Window parentWindow = rootDialogPane.getScene().getWindow();
		ImageFileController imageFileController = new ImageFileController();

		File imageToOpen = imageFileController.startOpenFileDialog(parentWindow);
		setImage(imageToOpen, fieldImage);
	}

	@FXML
	void onActionBrowseProjectile(ActionEvent event)
	{
		System.out.println("onActionBrowseProjectile");
		Window parentWindow = rootDialogPane.getScene().getWindow();
		ImageFileController imageFileController = new ImageFileController();

		File imageToOpen = imageFileController.startOpenFileDialog(parentWindow);
		setImage(imageToOpen, fieldProjectile);
	}

	private void setImage(File imageToOpen, ImageView imageView)
	{
		if(imageToOpen != null)
		{
			/*if ( (double)fieldLength.getValue() > 0 && (double)fieldWidth.getValue() > 0) {
				fieldImage.setFitHeight((double)fieldWidth.getValue() * 100);
				fieldImage.setFitWidth((double)fieldLength.getValue() * 100);
			}*/
			sportFieldImageFile = imageToOpen;
			imageView.setImage(new Image(imageToOpen.toURI().toString()));
		}
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