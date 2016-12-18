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

    public File saveFile(Image content, File fileName)
    {
        try {
            if (fileName != null) {
                BufferedImage bImage = SwingFXUtils.fromFXImage(content, null);
                String format = getFileFormat(fileName);
                ImageIO.write(bImage, format, fileName);
            }
        }
        catch (IOException ex)
        {

        }
        return fileName;
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

        imageChooser.setTitle("Sauvegarder une image");
        selectedImagePath = imageChooser.showSaveDialog(parentWindow);
        saveFile(content, selectedImagePath);

        return selectedImagePath;
    }
}
