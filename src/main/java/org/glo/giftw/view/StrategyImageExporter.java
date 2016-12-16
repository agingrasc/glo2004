package org.glo.giftw.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.*;
import org.glo.giftw.domain.util.Viewable;
import org.glo.giftw.view.edit.ViewableGameObject;
import org.glo.giftw.view.edit.ViewablePlayer;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by alexandra on 12/6/16.
 */
public class StrategyImageExporter extends ImageView {
    private Strategy strategy;

    private ImageFileController imageFileController;
    private File fileName;

    private Canvas content;
    private GraphicsContext gc;
    private GraphicContextDrawController gcDraw;

    public StrategyImageExporter(Strategy pStrategy)
    {
        content = new Canvas();
        gc = content.getGraphicsContext2D();
        gcDraw = new GraphicContextDrawController();

        strategy = pStrategy;

    }

    public void exportStratAsImage()
    {
        this.setImage(gcDraw.getCurrentDrawnState());
    }

    public void tracePlayerMovement(Player player)
    {

    }

    public void traceProjectileMovement(ViewableGameObject projectile)
    {

    }

    public void drawGameObject(GameObject gameObject)
    {
        try {
            String uuid = gameObject.getId();

            Image image = vgo.getImage();
            gcDraw.drawImage(gc, image, image.getWidth(), image.getHeight());
        }
        catch (GameObjectNotFound gonf)
        {

        }
    }

    public void drawObjectsInFrame()
    {
        Frame firstFrame = strategy.getFrame(0);
        Set<GameObject> gameObjectSet = firstFrame.getGameObjects();
        // get game objects in frame
        GameObject[] gameObjects = gameObjectSet.toArray(new GameObject[gameObjectSet.size()]);

        for(int i = 0; i < gameObjectSet.size(); i++)
        {
            GameObject aGameObject = gameObjects[i];
            drawGameObject(aGameObject);
            if()
        }
    }

}
