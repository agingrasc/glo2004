package org.glo.giftw.view;

import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import org.glo.giftw.domain.util.Vector;
import org.glo.giftw.view.edit.ViewableGameObject;

import java.util.HashMap;

public class FrameView extends Pane
{
    private HashMap<String, ViewableGameObject> viewableGameObjects;

    public FrameView()
    {
        this.setBackground(Background.EMPTY);
        this.viewableGameObjects = new HashMap<>();

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
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString())
        {
            String uuid = db.getString();
            Vector coordinate = new Vector(event.getX(), event.getY());

            ViewableGameObject viewableGameObject = this.getViewableGameObject(uuid);
            this.placeViewableInPane(viewableGameObject, coordinate);
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

    public ViewableGameObject getViewableGameObject(String uuid)
    {
        return this.viewableGameObjects.get(uuid);
    }
}
