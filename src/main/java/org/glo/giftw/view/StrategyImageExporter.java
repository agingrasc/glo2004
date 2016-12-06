package org.glo.giftw.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.glo.giftw.domain.strategy.Frame;
import org.glo.giftw.domain.strategy.GameObject;
import org.glo.giftw.domain.strategy.Strategy;
import org.glo.giftw.view.edit.ViewableGameObject;
import org.glo.giftw.view.edit.ViewablePlayer;

import java.io.File;
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

    public StrategyImageExporter(Strategy strategy)
    {
        content = new Canvas();
        gc = content.getGraphicsContext2D();
        gcDraw = new GraphicContextDrawController();

        strategy = strategy;
    }

    public void exportStratAsImage()
    {
        this.setImage(gcDraw.getCurrentDrawnState());
    }

    public void tracePlayerMovement(ViewablePlayer player)
    {

    }

    public void traceProjectileMovement(ViewableGameObject projectile)
    {

    }

    public void drawGameObject(ViewableGameObject gameObject)
    {
        Image image = gameObject.getImage();
        gcDraw.drawImage(gc, image, image.getWidth(), image.getHeight());
    }
    
    public void drawFirstFrame()
    {

        Frame firstFrame = strategy.getFrame(0);
        Set<GameObject> gameObjectSet = firstFrame.getGameObjects();
        for(int i =0; i<gameObjectSet.size(); i++)
        {
            // get game objects in frame
            ViewableGameObject vgo;
            drawGameObject(vgo);

        }
    }

}
