package org.glo.giftw.view;

/**
 * Created by alexandra on 11/4/16.
 */

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.EventListener;
import java.util.Stack;

public class FieldEditorController
{
    private double[] startDragPosition;

    private File fieldSelectedFilePath;

    private GraphicsContext gcForeground, gcBackground;

    private GraphicContextDrawController fieldDrawControl;

    private Stack<Image> states, undoStates;

    private Dialog rootDialog;

    @FXML
    private DialogPane rootPane;

    @FXML
    private Canvas fieldDraw, fieldDrawPreview;

    @FXML
    private Spinner<Double> fieldLength, fieldWidth;

    @FXML
    private ChoiceBox<String> fieldSize;

    @FXML
    private ColorPicker fieldColor;

    @FXML
    private Slider drawSizeSlider;

    @FXML
    private TextField drawSizeText;

    @FXML
    private RadioButton fieldPencil, fieldCircle, fieldSquare, fieldLine;

    public File getDrawnFieldFilePath()
    {
        return fieldSelectedFilePath;
    }

    public double getLength() { return fieldLength.getValue(); }

    public double getWidth() { return fieldWidth.getValue(); }

    public Dialog getDialog() { return rootDialog; }

    public void saveImage()
    {
        //format canvas as image
        Image currentField = fieldDrawControl.getCurrentDrawnState();

        Window parentWindow = rootPane.getScene().getWindow();
        ImageFileController saveFileDialog = new ImageFileController();

        fieldSelectedFilePath = saveFileDialog.startSaveFileDialog(parentWindow, currentField);
    }

    public void openImage()
    {
        Window parentWindow = rootPane.getScene().getWindow();
        ImageFileController openFileDialog = new ImageFileController();

        File openFilePath = openFileDialog.startOpenFileDialog(parentWindow);
        if(openFilePath != null)
        {
            Image newBackground = new Image(openFilePath.toURI().toString());
            initImage(newBackground, openFilePath);
        }
    }

    public void initImage(Image image, File imageFilePath)
    {
        fieldDrawControl.drawImage(gcBackground, image);
        fieldSelectedFilePath = imageFilePath;
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
        gcBackground = fieldDraw.getGraphicsContext2D();
        gcForeground = fieldDrawPreview.getGraphicsContext2D();

        gcBackground.setFill(Color.WHITE);
        gcBackground.fillRect(0, 0, fieldDraw.getWidth(), fieldDraw.getHeight());

        states = new Stack<Image>();
        undoStates = new Stack<Image>();
        saveLastDrawnState();
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

    private void initSlider()
    {
        drawSizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                drawSizeText.setText(String.valueOf(drawSizeSlider.getValue()));
                onTextSizeChanged();
            }
        });
    }

    private void initKeyEvent()
    {
        final EventHandler<KeyEvent> keyEventEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.isControlDown()) {
                    if (keyEvent.getCode() == KeyCode.S) {
                        saveImage();
                    }
                    else if (keyEvent.getCode() == KeyCode.O) {
                        openImage();
                    }
                    else if (keyEvent.getCode() == KeyCode.N) {
                        onNew();
                    }
                    else if (keyEvent.getCode() == KeyCode.Z) {
                        restorePreviousState();
                    }
                    else if (keyEvent.getCode() == KeyCode.Y) {
                        restoreNextState();
                    }
                }
            }
        };
        rootPane.addEventFilter(KeyEvent.KEY_PRESSED, keyEventEventHandler);
    }

    @FXML
    public void initialize()
    {
        System.out.println("initializeFieldEditor");
        startDragPosition = new double[2];
        fieldDrawControl = new GraphicContextDrawController();
        rootDialog = new Dialog();
        rootDialog.setTitle("Édition de Terrain");
        initCanvas();
        initChoiceBox();
        initRadioButtons();
        initSlider();
        initKeyEvent();
        rootDialog.setDialogPane(rootPane);
        rootDialog.setResizable(true);
    }

    @FXML
    public void onNew()
    {
        eraseAll(gcBackground);
        gcBackground.setFill(Color.WHITE);
        gcBackground.fillRect(0, 0, fieldDraw.getWidth(), fieldDraw.getHeight());
    }

    @FXML
    public void onSave()
    {
        saveImage();
    }

    @FXML
    public void onOpen() { openImage(); }

    @FXML
    public void onUndo() { restorePreviousState(); }

    @FXML
    public void onRedo() { restoreNextState(); }

    @FXML
    public void onSizeChanged()
    {
        System.out.println("onSizeChanged");
        fieldDraw.setHeight(fieldWidth.getValue());
        fieldDraw.setWidth(fieldLength.getValue());
        fieldDrawPreview.setHeight(fieldWidth.getValue());
        fieldDrawPreview.setWidth(fieldLength.getValue());

    }

    @FXML
    public void onTextSizeChanged()
    {
        System.out.println("onTextSizeChanged");
        double newSize = Double.valueOf(drawSizeText.getText());
        drawSizeSlider.setValue(newSize);
        fieldDrawControl.setStrokeLineSize(gcBackground, newSize);
        fieldDrawControl.setStrokeLineSize(gcForeground, newSize);
    }

    @FXML
    void onFillColor()
    {
        gcBackground.setFill(fieldColor.getValue());
        gcBackground.fillRect(0, 0, fieldDraw.getWidth(), fieldDraw.getHeight());
        saveLastDrawnState();
    }

    @FXML
    void onDraw(MouseEvent me)
    {
        //System.out.println("onDraw");
        eraseAll(gcForeground);
        drawShape(me, gcForeground);

        if (fieldPencil.isSelected())
        {
            drawWithPencil(me.getX(), me.getY(), drawSizeSlider.getValue());
        }
    }

    @FXML
    void onInitShape(MouseEvent me)
    {
        //System.out.println("onInitShape");
        if (fieldCircle.isSelected() || fieldSquare.isSelected() || fieldLine.isSelected())
        {
            startDragPosition[0] = me.getX();
            startDragPosition[1] = me.getY();
        }
        else if (fieldPencil.isSelected())
        {
            drawWithPencil(me.getX(), me.getY(), drawSizeSlider.getValue());
        }
    }

    @FXML
    void onFinishShape(MouseEvent me)
    {
        //System.out.println("onFinishShape");
        eraseAll(gcForeground);
        drawShape(me, gcBackground);
        saveLastDrawnState();
    }

    void eraseAll(GraphicsContext gc) { fieldDrawControl.eraseAll(gc); }

    void saveLastDrawnState() { fieldDrawControl.saveLastDrawnState(gcBackground); }

    void restorePreviousState() { fieldDrawControl.redrawPreviousState(gcBackground); }

    void restoreNextState() { fieldDrawControl.redrawNextState(gcBackground); }

    void drawWithPencil(double x, double y, double size)
    {
        fieldDrawControl.setStrokeLineSize(gcBackground, drawSizeSlider.getValue());
        fieldDrawControl.setDrawColor(gcBackground, fieldColor.getValue());
        fieldDrawControl.drawWithPencil(gcBackground, x, y, size);
    }

    void drawShape(MouseEvent me, GraphicsContext gc)
    {
        // Choisir le point initial et la distance
        fieldDrawControl.setStrokeLineSize(gc, drawSizeSlider.getValue());
        fieldDrawControl.setDrawColor(gc, fieldColor.getValue());

        if (fieldLine.isSelected()) {
            fieldDrawControl.drawLine(gc, startDragPosition[0], startDragPosition[1], me.getX(), me.getY());
        }
        else if (fieldCircle.isSelected())
        {
            fieldDrawControl.drawOval(gc, me.getX(), me.getY(), startDragPosition[0], startDragPosition[1]);
        }
        else if (fieldSquare.isSelected())
        {
            fieldDrawControl.drawRectangle(gc, me.getX(), me.getY(), startDragPosition[0], startDragPosition[1]);
        }
    }
}