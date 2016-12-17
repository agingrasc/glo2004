package org.glo.giftw.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.paint.Color;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.strategy.*;
import org.glo.giftw.domain.util.Vector;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by alexandra on 12/6/16.
 */
public class StrategyImageExporter {
    private Vector dimensions;

    private Controller controller;

    private Canvas content;
    private GraphicsContext gc;
    private GraphicContextDrawController gcDraw;

    public StrategyImageExporter() {
        System.out.println("BuildingStrategyExportation");

        controller = Controller.getInstance();

        File fieldPath = new File(controller.getSportFieldImagePath());
        Image fieldBackground = new Image(fieldPath.toURI().toString());

        content = new Canvas(fieldBackground.getWidth(), fieldBackground.getHeight());
        gc = content.getGraphicsContext2D();
        gcDraw = new GraphicContextDrawController();

        computeAndSetPixelToUnitRatio();
        dimensions = controller.getFieldDimensions();

        gcDraw.drawImage(gc, fieldBackground);

        drawObjects();
    }

    public Vector getDimensions() {
        return dimensions;
    }

    public Image getImage() {
        return gcDraw.getCurrentDrawnState();
    }

    private void setAtFirstFrame() {
        while (!controller.isFirstFrame()) {
            controller.previousFrame();
        }
    }

    private void computeAndSetPixelToUnitRatio()
    {
        Vector fieldDimensions = Controller.getInstance().getFieldDimensions();
        double adjustedWidth = gc.getCanvas().getWidth();
        double adjustedHeight = gc.getCanvas().getHeight();
        System.out.println(adjustedHeight);
        System.out.println(adjustedWidth);
        System.out.println(fieldDimensions.getY());
        System.out.println(fieldDimensions.getX());
        Vector ratioPixelToUnit = new Vector(adjustedWidth / fieldDimensions.getX(),
                adjustedHeight / fieldDimensions.getY());
        controller.setPixelToUnitRatio(ratioPixelToUnit);
    }

    private void drawGameObject(GameObject gameObject, String filePath, Vector size) {
        Vector position = controller.getPosition(gameObject);

        File file = new File(filePath);
        gcDraw.drawImage(gc, new Image(file.toURI().toString()), position.getX(), position.getY(), size.getX(), size.getY());
    }

    private void centerPosition(Vector position, Vector size)
    {
        position.setX(position.getX() + (0.5*size.getX()));
        position.setY(position.getY() + (0.5*size.getY()));
    }


    private void tracePlayerMovement(GameObject player) {
        gcDraw.setStrokeLineSize(gc, 5);
        setAtFirstFrame();
        Vector size = controller.getDimensions(player);
        Vector prevPosition = controller.getPosition(player);
        centerPosition(prevPosition, size);
        Vector nextPosition;

        while (!controller.isLastFrame()) {
            controller.nextFrame();
            nextPosition = controller.getPosition(player);
            centerPosition(nextPosition, size);
            if (controller.getCurrentFrame().isKeyFrame()) {
                gcDraw.drawArrow(gc, prevPosition.getX(), prevPosition.getY(), nextPosition.getX(), nextPosition.getY());
            } else {
                gcDraw.drawLine(gc, prevPosition.getX(), prevPosition.getY(), nextPosition.getX(), nextPosition.getY());
            }

            prevPosition = nextPosition;
        }

    }

    private void traceProjectileMovement(GameObject projectile) {
        setAtFirstFrame();
        gcDraw.setStrokeLineSize(gc, 5);
        gcDraw.setStrokeLineStyle(gc, true);
        Frame frame = controller.getCurrentFrame();
        Vector prevPosition = frame.getPosition(projectile);
        Vector nextPosition;

        while (!controller.isLastFrame()) {
            controller.nextFrame();
            frame = controller.getCurrentFrame();
            nextPosition = frame.getPosition(projectile);
            if (frame.isKeyFrame()) {
                gcDraw.drawArrow(gc, prevPosition.getX(), prevPosition.getY(), nextPosition.getX(), nextPosition.getY());
            } else {
                gcDraw.drawLine(gc, prevPosition.getX(), prevPosition.getY(), nextPosition.getX(), nextPosition.getY());
            }

            prevPosition = nextPosition;
        }
        gcDraw.setStrokeLineStyle(gc, false);
    }

    private void drawPlayer(GameObject player) {
        Vector position = controller.getPosition(player);
        Vector size = controller.getDimensions(player);

        position.setY(position.getY()+(0.5*size.getY()));

        String team = controller.getPlayerTeam(player);

        Color color = Color.web(controller.getTeamColour(team));
        gcDraw.setDrawColor(gc, color);

        gc.fillOval(position.getX(), position.getY(), size.getX(), size.getY());

        //gcDraw.setDrawColor(gc, Color.WHITE);
        //gcDraw.drawText(gc, position.getX(), position.getY(), 15, player.getName());

        gcDraw.setDrawColor(gc, Color.BLACK);
    }

    private void drawPlayerHasBall(GameObject player, Vector position) {
        drawPlayer(player);
        gcDraw.setDrawColor(gc, Color.YELLOW);

        Vector size = player.getDimensions();
        gc.strokeOval(position.getX(), position.getY(), size.getX(), size.getY());
    }

    private void drawObjects() {
        // get game objects in frame
        setAtFirstFrame();

        for (GameObject gameObject : controller.getGameObjects()) {
            if (gameObject instanceof Player) {
                tracePlayerMovement(gameObject);
                drawPlayer(gameObject);
                setAtFirstFrame();
                drawPlayer(gameObject);
            }
        }

        /*Projectile projectile = controller.getProjectile();
        traceProjectileMovement(projectile);
        drawGameObject(projectile, projectile.getImagePath(), projectile.getDimensions());

        for (Obstacle obstacle : controller.getObstacles()) {
            String filePath = obstacle.getImagePath();
            Vector size = obstacle.getDimensions();

            drawGameObject(obstacle, filePath, size);
        }*/

        gcDraw.saveLastDrawnState(gc);
    }

}

