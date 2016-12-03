package org.glo.giftw.view.edit;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.Player;
import org.glo.giftw.domain.util.Vector;

/**
 *
 */
public class ViewablePlayer extends ViewableGameObject
{
    private VBox node;

    public ViewablePlayer(String uuid)
    {
        super(uuid);
        this.node = new VBox();
        this.display(new Vector());
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
        return new Circle(30, teamColor);
    }

    private Color getTeamColor(Player player)
    {
        String teamName = this.ctlInst.getPlayerTeam(player);
        String teamColor = this.ctlInst.getTeamColour(teamName);
        return Color.web(teamColor);
    }

    private Node constructNode(boolean displayName, boolean displayRole)
    {
        Player player = getPlayer();
        Circle playerImg = getPlayerImg(player);
        Label name = new Label(player.getName());
        Label role = new Label(player.getRole());

        VBox node = new VBox();

        name.setVisible(displayName);
        node.getChildren().add(name);

        role.setVisible(displayRole);
        node.getChildren().add(role);

        node.getChildren().add(playerImg);

        node.setOnDragDetected(this::onNodeDragDetected);
        return node;
    }

    @Override
    public Image getImage()
    {
        return this.getSnapshot();
    }

    private Image getSnapshot()
    {
        new Scene(this.node);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage snapshot = this.node.snapshot(parameters, null);
        return snapshot;
    }

    public void setShowName(boolean show)
    {
        this.node.getChildren().get(0).setVisible(show);
    }

    public void setShowRole(boolean show)
    {
        this.node.getChildren().get(1).setVisible(show);
    }
}
