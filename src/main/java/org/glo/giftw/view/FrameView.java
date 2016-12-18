package org.glo.giftw.view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.GameObject;
import org.glo.giftw.domain.strategy.Player;
import org.glo.giftw.domain.util.Vector;
import org.glo.giftw.view.edit.ViewableGameObject;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrameView extends Pane
{
    private HashMap<String, ViewableGameObject> viewableGameObjects;
    private Vector currentMousePosition = new Vector();
    private Vector currentAbsoluteMousePosition = new Vector();

    public FrameView()
    {
        this.setBackground(Background.EMPTY);
        this.viewableGameObjects = new HashMap<>();
        this.initMouseClicked();
        this.setOnDragOver(this::onDragOver);
        this.setOnDragDropped(this::onDragDropped);
    }

    private void initMouseClicked()
    {
        this.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent me)
            {
                Accordion rightMenu = null;
                try
                {
                    rightMenu = RootLayoutController.getInstance().getGeneralPropertiesPaneController().getRootAccordion();
                    RootLayoutController.getInstance().getCreationStackPaneController().setSelectedUUID(null);
                    RootLayoutController.getInstance().getCreationStackPaneController().displayStrategy();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                RootLayoutController.getInstance().setRightPane(rightMenu);
            }
        });
    }

    public void onDragOver(DragEvent event)
    {
        if (event.getDragboard().hasString())
        {
            event.acceptTransferModes(TransferMode.ANY);
        }
        Vector currentMousePosition = new Vector(event.getX(), event.getY());
        String gameObjectUuid;
        GameObject gameObject;
        try
        {
            gameObjectUuid = RootLayoutController.getInstance().getCreationStackPaneController().getSelectedUUID();
            gameObject = Controller.getInstance().getGameObjectByUUID(gameObjectUuid);
            if (gameObject instanceof Player && Controller.getInstance().collide(currentMousePosition, gameObjectUuid))
            {
                Platform.runLater(() ->
                                  {
                                      //FIXME: horrible hack -> http://stackoverflow.com/questions/37500567/javafx-how-to-position-the-mouse
                                      Robot robot = null;
                                      try
                                      {
                                          robot = new Robot();
                                      }
                                      catch (AWTException e)
                                      {
                                          e.printStackTrace();
                                      }
                                      robot.mouseMove((int) this.currentAbsoluteMousePosition.getX(), (int) this.currentAbsoluteMousePosition.getY());
                                  });
            }
            else
            {
                this.currentMousePosition = currentMousePosition;
                this.currentAbsoluteMousePosition = new Vector(event.getScreenX(), event.getScreenY());
            }
        }
        catch (IOException | GameObjectNotFound e)
        {
            e.printStackTrace();
        }
        event.consume();
    }

    public void onDragDropped(DragEvent event)
    {
        try
        {
            RootLayoutController.getInstance().getCreationStackPaneController().stop();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString())
        {
            String uuid = db.getString();
            Vector coordinate = new Vector(event.getX(), event.getY());
            try
            {
                Controller.getInstance().placeGameObject(uuid, coordinate);
                RootLayoutController.getInstance().getCreationStackPaneController().stop();
                RootLayoutController.getInstance().getCreationStackPaneController().displayStrategy();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (GameObjectNotFound e)
            {
            	try
				{
					Controller.getInstance().placeGameObject(uuid, coordinate, 0);
					RootLayoutController.getInstance().getCreationStackPaneController().stop();
	                RootLayoutController.getInstance().getCreationStackPaneController().displayStrategy();
				} catch (GameObjectNotFound e1)
				{
					e1.printStackTrace();
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
            }

            success = true;
        }
        event.setDropCompleted(success);

        event.consume();
    }

    public void addViewableToHashMap(String uuid, ViewableGameObject viewableGameObject)
    {
        this.viewableGameObjects.put(uuid, viewableGameObject);
    }

    public void placeViewableInPane(ViewableGameObject viewableGameObject, Vector coordinate)
    {
        Node node = viewableGameObject.display(coordinate);

        try
        {
            this.getChildren().add(node);
        }
        catch (Exception e)
        {
            //FIXME: duplicate children, mauvaise architecture
        }
    }

    public void clearPane()
    {
        viewableGameObjects.clear();
        this.getChildren().clear();
    }

    public ViewableGameObject getViewableGameObject(String uuid)
    {
        return this.viewableGameObjects.get(uuid);
    }

    public Map<String, ViewableGameObject> getViewableGameObjects()
    {
        return this.viewableGameObjects;
    }

    public Vector getCurrentMousePosition()
    {
        return this.currentMousePosition;
    }
}
