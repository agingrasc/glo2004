package org.glo.giftw.view;

import javafx.animation.AnimationTimer;
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
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.Frame;
import org.glo.giftw.domain.strategy.GameObject;
import org.glo.giftw.domain.strategy.Strategy;
import org.glo.giftw.domain.strategy.Player;
import org.glo.giftw.domain.util.Vector;
import org.glo.giftw.view.edit.ViewableGameObject;
import org.glo.giftw.view.edit.ViewableGameObjectBuilder;
import org.glo.giftw.view.edit.ViewablePlayer;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class CreationStackPaneController extends AnimationTimer
{
    @FXML
    private StackPane stackPane;
    @FXML
    private ScrollPane scrollPane;
    private FrameView previousPane;
    private FrameView currentPane;
    private boolean ctrlPressed = false;
    private Vector ratioPixelToUnit;
    private ImageView fieldBackground;
    private boolean displayNames;
    private boolean displayRoles;
    private String selectedUUID;
    public EditionMode mode;
    private long lastTimeStamp;

    @Override
    public void handle(long timestamp)
    {
        long delta_t = timestamp - lastTimeStamp;

        long fpsInNano = 1000^3 / Strategy.FRAME_PER_SECOND;
        if (delta_t >= fpsInNano)
        {
            this.lastTimeStamp = timestamp;
            String uuid = this.getSelectedUUID();
            try
            {
                Controller.getInstance().placeGameObject(uuid, this.currentPane.getCurrentMousePosition());
            }
            catch (GameObjectNotFound gameObjectNotFound)
            {
                gameObjectNotFound.printStackTrace();
            }

            if (Controller.getInstance().isLastFrame())
            {
                Controller.getInstance().createNewFrame();
            }
            else
            {
                Controller.getInstance().nextFrame();
            }
            //this.resetDisplay();
            this.displayStrategy();
        }
    }

    @FXML
    void onMouseMoved(MouseEvent event) throws IOException
    {
        BottomToolBarController bottomToolBarController = RootLayoutController.getInstance().getBottomToolBarController();
        Vector adjCoord = new Vector(event.getX(), event.getY());
        bottomToolBarController.updateCoordinate(adjCoord, this.ratioPixelToUnit);
    }

    public void init(EditionMode mode)
    {
        this.lastTimeStamp = 0;
        this.mode = mode;
        displayNames = false;
        displayRoles = false;
        addEventFilter();
        setFieldAsBackground();
        computeAndSetPixelToUnitRatio();
        addPanes();
        resetDisplay();
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
        Vector fieldDimensions = Controller.getInstance().getFieldDimensions();
        double wantedRatio = fieldDimensions.getX()/fieldDimensions.getY();
        Image originalSportFieldImage = new Image(file.toURI().toString());
        double width = originalSportFieldImage.getWidth();
        double height = width/wantedRatio;
        Image newSportFieldImage = new Image(file.toURI().toString(), width, height, false, true);
        fieldBackground = new ImageView(newSportFieldImage);
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
        placeGameObjectInPane(currentPane);
        if (!Controller.getInstance().isFirstFrame() && (this.mode == EditionMode.IMAGE || this.mode == EditionMode.REAL_TIME))
        {
            Controller.getInstance().previousKeyFrame();
            placeGameObjectInPane(previousPane);
            previousPane.getChildren().forEach(n -> n.setOpacity(0.5));
            Controller.getInstance().nextKeyFrame();
        }
    }

    public void resetDisplay()
    {
        Controller.getInstance().clearUnplacedGameObjects();
        this.currentPane.clearPane();
        this.currentPane.getViewableGameObjects().clear();
        this.previousPane.clearPane();
        this.previousPane.getViewableGameObjects().clear();
        this.generateViewableGameObjectFromFrame(this.currentPane);

        if (!Controller.getInstance().isFirstFrame())
        {
            Controller.getInstance().previousKeyFrame();
            this.generateViewableGameObjectFromFrame(this.previousPane);
            Controller.getInstance().nextKeyFrame();
        }
    }

    private void placeGameObjectInPane(FrameView pane)
    {
        Map<String, ViewableGameObject> viewables = pane.getViewableGameObjects();
        for (String key : viewables.keySet())
        {
            ViewableGameObject obj = viewables.get(key);
            GameObject gameObject = null;
            try
            {
                gameObject = Controller.getInstance().getGameObjectByUUID(key);
            }
            catch (GameObjectNotFound gameObjectNotFound)
            {
                gameObjectNotFound.printStackTrace();
            }
            boolean selected = false;
            if (selectedUUID != null && selectedUUID.equals(gameObject.getId()))
            {
                selected = true;
            }
            obj.setSelected(selected);
            if(gameObject instanceof Player)
            {
            	((ViewablePlayer)obj).setDisplayName(displayNames);
            	((ViewablePlayer)obj).setDisplayRole(displayRoles);
            }
            
            obj.updateNode();//Pour mettre a jour l'affichage selon le domaine
            
            try
			{
				pane.placeViewableInPane(obj, Controller.getInstance().getPosition(gameObject));
			} catch (GameObjectNotFound e)
			{
				e.printStackTrace();
			}
        }
    }

    public void generateViewableGameObjectFromFrame(FrameView pane)
    {
        Frame currentFrame = Controller.getInstance().getCurrentFrame();
        Set<GameObject> gameObjectSet = currentFrame.getGameObjects();
        for (GameObject gameObject : gameObjectSet)
        {
            ViewableGameObject obj = ViewableGameObjectBuilder.buildViewableGameObject(gameObject, displayNames,
                                                                                       displayRoles, false);
            pane.addViewableToHashMap(gameObject.getId(), obj);
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

    public boolean getDisplayNames()
    {
        return this.displayNames;
    }

    public boolean getDisplayRoles()
    {
        return this.displayRoles;
    }

    public ScrollPane getScrollPane()
    {
        return this.scrollPane;
    }

    public void setDisplayNames(boolean displayNames)
    {
        this.displayNames = displayNames;
        displayStrategy();
    }

    public void setDisplayRoles(boolean displayRoles)
    {
        this.displayRoles = displayRoles;
        displayStrategy();
    }

    public String getSelectedUUID()
    {
        return selectedUUID;
    }

    public void setSelectedUUID(String selectedUUID)
    {
        this.selectedUUID = selectedUUID;
    }

	public void delete()
	{
		Controller.getInstance().deleteGameObject(selectedUUID);
		this.resetDisplay();
		this.displayStrategy();
	}

}
