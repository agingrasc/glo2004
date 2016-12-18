package org.glo.giftw.view;

import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import org.glo.giftw.domain.util.Vector;

import java.io.File;

/**
 * Created by alexandra on 12/16/16.
 */
public class StrategyExporterController {
    private Dialog rootDialog;

    private File exportedFilePath;

    @FXML
    private DialogPane rootPane;

    @FXML
    private ImageView strategyView;

    @FXML
    public void initialize()
    {

        rootDialog = new Dialog();
        rootDialog.setTitle("Exporter la stratégie");
        rootDialog.setDialogPane(rootPane);
        rootDialog.setResizable(true);

        StrategyImageExporter preview = new StrategyImageExporter();

        rootDialog.setResizable(true);

        Vector imageSize = preview.getDimensions();

        rootDialog.setWidth(imageSize.getX());
        rootDialog.setHeight(imageSize.getY());

        strategyView.setImage(preview.getImage());
    }

    @FXML
    public void onSave()
    {
        Image currentField = strategyView.getImage();

        Window parentWindow = rootPane.getScene().getWindow();
        ImageFileController saveFileDialog = new ImageFileController();

        exportedFilePath = saveFileDialog.startSaveFileDialog(parentWindow, currentField);
    }

    public void showDialog()
    {
        rootDialog.showAndWait();
    }
}
