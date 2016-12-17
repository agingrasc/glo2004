package org.glo.giftw.view;

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
import org.glo.giftw.domain.util.Vector;
import org.glo.giftw.view.edit.ViewableGameObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrameView extends Pane
{
    private HashMap<String, ViewableGameObject> viewableGameObjects;

    public FrameView()
    {
        this.setBackground(Background.EMPTY);
        this.viewableGameObjects = new HashMap<>();
        this.initMouseClicked();
        this.setOnDragOver(this::onDragOver);
        this.setOnDragDropped(this::onDragDropped);
    }

    public void onDragOver(DragEvent event)
    {
        if (event.getDragboard().hasString())
        {
            event.acceptTransferModes(TransferMode.ANY);
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
                Controller.getInstance().placeGameObject(uuid, coordinate, 0);
                RootLayoutController.getInstance().getCreationStackPaneController().displayStrategy();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (GameObjectNotFound e)
            {
                e.printStackTrace();
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
}
