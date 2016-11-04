package org.glo.giftw.view;

/**
 * Created by alexandra on 11/3/16.
 */

import javafx.stage.FileChooser;
import javafx.stage.Window;
import java.io.File;

public class OpenImageFileController {
    private FileChooser imageChooser;

    OpenImageFileController() {
        imageChooser = null;
        initDialog();
    }

    void initDialog() {
        imageChooser = new FileChooser();
        imageChooser.setTitle("Sélectionner une image d'obstacle");
        imageChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.tif", "*.tiff",
                        "*.bmp")
        );
    }

    File startDialog(Window parentWindow) {
        File selectedImage = imageChooser.showOpenDialog(parentWindow);
        return selectedImage;
    }


}
