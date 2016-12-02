package org.glo.giftw.view.edit;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.Player;

/**
 *
 */
public class ViewablePlayer extends ViewableGameObject
{
    private GridPane node;
    private boolean isDisplayName;
    private boolean isDisplayRole;

    public ViewablePlayer(String uuid)
    {
        super(uuid);
        this.node = new GridPane();
        this.display(0, 0);
        this.isDisplayName = false;
        this.isDisplayRole = false;
    }

    @Override
    public Node display(float xPos, float yPos)
    {
        Player player = getPlayer();

        Circle playerImg = getPlayerImg(player);
        Label name = new Label(player.getName());
        Label role = new Label(player.getRole());

        this.node = constructGridPane(name, role, playerImg);
        this.node.relocate(xPos, yPos);

        return this.node;
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

    private GridPane constructGridPane(Label name, Label role, Circle playerImg)
    {
        GridPane node = new GridPane();
        int rowIdx = 0;
        if (this.isDisplayName)
        {
            node.add(name, 0, rowIdx);
            rowIdx++;
        }

        if (this.isDisplayRole)
        {
            node.add(role, 0, rowIdx);
            rowIdx++;
        }
        node.add(playerImg, 0, rowIdx);
        return node;
    }
}
