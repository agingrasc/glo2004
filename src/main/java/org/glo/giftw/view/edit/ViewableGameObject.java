package org.glo.giftw.view.edit;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.GameObject;
import org.glo.giftw.domain.util.Vector;
import org.glo.giftw.domain.util.Viewable;
import org.glo.giftw.view.EditionMode;
import org.glo.giftw.view.RootLayoutController;

import java.io.IOException;

/**
 *
 */
public class ViewableGameObject
{
    protected Controller ctlInst;
    protected Node node;
    protected String uuid;
    protected boolean isSelected;

    protected ViewableGameObject()
    {
        this.ctlInst = Controller.getInstance();
        this.node = null;
        this.uuid = null;
    }

    public ViewableGameObject(String uuid, boolean isSelected)
    {
    	this.isSelected = isSelected;
        this.uuid = uuid;
        this.ctlInst = Controller.getInstance();
        this.constructNode();
    }

    protected Node constructNode()
    {
        Image img = this.getImage();
        this.node = new ImageView(img);
        this.node.setOnDragDetected(this::onNodeDragDetected);
        return this.node;
    }
    
    public void updateNode()
    {
    	 if(isSelected)
         {
         	node.setStyle("-fx-background-color: gray");
         }
    	 else
    	 {
    		 node.setStyle(null);
    	 }
    }

    public Node display(Vector position)
    {
        this.node.relocate(position.getX(), position.getY());
        return this.node;
    }

    public Image getImage()
    {
        String imgPath = null;
        Vector dimensions = null;
        try
        {
            GameObject gameObject = this.ctlInst.getGameObjectByUUID(this.uuid);
            imgPath = ((Viewable) gameObject).getImagePath();
            dimensions = ctlInst.getDimensions(gameObject);
        }
        catch (GameObjectNotFound gameObjectNotFound)
        {
            gameObjectNotFound.printStackTrace();
            return null;
        }
        return new Image(String.format("file:%s", imgPath), dimensions.getX(), dimensions.getY(), true, true);
    }

    protected void onNodeDragDetected(MouseEvent event)
    {
        Controller.getInstance().enableAutoSave(false);
        try
        {
            if (RootLayoutController.getInstance().getCreationStackPaneController().mode == EditionMode.REAL_TIME)
            {
                RootLayoutController.getInstance().getCreationStackPaneController().start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Dragboard db = this.node.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        db.setDragView(this.getImage());
        content.putString(this.uuid);
        db.setContent(content);
        event.consume();
    }

    public void setSelected(boolean selected)
    {
    	this.isSelected = selected;
    }
}
