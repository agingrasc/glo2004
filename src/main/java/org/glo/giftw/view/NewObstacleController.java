package org.glo.giftw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import org.glo.giftw.domain.Controller;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

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

    @FXML
    private TextField obstacleWidth;

    @FXML
    private TextField obstacleHeight;

    private File imageToOpen;

    @FXML
    void onActionBrowse(ActionEvent event)
    {
        Window parentWindow = rootDialogPane.getScene().getWindow();
        ImageFileController imageFileController = new ImageFileController();

        imageToOpen = imageFileController.startOpenFileDialog(parentWindow);

        imagePreview.setImage(new Image(imageToOpen.toURI().toString()));
    }

    public void showDialog() throws IOException
    {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setDialogPane(rootDialogPane);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.FINISH)
        {
            Integer obsWidth = Integer.parseInt(obstacleWidth.getText());
            Integer obsHeight = Integer.parseInt(obstacleHeight.getText());
            Controller.getInstance().createObstacle(obstacleName.getText(), isCollision.isSelected(),
                                                    imageToOpen.getPath(), obsWidth, obsHeight);
            RootLayoutController.getInstance().getOpenObstacleController().updateTable();
            RootLayoutController.getInstance().getItemsAccordionController().updateObstaclesTable();
        }
    }
}