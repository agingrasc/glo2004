package org.glo.giftw.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.*;
import org.glo.giftw.domain.util.Vector;
import org.glo.giftw.view.edit.ViewableGameObject;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Created by alexandra on 12/6/16.
 */
public class StrategyImageExporter extends ImageView {
    private List<Frame> frames;
    private Vector dimensions;

    private Controller controller;

    private ImageFileController imageFileController;
    private File fileName;

    private Canvas content;
    private GraphicsContext gc;
    private GraphicContextDrawController gcDraw;

    public StrategyImageExporter()
    {
        controller = Controller.getInstance();
        dimensions = controller.getFieldDimensions();

        initListFrames();

        content = new Canvas(dimensions.getX(), dimensions.getY());
        gc = content.getGraphicsContext2D();
        gcDraw = new GraphicContextDrawController();
        String fieldPath = controller.getSportFieldImagePath();

        gcDraw.drawImage(gc, new Image(fieldPath));
    }

    private void initListFrames()
    {
        while (!controller.isFirstFrame())
        {
            controller.previousFrame();
        }
        while (!controller.isLastFrame())
        {
            frames.add(controller.getCurrentFrame());
            controller.nextFrame();
        }
        frames.add(controller.getCurrentFrame());
    }

    protected void drawGameObject(GameObject gameObject, String filePath, Vector size)
    {
        Vector position = controller.getPosition(gameObject);

        gcDraw.drawImage(gc, new Image(filePath), position.getX(), position.getY(), size.getX(), size.getY());
    }

    public void exportStratAsImage()
    {
        this.setImage(gcDraw.getCurrentDrawnState());
    }

    public void tracePlayerMovement(GameObject player)
    {
        gcDraw.setStrokeLineSize(gc, 5);
        Frame frame = frames.get(0);
        Vector prevPosition = frame.getPosition(player);
        Vector nextPosition;

        for(int i = 1; i<frames.size(); i++)
        {
            frame = frames.get(i);
            nextPosition = frame.getPosition(player);
            if(frame.isKeyFrame()) {
                gcDraw.drawArrow(gc, prevPosition.getX(), prevPosition.getY(), nextPosition.getX(), nextPosition.getY());
            }
            else {
                gcDraw.drawLine(gc, prevPosition.getX(), prevPosition.getY(), nextPosition.getX(), nextPosition.getY());
            }
            
            prevPosition = nextPosition;
        }
        
        drawPlayer(player);
    }

    public void traceProjectileMovement(Projectile projectile)
    {


    }

    public void drawPlayer(GameObject player)
    {
        String team = controller.getPlayerTeam(player);

        Color color = Color.web(controller.getTeamColour(team));
        gcDraw.setDrawColor(gc, color);

        Vector position = frames.get(0).getPosition(player);
        Vector size = player.getDimensions();

        gc.fillOval(position.getX(), position.getY(), size.getX(), size.getY());

        gcDraw.setDrawColor(gc, Color.WHITE);
        gcDraw.drawText(gc, position.getX(), position.getY(), 15, player.getName());

        gcDraw.setDrawColor(gc, Color.BLACK);
    }

    public void drawObjectsInFrame()
    {
        Frame firstFrame = frames.get(0);
        Set<GameObject> gameObjectSet = firstFrame.getGameObjects();
        // get game objects in frame

        for(GameObject gameObject : gameObjectSet)
        {
            if(gameObject.getClass().isInstance(Player.class))
            {
                drawPlayer(gameObject);
                tracePlayerMovement(gameObject);
            }
        }

        Projectile projectile = controller.getProjectile();
        drawGameObject(projectile, projectile.getImagePath(), projectile.getDimensions());
        traceProjectileMovement(projectile);

        for(Obstacle obstacle : controller.getObstacles())
        {
            String filePath = obstacle.getImagePath();
            Vector size = obstacle.getDimensions();

            drawGameObject(obstacle, filePath, size);
        }
    }


}

