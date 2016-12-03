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

    public ViewableGameObject(String uuid)
    {
        this.uuid = uuid;
        this.ctlInst = Controller.getInstance();
        this.constructNode();
    }

    public Node constructNode()
    {
        Viewable gameObject;
        try
        {
            gameObject = (Viewable) this.ctlInst.getGameObjectByUUID(uuid);
        }
        catch (GameObjectNotFound e)
        {
            e.printStackTrace();
            return null;
        }

        String imgPath = gameObject.getImagePath();
        Image img = this.getImage();
        this.node = new ImageView(img);
        this.node.setOnDragDetected(this::onNodeDragDetected);
        return this.node;
    }

    public Node display(Vector position)
    {
        this.node.relocate(position.getX(), position.getY());
        return this.node;
    }

    public Image getImage()
    {
        String imgPath = null;
        try
        {
            imgPath = ((Viewable) this.ctlInst.getGameObjectByUUID(this.uuid)).getImagePath();
        }
        catch (GameObjectNotFound gameObjectNotFound)
        {
            gameObjectNotFound.printStackTrace();
            return null;
        }
        //FIXME: dynamique
        return new Image(String.format("file:%s", imgPath), 20, 0, true, true);
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

}
