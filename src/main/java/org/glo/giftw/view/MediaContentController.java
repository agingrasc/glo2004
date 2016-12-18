package org.glo.giftw.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.*;
import org.glo.giftw.domain.util.Vector;

import java.io.File;
import java.util.Collection;

public class MediaContentController
{


    @FXML
    private Pane field;

    private Vector ratioPixelToUnit;

    public void displayNewFrame()
    {
        field.getChildren().clear();
        this.initWithCurrentFrame();
    }

    public void initWithCurrentFrame()
    {
        Vector fieldDimensions = Controller.getInstance().getFieldDimensions();

        File file = new File(Controller.getInstance().getSportFieldImagePath());
        Image sportFieldImage = new Image(file.toURI().toString());

        BackgroundPosition bgPos = BackgroundPosition.DEFAULT;
        BackgroundImage bgImg = new BackgroundImage(sportFieldImage, BackgroundRepeat.NO_REPEAT,
                                                    BackgroundRepeat.NO_REPEAT, bgPos, BackgroundSize.DEFAULT);
        Background bg = new Background(bgImg);
        this.field.setBackground(bg);

        double imgWidth = sportFieldImage.getWidth();
        double imgHeight = sportFieldImage.getHeight();
        double actualHeight = this.field.getParent().getBoundsInParent().getHeight() - 150; //magic number venant des prefHeight
        double ratio = actualHeight / imgHeight;
        double actualWidth = imgWidth * ratio;

        this.ratioPixelToUnit = new Vector(actualWidth / fieldDimensions.getX(), actualHeight / fieldDimensions.getY());
        this.displayFrame(Controller.getInstance().getCurrentFrame());
    }

    public void displayFrame(Frame frame)
    {
        Collection<GameObject> gameObjects = frame.getGameObjects();
        for (GameObject go : gameObjects)
        {
            Vector position = new Vector(0,0);
			try
			{
				position = frame.getPosition(go);
			} catch (GameObjectNotFound e)
			{
				e.printStackTrace();
			}
            float orientation = 0;
			try
			{
				orientation = frame.getOrientation(go);
			} catch (GameObjectNotFound e)
			{
				e.printStackTrace();
			}
            Vector dimension = Controller.getInstance().getDimensions(go);
            if (go instanceof Player)
            {
                Player player = (Player) go;
                String teamColor = "";
                Collection<Team> teams = Controller.getInstance().getTeams();
                for (Team t : teams)
                {
                    if (t.isPlayerInTeam(player))
                    {
                        teamColor = t.getColour();
                    }
                }

                GridPane gp = new GridPane();
                Circle playerImg = new Circle(dimension.getX());
                playerImg.setFill(Color.web(teamColor));
                gp.add(new Label(player.getName()), 0, 0);
                gp.add(new Label(player.getRole()), 0, 1);
                gp.add(new Circle(dimension.getX()), 0, 2);
                gp.setPrefWidth(this.ratioPixelToUnit.getX() * dimension.getX());
                gp.setPrefHeight(this.ratioPixelToUnit.getY() * dimension.getY());
                gp.setLayoutX(this.ratioPixelToUnit.getX() * position.getX());
                gp.setLayoutY(this.ratioPixelToUnit.getY() * position.getY());
                gp.setRotate(orientation);
                this.field.getChildren().add(gp);
            }
            if (go instanceof Projectile)
            {
                String imgPath = ((Projectile) go).getImagePath();
                File file = new File(imgPath);
                Image projImg = new Image(file.toURI().toString());
                ImageView iv = new ImageView(projImg);
                iv.setFitWidth(this.ratioPixelToUnit.getX() * dimension.getX());
                iv.setFitHeight(this.ratioPixelToUnit.getY() * dimension.getY());
                iv.setLayoutX(this.ratioPixelToUnit.getX() * position.getX());
                iv.setLayoutY(this.ratioPixelToUnit.getY() * position.getY());
                iv.setRotate(orientation);
                this.field.getChildren().add(iv);
            }
            if (go instanceof Obstacle)
            {
                String imgPath = ((Obstacle) go).getImagePath();
                File file = new File(imgPath);
                Image obsImg = new Image(file.toURI().toString());
                ImageView iv = new ImageView(obsImg);
                iv.setFitWidth(this.ratioPixelToUnit.getX() * dimension.getX());
                iv.setFitHeight(this.ratioPixelToUnit.getY() * dimension.getY());
                iv.setLayoutX(this.ratioPixelToUnit.getX() * position.getX());
                iv.setLayoutY(this.ratioPixelToUnit.getY() * position.getY());
                iv.setRotate(orientation);
                this.field.getChildren().add(iv);
            }
        }
    }

    public Pane getField()
    {
        return this.field;
    }
}
