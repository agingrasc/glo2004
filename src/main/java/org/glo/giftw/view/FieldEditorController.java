package org.glo.giftw.view;

/**
 * Created by alexandra on 11/4/16.
 */

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
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

    @FXML
    private ChoiceBox fieldSize;

    @FXML
    private ColorPicker fieldColor;

    @FXML
    public void initialize() {
        System.out.println("initializeFieldEditor");

        initCanvas();
        initSpinners();
        initChoiceBox();
    }

    private void initSpinners()
    {
        double fieldInitialLength = (fieldDraw.getWidth()) / 100;
        double fieldInitialWidth = (fieldDraw.getHeight()) / 100;

        fieldLength.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, fieldInitialLength));
        fieldWidth.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, fieldInitialWidth));

        fieldLength.setEditable(true);
        fieldWidth.setEditable(true);
    }

    private void initCanvas()
    {
        GraphicsContext gc = fieldDraw.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, fieldDraw.getWidth(), fieldDraw.getHeight());
    }

    private void initChoiceBox()
    {
        fieldSize.getItems().addAll("m²", "pi²");
        fieldSize.setValue("m²");

        fieldSize.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println("selection changed!");
                if(fieldSize.getSelectionModel().isSelected(1)) //pi carré
                {
                    fieldLength.getValueFactory().setValue((fieldDraw.getWidth()/100)*3.28);
                    fieldWidth.getValueFactory().setValue((fieldDraw.getHeight()/100)*3.28);
                }
                else if(fieldSize.getSelectionModel().isSelected(0)) // mètre carré
                {
                    fieldLength.getValueFactory().setValue((fieldDraw.getWidth()/100));
                    fieldWidth.getValueFactory().setValue((fieldDraw.getHeight()/100));
                }
            }
        });
    }

    @FXML
    public void onSave()
    {
        System.out.println("onSave");
    }

    @FXML
    public void onFieldColorChanged()
    {
        System.out.println("onColorChanged");
        GraphicsContext gc = fieldDraw.getGraphicsContext2D();
        gc.setFill(fieldColor.getValue());
        gc.fillRect(0, 0, fieldDraw.getWidth(), fieldDraw.getHeight());
    }

}