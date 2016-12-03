package org.glo.giftw.view;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.Dragable;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.util.Vector;
import org.glo.giftw.view.edit.ViewableGameObject;
import org.glo.giftw.view.edit.ViewableGameObjectBuilder;

import java.io.IOException;
import java.util.HashMap;

public class FrameView extends Pane
{
    private HashMap<String, ViewableGameObject> viewableGameObjects;

    public FrameView()
    {
        this.setBackground(Background.EMPTY);
        this.viewableGameObjects = new HashMap<>();

        this.setOnDragOver((DragEvent event) ->
                           {
                               if (event.getDragboard().hasString())
                               {
                                   event.acceptTransferModes(TransferMode.ANY);
                               }
                               event.consume();
                           });

        this.setOnDragDropped(new EventHandler<DragEvent>()
        {
            public void handle(DragEvent event)
            {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString())
                {
                    Dragable dragObject = Controller.getInstance().getDraggedObject(db.getString());
                    String uuid = db.getString();
                    Vector coordinate = new Vector(event.getX(), event.getY());

                    ViewableGameObject viewableGameObject;
                    try
                    {
                        viewableGameObject = ViewableGameObjectBuilder.buildViewableGameObject(uuid);
                    }
                    catch (GameObjectNotFound gameObjectNotFound)
                    {
                        gameObjectNotFound.printStackTrace();
                        success = false;
                        return;
                    }
                    try
                    {
                        RootLayoutController.getInstance().getCreationStackPaneController().getCurrentPane().addViewableGameObject(
                                viewableGameObject, coordinate);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    success = true;
                }
                event.setDropCompleted(success);

                event.consume();
            }
        });
    }

    public void addViewableGameObject(ViewableGameObject viewableGameObject, Vector coordinate)
    {
        Node node = viewableGameObject.display(coordinate);
        this.getChildren().add(node);
    }

    public ViewableGameObject getViewableGameObject(String uuid)
    {
        return this.viewableGameObjects.get(uuid);
    }
}
