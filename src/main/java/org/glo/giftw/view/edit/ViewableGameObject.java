package org.glo.giftw.view.edit;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.util.Viewable;

/**
 *
 */
public class ViewableGameObject
{
    protected Controller ctlInst;
    protected Node node;
    protected String uuid;

    protected ViewableGameObject(String uuid)
    {
        this.uuid = uuid;
        this.ctlInst = Controller.getInstance();
    }

    public Node display(float xPos, float yPos)
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
        Image img = new Image(this.getClass().getResourceAsStream(imgPath));
        ImageView node = new ImageView(img);

        node.relocate(xPos, yPos);

        this.node = node;

        return node;
    }

    public Image getSnapshot()
    {
        new Scene(this);//Pas le choix pour snapshot
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage snapshot = this.snapshot(parameters, null);
        return snapshot;
    }

    public void setShowName(boolean show)
    {
        if(show)
        {
            name.setVisible(true);
        }
        else
        {
            name.setVisible(false);
        }
    }

    public void setShowRole(boolean show)
    {
        if(show)
        {
            role.setVisible(true);
        }
        else
        {
            role.setVisible(false);
        }
    }

}
