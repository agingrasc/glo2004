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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static java.lang.Math.abs;


public class FieldEditorController
{
    private double[] startDragPosition;

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
    private RadioButton fieldPencil;

    @FXML
    private RadioButton fieldCircle;

    @FXML
    private RadioButton fieldSquare;

    @FXML
    private RadioButton fieldLine;

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

    private void initRadioButtons()
    {
        ToggleGroup toggleGroup = new ToggleGroup();

        fieldPencil.setToggleGroup(toggleGroup);
        fieldCircle.setToggleGroup(toggleGroup);
        fieldSquare.setToggleGroup(toggleGroup);
        fieldLine.setToggleGroup(toggleGroup);

        fieldPencil.setSelected(true);
    }

    @FXML
    public void initialize() {
        System.out.println("initializeFieldEditor");
        startDragPosition = new double[2];
        initCanvas();
        initSpinners();
        initChoiceBox();
        initRadioButtons();
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
    }

    @FXML
    void onFillColor()
    {
        GraphicsContext gc = fieldDraw.getGraphicsContext2D();
        gc.setFill(fieldColor.getValue());
        gc.fillRect(0, 0, fieldDraw.getWidth(), fieldDraw.getHeight());
    }

    @FXML
    void onPaintColor()
    {
    }

    @FXML
    void onDraw(MouseEvent me)
    {
        if(fieldPencil.isSelected())
        {
            GraphicsContext gc = fieldDraw.getGraphicsContext2D();
            gc.setFill(fieldColor.getValue());
            gc.fillRect(me.getX(), me.getY(), 3, 3);
        }
    }

    @FXML
    void onInitShape(MouseEvent me)
    {
        if(fieldCircle.isSelected() || fieldSquare.isSelected() || fieldLine.isSelected())
        {
            System.out.println("new Start!");
            startDragPosition[0] = me.getX();
            startDragPosition[1] = me.getY();
        }
    }

    @FXML
    void onFinishShape(MouseEvent me)
    {
        if(fieldCircle.isSelected())
        {
            GraphicsContext gc = fieldDraw.getGraphicsContext2D();
            gc.setStroke(fieldColor.getValue());
            gc.strokeOval(
                    startDragPosition[0],
                    startDragPosition[1],
                    abs(me.getX() - startDragPosition[0]),
                    abs(me.getY() - startDragPosition[1])
            );

        }
        else if(fieldSquare.isSelected())
        {
            GraphicsContext gc = fieldDraw.getGraphicsContext2D();
            gc.setStroke(fieldColor.getValue());
            gc.strokeRect(
                    startDragPosition[0],
                    startDragPosition[1],
                    abs(me.getX() - startDragPosition[0]),
                    abs(me.getY() - startDragPosition[1])
            );
        }
        else if(fieldLine.isSelected())
        {
            GraphicsContext gc = fieldDraw.getGraphicsContext2D();
            gc.setStroke(fieldColor.getValue());
            gc.strokeLine(startDragPosition[0], startDragPosition[1], me.getX(), me.getY());
        }
    }
}