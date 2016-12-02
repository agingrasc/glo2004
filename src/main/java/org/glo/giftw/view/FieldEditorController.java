package org.glo.giftw.view;

/**
 * Created by alexandra on 11/4/16.
 */

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Window;

import java.io.File;

public class FieldEditorController
{
    private double[] startDragPosition;

    private File fieldSelectedFilePath;

    @FXML
    private DialogPane fieldEditorDialog;

    @FXML
    private Canvas fieldDraw;

    @FXML
    private Spinner<Double> fieldLength;

    @FXML
    private Spinner<Double> fieldWidth;

    @FXML
    private ChoiceBox<String> fieldSize;

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

    public File getDrawnFieldFilePath()
    {
        return fieldSelectedFilePath;
    }

    public double getLength()
    {
        return (double) fieldLength.getValue();
    }

    public double getWidth()
    {
        return (double) fieldWidth.getValue();
    }

    public void initSpinners(double initialLength, double initialWidth)
    {
        double fieldInitialLength;
        double fieldInitialWidth;

        if (initialLength > 0 && initialWidth > 0)
        {
            fieldInitialLength = initialLength;
            fieldInitialWidth = initialWidth;
        }
        else
        {
            fieldInitialLength = (fieldDraw.getWidth()) / 100;
            fieldInitialWidth = (fieldDraw.getHeight()) / 100;
        }

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
        fieldSize.getItems().addAll("m2", "pi2");
        fieldSize.setValue("m2");

        fieldSize.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1)
            {
                System.out.println("selection changed!");
                if (fieldSize.getSelectionModel().isSelected(1)) //pi carré
                {
                    fieldLength.getValueFactory().setValue((fieldDraw.getWidth() / 100) * 3.28);
                    fieldWidth.getValueFactory().setValue((fieldDraw.getHeight() / 100) * 3.28);
                }
                else if (fieldSize.getSelectionModel().isSelected(0)) // mètre carré
                {
                    fieldLength.getValueFactory().setValue((fieldDraw.getWidth() / 100));
                    fieldWidth.getValueFactory().setValue((fieldDraw.getHeight() / 100));
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
    public void initialize()
    {
        System.out.println("initializeFieldEditor");
        startDragPosition = new double[2];
        initCanvas();
        initChoiceBox();
        initRadioButtons();
    }

    @FXML
    public void onSave()
    {
        //format canvas as image
        WritableImage currentField = new WritableImage((int) fieldDraw.getWidth(),
                                                       (int) fieldDraw.getHeight());

        fieldDraw.snapshot(null, currentField);

        Window parentWindow = fieldEditorDialog.getScene().getWindow();
        ImageFileController saveFileDialog = new ImageFileController();

        fieldSelectedFilePath = saveFileDialog.startSaveFileDialog(parentWindow, currentField);

    }

    @FXML
    public void onUndo()
    {
        System.out.println("onUndo");
        fieldDraw.getGraphicsContext2D();
    }

    @FXML
    public void onSizeChanged()
    {
        System.out.println("onSizeChanged");
        fieldDraw.setHeight((double) fieldWidth.getValue());
        fieldDraw.setWidth((double) fieldLength.getValue());
    }

    @FXML
    void onFillColor()
    {
        GraphicsContext gc = fieldDraw.getGraphicsContext2D();
        gc.setFill(fieldColor.getValue());
        gc.fillRect(0, 0, fieldDraw.getWidth(), fieldDraw.getHeight());
    }

    @FXML
    void onDraw(MouseEvent me)
    {
        GraphicsContext gc = fieldDraw.getGraphicsContext2D();

        if (fieldPencil.isSelected())
        {
            gc.setFill(fieldColor.getValue());
            gc.fillRect(me.getX(), me.getY(), 3, 3);
        }
    }

    @FXML
    void onInitShape(MouseEvent me)
    {
        GraphicsContext gc = fieldDraw.getGraphicsContext2D();
        if (fieldCircle.isSelected() || fieldSquare.isSelected() || fieldLine.isSelected())
        {
            startDragPosition[0] = me.getX();
            startDragPosition[1] = me.getY();
        }
        else if (fieldPencil.isSelected())
        {
            gc.setFill(fieldColor.getValue());
            gc.fillRect(me.getX(), me.getY(), 3, 3);
        }
    }

    @FXML
    void onFinishShape(MouseEvent me)
    {
        drawShape(me);
        fieldDraw.getGraphicsContext2D();
    }

    void drawShape(MouseEvent me)
    {
        GraphicsContext gc = fieldDraw.getGraphicsContext2D();
        gc.setStroke(fieldColor.getValue());
        if (fieldCircle.isSelected())
        {
            gc.strokeOval(
                    startDragPosition[0],
                    startDragPosition[1],
                    (me.getX() - startDragPosition[0]),
                    (me.getY() - startDragPosition[1])
            );

        }
        else if (fieldSquare.isSelected())
        {
            gc.strokeRect(
                    startDragPosition[0],
                    startDragPosition[1],
                    (me.getX() - startDragPosition[0]),
                    (me.getY() - startDragPosition[1])
            );
        }
        else if (fieldLine.isSelected())
        {
            gc.strokeLine(startDragPosition[0], startDragPosition[1], me.getX(), me.getY());
        }
    }
}