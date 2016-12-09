package org.glo.giftw.view.edit;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.Player;
import org.glo.giftw.domain.util.Vector;
import org.glo.giftw.view.RootLayoutController;

/**
 *
 */
public class ViewablePlayer extends ViewableGameObject
{
    private boolean isDisplayName;
    private boolean isDisplayRole;

    public ViewablePlayer(String uuid)
    {
        super();
        this.uuid = uuid;
        this.node = new VBox();
        //FIXME: passer correctement ces champs à la création
        this.isDisplayName = false;
        this.isDisplayRole = false;
        this.node = this.constructNode();
    }

    private Player getPlayer()
    {
        try
        {
            return (Player) this.ctlInst.getGameObjectByUUID(this.uuid);
        }
        catch (GameObjectNotFound gameObjectNotFound)
        {
            gameObjectNotFound.printStackTrace();
            return null;
        }
    }

    private Circle getPlayerImg(Player player)
    {
        Color teamColor = getTeamColor(player);
        Vector dimensions = Controller.getInstance().getDimensions(player);
        return new Circle(dimensions.getX()/2, teamColor);
    }

    private Color getTeamColor(Player player)
    {
        String teamName = this.ctlInst.getPlayerTeam(player);
        String teamColor = this.ctlInst.getTeamColour(teamName);
        return Color.web(teamColor);
    }

    @Override
    protected Node constructNode()
    {
        Player player = getPlayer();
        Circle playerImg = getPlayerImg(player);
        Label name = new Label(player.getName());
        Label role = new Label(player.getRole());

        VBox node = new VBox();
        //node.setBackground(Background.EMPTY);
        node.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

        name.setVisible(isDisplayName);
        node.getChildren().add(name);

        role.setVisible(isDisplayRole);
        node.getChildren().add(role);

        node.getChildren().add(playerImg);

        node.setOnDragDetected(this::onNodeDragDetected);
        initMouseClicked();
        return node;
    }

    @Override
    public Image getImage()
    {
        return this.getSnapshot();
    }

    private Image getSnapshot()
    {
        if (this.node.getParent() == null && this.node.getScene() == null)
        {
            new Scene((VBox) this.node);//pour que le snapshot prenne en compte les labels
        }
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage snapshot = this.node.snapshot(parameters, null);
        return snapshot;
    }

    public void setShowName(boolean show)
    {
        //FIXME: devrait pouvoir se fixe avec de l'heritage
        ((VBox) this.node).getChildren().get(0).setVisible(show);
    }

    public void setShowRole(boolean show)
    {
        ((VBox) this.node).getChildren().get(1).setVisible(show);
    }
    
    private void initMouseClicked()
    {
        this.node.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent me)
            {
            	try
				{
					RootLayoutController.getInstance().getPlayerPropertiesPaneController().setSelectedUUID(uuid);
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            	Accordion rightMenu = null;
				try
				{
					rightMenu = RootLayoutController.getInstance().getPlayerPropertiesPaneController().getRootAccordion();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                RootLayoutController.getInstance().setRightPane(rightMenu);
            }
        });
    }
}
