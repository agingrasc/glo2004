package org.glo.giftw.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.strategy.Frame;
import org.glo.giftw.domain.strategy.GameObject;
import org.glo.giftw.domain.util.Vector;
import org.glo.giftw.view.edit.ViewableGameObject;
import org.glo.giftw.view.edit.ViewableGameObjectBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class CreationStackPaneController
{
    @FXML
    private StackPane stackPane;
    @FXML
    private ScrollPane scrollPane;
    private FrameView previousPane;
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
        addPanes();
        displayStrategy();
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

    private void addPanes()
    {
        this.currentPane = new FrameView();
        this.previousPane = new FrameView();
        stackPane.getChildren().add(previousPane);
        stackPane.getChildren().add(currentPane);
    }

    public void displayStrategy()
    {
        placeObjectsInPane(currentPane);
        if (!Controller.getInstance().isFirstFrame())
        {
            Controller.getInstance().previousFrame();
            placeObjectsInPane(previousPane);
            previousPane.getChildren().forEach(n -> n.setOpacity(0.5));
            Controller.getInstance().nextFrame();
        }
    }

    private void placeObjectsInPane(FrameView pane)
    {
        Frame currentFrame = Controller.getInstance().getCurrentFrame();
        Set<GameObject> gameObjectSet = currentFrame.getGameObjects();
        pane.clearPane();
        for (GameObject gameObject : gameObjectSet)
        {
            ViewableGameObject obj = ViewableGameObjectBuilder.buildViewableGameObject(gameObject);
            pane.addViewableToHashMap(gameObject.getId(), obj);
            pane.placeViewableInPane(obj, currentFrame.getPosition(gameObject));
        }
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

    public ScrollPane getScrollPane()
    {
        return this.scrollPane;
    }
}
