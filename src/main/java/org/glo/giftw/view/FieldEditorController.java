package org.glo.giftw.view;

/**
 * Created by alexandra on 11/4/16.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class FieldEditorController {
    @FXML
    DialogPane fieldEditorDialog;

    @FXML
    Canvas obstacleDraw;

    @FXML
    TextField fieldLength;

    @FXML
    TextField fieldWidth;

    FieldEditorController()
    {
        GraphicsContext gc = obstacleDraw.getGraphicsContext2D();

        gc.setFill(Color.WHITE);
    }

    public DialogPane getFieldEditorDialog() {
        return fieldEditorDialog;
    }

}
