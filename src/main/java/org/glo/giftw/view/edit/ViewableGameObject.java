package org.glo.giftw.view.edit;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.GameObject;
import org.glo.giftw.domain.util.Vector;
import org.glo.giftw.domain.util.Viewable;

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
        //FIXME: code smell
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
        //System.out.println(position);
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
