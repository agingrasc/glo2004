package org.glo.giftw.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.util.Vector;

import java.io.File;
import java.io.IOException;

public class CreationStackPaneController
{
    @FXML
    private StackPane stackPane;
    @FXML
    private ScrollPane scrollPane;

    private FrameView currentPane;
    private boolean ctrlPressed = false;
    private Vector ratioPixelToUnit;

    @FXML
    void onMouseMoved(MouseEvent event) throws IOException
    {
        BottomToolBarController bottomToolBarController = RootLayoutController.getInstance().getBottomToolBarController();
        Vector adjCoord = new Vector(event.getX(), event.getY());
        bottomToolBarController.updateCoordinate(adjCoord, this.ratioPixelToUnit);
    }

    public void init()
    {
        addEventFilter();
        setFieldAsBackground();
        computeAndSetPixelToUnitRatio();
        createNewFrame();
    }

    private void addEventFilter()
    {
        scrollPane.addEventFilter(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>()
        {
            @Override
            public void handle(ScrollEvent event)
            {
                if (ctrlPressed == true)
                {
                    if (event.getDeltaY() == 0)
                    {
                        return;
                    }

                    if (event.getDeltaY() < 0)
                    {
                        zoomOut();
                    }
                    else
                    {
                        zoomIn();
                    }
                }
                event.consume();
            }
        });
    }

    private void setFieldAsBackground()
    {
        File file = new File(Controller.getInstance().getSportFieldImagePath());
        Image sportFieldImage = new Image(file.toURI().toString());
        ImageView fieldBackground = new ImageView(sportFieldImage);
        fieldBackground.setPreserveRatio(true);
        fieldBackground.setFitHeight(this.scrollPane.getViewportBounds().getHeight());
        fieldBackground.setFitWidth(this.scrollPane.getViewportBounds().getWidth());
        this.stackPane.getChildren().add(fieldBackground);
    }

    private void computeAndSetPixelToUnitRatio()
    {
        Vector fieldDimensions = Controller.getInstance().getFieldDimensions();
        double adjustedWidth = stackPane.getWidth();
        double adjustedHeight = stackPane.getHeight();
        this.ratioPixelToUnit = new Vector(adjustedWidth / fieldDimensions.getX(),
                                           adjustedHeight / fieldDimensions.getY());
        Controller.getInstance().setPixelToUnitRatio(this.ratioPixelToUnit);
    }

    private void createNewFrame()
    {
        Controller.getInstance().createNewFrame();
        this.currentPane = new FrameView();
        stackPane.getChildren().add(currentPane);
    }

    //TODO
    private void setDragDetected(Node node)
    {
        node.setOnDragDetected(new EventHandler<MouseEvent>()
        { //drag
            @Override
            public void handle(MouseEvent event)
            {
                // drag was detected, start drag-and-drop gesture
                Vector coordinate = new Vector(event.getX(), event.getY());
                try
                {
                    String uuid = Controller.getInstance().getGameObjectByCoordinate(coordinate);
                    Image image = RootLayoutController.getInstance().getCreationStackPaneController().getCurrentPane().getViewableGameObject(
                            uuid).getImage();
                    Dragboard db = currentPane.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    db.setDragView(image);
                    content.putString(uuid);
                    db.setContent(content);
                    event.consume();
                }
                catch (GameObjectNotFound gameObjectNotFound)
                {
                    return;
                }
                catch (IOException ioException)
                {
                    return;
                }
            }
        });
    }

    public void zoomIn()
    {
        Scale scaleTransform = new Scale(1.25, 1.25, 0, 0);
        stackPane.getTransforms().add(scaleTransform);
    }

    public void zoomOut()
    {
        Scale scaleTransform = new Scale(0.8, 0.8, 0, 0);
        stackPane.getTransforms().add(scaleTransform);
    }

    @FXML
    void onKeyPressed(KeyEvent event)
    {
        if (event.getCode() == KeyCode.CONTROL)
        {
            ctrlPressed = true;
        }
    }

    @FXML
    void onKeyReleased(KeyEvent event)
    {
        if (event.getCode() == KeyCode.CONTROL)
        {
            ctrlPressed = false;
        }
    }

    public FrameView getCurrentPane()
    {
        return this.currentPane;
    }
}
