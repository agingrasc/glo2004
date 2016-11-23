package org.glo.giftw.view;

/**
 * Created by alexandra on 11/3/16.
 */

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ImageFileController {
    private FileChooser imageChooser;

    ImageFileController() {
        imageChooser = null;
        initDialog();
    }

    private void initDialog() {
        imageChooser = new FileChooser();
        imageChooser.setTitle("Sélectionner une image d'obstacle");
        imageChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("TIF", "*.tif"),
                new FileChooser.ExtensionFilter("TIFF", "*.tiff"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
    }

    File startOpenFileDialog(Window parentWindow) {
        File selectedImage = imageChooser.showOpenDialog(parentWindow);
        return selectedImage;
    }

    File startSaveFileDialog(Window parentWindow, Image content) {
        try {
            File selectedImagePath = imageChooser.showSaveDialog(parentWindow);
            if (selectedImagePath != null) {
                List<String> fileType = imageChooser.getSelectedExtensionFilter().getExtensions();
                BufferedImage bImage = SwingFXUtils.fromFXImage(content, null);
                String format = fileType.get(0).replace("*.", "");
                ImageIO.write(bImage, format, selectedImagePath);
            }
            return selectedImagePath;
        } catch (IOException ex){}
    }
}
