package org.glo.giftw.view;

/**
 * Created by alexandra on 11/4/16.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class FieldEditorController
{
    @FXML
    private DialogPane fieldEditorDialog;

    @FXML
    private Pane fieldEditorPane;

    @FXML
    private Canvas fieldDraw;

    @FXML
    private Spinner fieldLength;

    @FXML
    private Spinner fieldWidth;

    //FieldEditorController() {
    //}

    @FXML
    public void initialize() {
        System.out.println("initializeFieldEditor");

        GraphicsContext gc = fieldDraw.getGraphicsContext2D();
        gc.setFill(Color.WHITE);

        double fieldInitialLength = (fieldDraw.getWidth()) / 100;
        double fieldInitialWidth = (fieldDraw.getHeight()) / 100;

        fieldLength.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, fieldInitialLength));
        fieldWidth.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, fieldInitialWidth));
    }

    public DialogPane getFieldEditorDialog() {
        return fieldEditorDialog;
    }

    public Pane getFieldEditorPane() {
        return fieldEditorPane;
    }

    public Canvas getFieldDraw() {
        return fieldDraw;
    }

    public Spinner getFieldLength() {
        return fieldLength;
    }

    public Spinner getFieldWidth() {
        return fieldWidth;
    }
}