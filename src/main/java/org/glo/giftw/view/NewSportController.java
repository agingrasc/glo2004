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
import org.glo.giftw.domain.Controller;

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
    private TextField projectileWidth;

    @FXML
    private TextField projectileHeight;

    @FXML
    private ImageView fieldImage;

    @FXML
    private ImageView fieldProjectile;

    @FXML
    private Spinner<Double> fieldLength;

    @FXML
    private Spinner<Double> fieldWidth;

    private File fieldImageFile;

    private File projectileImageFile;

    @FXML
    public void initialize()
    {
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXMLPaths.FIELD_EDITOR_PATH.toString()));
        loader.load();

        FieldEditorController fieldEditorController = loader.<FieldEditorController>getController();

        if (fieldImage.getImage() != null)
        {
            fieldEditorController.initImage(fieldImage.getImage());
        }

        Dialog<Object> dialog = fieldEditorController.getDialog();
        dialog.showAndWait();

        fieldImageFile = fieldEditorController.getDrawnFieldFilePath();
        setImage(fieldEditorController.getDrawnFieldFilePath(), fieldImage);
    }

    @FXML
    void onActionBrowseField(ActionEvent event)
    {
        Window parentWindow = rootDialogPane.getScene().getWindow();
        ImageFileController imageFileController = new ImageFileController();

        File imageToOpen = imageFileController.startOpenFileDialog(parentWindow);
        fieldImageFile = imageToOpen;
        setImage(imageToOpen, fieldImage);
    }

    @FXML
    void onActionBrowseProjectile(ActionEvent event)
    {
        Window parentWindow = rootDialogPane.getScene().getWindow();
        ImageFileController imageFileController = new ImageFileController();

        File imageToOpen = imageFileController.startOpenFileDialog(parentWindow);
        projectileImageFile = imageToOpen;
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
        if (event.getCode().equals(KeyCode.DELETE))
        {
            if (roles.getValue() != null)
            {
                roles.getItems().remove(roles.getValue());
            }
        }
    }

    @FXML
    void onActionAddRole(ActionEvent event)
    {
        roles.getItems().add(addRole.getText());
        roles.setValue(addRole.getText());
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
            String sportImgPath = fieldImageFile.getPath();
            String projName = projectileName.getText();
            Integer projWidth = Integer.parseInt(projectileWidth.getText());
            Integer projHeight = Integer.parseInt(projectileHeight.getText());
            String projPath = projectileImageFile.getPath();
            Integer maxNumberOfPlayer = Integer.parseInt(maxPlayers.getText());
            Integer maxNumberOfTeams = Integer.parseInt(maxTeams.getText());
            Controller.getInstance().createSport(name, lroles, flength, fwidth, sportImgPath, projName, projPath,
                                                 projWidth, projHeight, maxNumberOfPlayer, maxNumberOfTeams);

            RootLayoutController.getInstance().getOpenStrategyController().updateTree();
            RootLayoutController.getInstance().getItemsAccordionController().updateProjectilesTable();
        }
    }
}