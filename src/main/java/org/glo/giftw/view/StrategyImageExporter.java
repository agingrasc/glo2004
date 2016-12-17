package org.glo.giftw.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.paint.Color;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.strategy.*;
import org.glo.giftw.domain.util.Vector;

import java.util.ArrayList;

/**
 * Created by alexandra on 12/6/16.
 */
public class StrategyImageExporter extends ImageView {
    private ArrayList<Frame> frames;
    private Vector dimensions;

    private Controller controller;

    private Canvas content;
    private GraphicsContext gc;
    private GraphicContextDrawController gcDraw;

    public StrategyImageExporter() {
        controller = Controller.getInstance();
        dimensions = controller.getFieldDimensions();

        frames = new ArrayList<>();
        initListFrames();

        content = new Canvas(dimensions.getX(), dimensions.getY());
        gc = content.getGraphicsContext2D();
        gcDraw = new GraphicContextDrawController();
        String fieldPath = controller.getSportFieldImagePath();

        gcDraw.drawImage(gc, new Image(fieldPath));

        drawObjects();

        this.setImage(gcDraw.getCurrentDrawnState());
    }

    private void initListFrames() {
        while (!controller.isFirstFrame()) {
            controller.previousFrame();
        }
        while (!controller.isLastFrame()) {
            frames.add(controller.getCurrentFrame());
            controller.nextFrame();
        }
        frames.add(controller.getCurrentFrame());
    }

    private void drawGameObject(GameObject gameObject, String filePath, Vector size) {
        Vector position = controller.getPosition(gameObject);

        gcDraw.drawImage(gc, new Image(filePath), position.getX(), position.getY(), size.getX(), size.getY());
    }

    private void tracePlayerMovement(GameObject player) {
        gcDraw.setStrokeLineSize(gc, 5);
        Frame frame = frames.get(0);
        Vector prevPosition = frame.getPosition(player);
        Vector nextPosition;

        for (int i = 1; i < frames.size(); i++) {
            frame = frames.get(i);
            nextPosition = frame.getPosition(player);
            if (frame.isKeyFrame()) {
                gcDraw.drawArrow(gc, prevPosition.getX(), prevPosition.getY(), nextPosition.getX(), nextPosition.getY());
            } else {
                gcDraw.drawLine(gc, prevPosition.getX(), prevPosition.getY(), nextPosition.getX(), nextPosition.getY());
            }

            prevPosition = nextPosition;
        }

    }

    private void traceProjectileMovement(GameObject projectile) {
        gcDraw.setStrokeLineSize(gc, 5);
        gcDraw.setStrokeLineStyle(gc, true);
        Frame frame = frames.get(0);
        Vector prevPosition = frame.getPosition(projectile);
        Vector nextPosition;

        for (int i = 1; i < frames.size(); i++) {
            frame = frames.get(i);
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

    private void drawPlayer(GameObject player, Vector position) {
        String team = controller.getPlayerTeam(player);

        Color color = Color.web(controller.getTeamColour(team));
        gcDraw.setDrawColor(gc, color);

        Vector size = player.getDimensions();

        gc.fillOval(position.getX(), position.getY(), size.getX(), size.getY());

        gcDraw.setDrawColor(gc, Color.WHITE);
        gcDraw.drawText(gc, position.getX(), position.getY(), 15, player.getName());

        gcDraw.setDrawColor(gc, Color.BLACK);
    }

    private void drawPlayerHasBall(GameObject player, Vector position) {
        drawPlayer(player, position);
        gcDraw.setDrawColor(gc, Color.YELLOW);

        Vector size = player.getDimensions();
        gc.strokeOval(position.getX(), position.getY(), size.getX(), size.getY());
    }

    private void drawObjects() {
        Frame firstFrame = frames.get(0);
        // get game objects in frame

        for (GameObject gameObject : firstFrame.getGameObjects()) {
            if (gameObject.getClass().isInstance(Player.class)) {
                tracePlayerMovement(gameObject);
                drawPlayer(gameObject, frames.get(0).getPosition(gameObject));
                drawPlayer(gameObject, frames.get(frames.size()).getPosition(gameObject));
            }
        }

        Projectile projectile = controller.getProjectile();
        traceProjectileMovement(projectile);
        drawGameObject(projectile, projectile.getImagePath(), projectile.getDimensions());

        for (Obstacle obstacle : controller.getObstacles()) {
            String filePath = obstacle.getImagePath();
            Vector size = obstacle.getDimensions();

            drawGameObject(obstacle, filePath, size);
        }

        gcDraw.saveLastDrawnState(gc);
    }

}

