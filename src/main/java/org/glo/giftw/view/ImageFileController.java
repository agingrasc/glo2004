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
import java.io.IOException;
import java.util.List;

public class ImageFileController
{
    private FileChooser imageChooser;

    ImageFileController()
    {
        imageChooser = null;
        initDialog();
    }

    private void initDialog()
    {
        imageChooser = new FileChooser();
        imageChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("image file",
                                                "*.png", "*.jpg",
                                                "*.gif", "*.tif",
                                                "*.tiff", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("TIF", "*.tif"),
                new FileChooser.ExtensionFilter("TIFF", "*.tiff"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
    }

    File startOpenFileDialog(Window parentWindow)
    {
        imageChooser.setTitle("Ouvrir une image");
        File selectedImage = imageChooser.showOpenDialog(parentWindow);
        return selectedImage;
    }

    private String getFileFormat(File imageFile)
    {
        String imageFileAsString = imageFile.getName();
        int dotIndex = imageFileAsString.lastIndexOf(".");
        return imageFileAsString.substring(dotIndex + 1);
    }

    File startSaveFileDialog(Window parentWindow, Image content)
    {
        File selectedImagePath = null;
        try
        {
            imageChooser.setTitle("Sauvegarder une image");
            selectedImagePath = imageChooser.showSaveDialog(parentWindow);
            if (selectedImagePath != null)
            {
                List<String> fileType = imageChooser.getSelectedExtensionFilter().getExtensions();
                BufferedImage bImage = SwingFXUtils.fromFXImage(content, null);
                String format = getFileFormat(selectedImagePath);
                ImageIO.write(bImage, format, selectedImagePath);
            }
        }
        catch (IOException ex)
        {
        }
        return selectedImagePath;
    }
}
