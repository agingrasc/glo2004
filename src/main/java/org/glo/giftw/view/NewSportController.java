package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;
import org.glo.giftw.controller.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class NewSportController
{
    @FXML
    private DialogPane rootDialogPane;

    @FXML
    private TextField sportName;
    
    @FXML
    private TextField addRole;

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
    private Spinner<Double> fieldLength;

    @FXML
    private Spinner<Double> fieldWidth;

    public File getSportFieldImageFile()
    {
        return new File(fieldImage.getImage().toString());
    }

    public File getSportProjectileImageFile()
    {
        return new File(fieldProjectile.getImage().toString());
    }

    @FXML
    public void initialize()
    {
        System.out.println("initializeNewSport");
        initSpinners(0, 0);
    }

    private void initSpinners(double initialLength, double initialWidth)
    {
        fieldLength.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, initialLength));
        fieldWidth.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, initialWidth));

        fieldLength.setEditable(true);
        fieldWidth.setEditable(true);
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

        fieldEditorController.initSpinners((double) fieldLength.getValue(), (double) fieldWidth.getValue());

        dialog.showAndWait();

        setImage(fieldEditorController.getDrawnFieldFilePath(), fieldImage);
        if (fieldEditorController.getDrawnFieldFilePath() != null)
        {
            double newLength = fieldEditorController.getLength();
            double newWidth = fieldEditorController.getWidth();
            if (newLength > 0 && newWidth > 0)
            {
                initSpinners(newLength, newWidth);
            }
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
        if (imageToOpen != null)
        {
            /*if ( (double)fieldLength.getValue() > 0 && (double)fieldWidth.getValue() > 0) {
				fieldImage.setFitHeight((double)fieldWidth.getValue() * 100);
				fieldImage.setFitWidth((double)fieldLength.getValue() * 100);
			}*/
            imageView.setImage(new Image(imageToOpen.toURI().toString()));
        }
    }
    
    @FXML
    void onKeyPressed(KeyEvent event) 
    {
    	if(event.getCode().equals(KeyCode.DELETE))
    	{
    		if(roles.getValue() != null)
    		{
    			roles.getItems().remove(roles.getValue());
    		}
    	}
    }
    
    @FXML
    void onActionAddRole(ActionEvent event)
    {
    	roles.getItems().add(addRole.getText());
    	addRole.setText(null);
    }

    public void showDialog() throws IOException
    {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setDialogPane(rootDialogPane);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.FINISH)
        {
            String name = sportName.getText();
            List<String> lroles = roles.getItems();
            Integer flength = Integer.parseInt(fieldLength.getEditor().getText());
            Integer fwidth = Integer.parseInt(fieldWidth.getEditor().getText());
            String sportImgPath = getSportFieldImageFile().getPath();
            String projName = projectileName.getText();
            String projPath = getSportProjectileImageFile().getPath();
            Integer maxNumberOfPlayer = Integer.parseInt(maxPlayers.getText());
            Integer maxNumberOfTeams = Integer.parseInt(maxTeams.getText());

            Controller.getInstance().createSport(name, lroles, flength, fwidth, sportImgPath, projName, projPath,
                                                 maxNumberOfPlayer, maxNumberOfTeams);

            RootLayoutController.getInstance().getOpenSportController().updateTable();
        }
    }
}