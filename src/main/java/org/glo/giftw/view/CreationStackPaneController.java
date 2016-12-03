package org.glo.giftw.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.strategy.GameObject;
import org.glo.giftw.domain.strategy.Obstacle;
import org.glo.giftw.domain.strategy.Player;
import org.glo.giftw.domain.strategy.Projectile;
import org.glo.giftw.domain.util.Vector;

import java.io.File;
import java.io.IOException;

public class CreationStackPaneController
{
    @FXML
    private StackPane stackPane;

    private Pane currentPane;

    boolean ctrlPressed = false;

    @FXML
    private ScrollPane scrollPane;

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
        FrameView currentPane = new FrameView();
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
                GameObject selected = Controller.getInstance().getGameObjectByCoordinate(
                        new Vector(event.getX(), event.getY()));
                System.out.println(selected);
                if (selected != null)
                {
                    Dragboard db = currentPane.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    Image image = null;
                    if (selected instanceof Obstacle)
                    {
                        File imageFile = new File(((Obstacle) selected).getImagePath());
                        image = new Image(imageFile.toURI().toString());
                    }
                    else if (selected instanceof Projectile)
                    {
                        File imageFile = new File(((Projectile) selected).getImagePath());
                        image = new Image(imageFile.toURI().toString());
                    }
                    else if (selected instanceof Player)
                    {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource(FXMLPaths.PLAYER_DISPLAY_PATH.toString()));
                        GridPane playerDisplay = null;
                        try
                        {
                            playerDisplay = loader.load();
                            PlayerDisplayController pdc = loader.getController();
                            Canvas canvas = pdc.getCanvas();
                            GraphicsContext gc = canvas.getGraphicsContext2D();
                            String teamName = Controller.getInstance().getPlayerTeam(selected);
                            gc.setFill(Color.web(Controller.getInstance().getTeamColour(teamName)));
                            double x = Controller.getInstance().getCurrentFrame().getDimensions(
                                    selected).getX() * ratioPixelToUnit.getX();
                            double y = Controller.getInstance().getCurrentFrame().getDimensions(
                                    selected).getY() * ratioPixelToUnit.getY();
                            gc.fillOval(0, 0, x, y);
                            WritableImage writableImage = playerDisplay.snapshot(new SnapshotParameters(), null);
                            image = writableImage;

                        }
                        catch (IOException e)
                        {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    db.setDragView(image);
                    content.putString(String.valueOf(selected.getId()));
                    db.setContent(content);
                    event.consume();

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
}
