package org.glo.giftw.view.edit;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.glo.giftw.domain.Controller;
import org.glo.giftw.domain.exceptions.GameObjectNotFound;
import org.glo.giftw.domain.strategy.Player;
import org.glo.giftw.domain.util.Vector;
import org.glo.giftw.view.RootLayoutController;

import java.io.IOException;

/**
 *
 */
public class ViewablePlayer extends ViewableGameObject
{
    private boolean isDisplayName;
    private boolean isDisplayRole;
    private Canvas playerImg;
    private Label name;
    private Label role;

    public ViewablePlayer(String uuid, boolean isDisplayName, boolean isDisplayRole, boolean isSelected)
    {
        super();
        this.uuid = uuid;
        this.isDisplayName = isDisplayName;
        this.isDisplayRole = isDisplayRole;
        this.isSelected = isSelected;
        this.constructNode();
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

    private Canvas getPlayerImg(Player player)
    {
        Color teamColor = getTeamColor(player);
        Vector dimensions = Controller.getInstance().getDimensions(player);
        float orientation;
        try
        {
            orientation = Controller.getInstance().getOrientation(player);
            System.out.println(orientation);
        }
        catch (NullPointerException e)
        {
            orientation = 0;
        }
        Canvas canvas = new Canvas(dimensions.getX(), dimensions.getY());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(teamColor);
        gc.fillOval(0, 0, dimensions.getX(), dimensions.getY());
        gc.setFill(Color.BLACK);
        double[] xCoord = {dimensions.getX(), 0.7 * dimensions.getX(), 0.7 * dimensions.getX()};
        double[] yCoord = {dimensions.getY() / 2, 0.3 * dimensions.getY(), 0.7 * dimensions.getX()};
        gc.fillPolygon(xCoord, yCoord, 3);
        //gc.rotate(orientation);
        return canvas;
    }

    private Color getTeamColor(Player player)
    {
        String teamName = this.ctlInst.getPlayerTeam(player);
        String teamColor = this.ctlInst.getTeamColour(teamName);
        return Color.web(teamColor);
    }

    @Override
    public Node constructNode()
    {
        Player player = getPlayer();
        playerImg = getPlayerImg(player);
        name = new Label(player.getName());
        role = new Label(player.getRole());
        node = new VBox();
        ((VBox) node).setAlignment(Pos.CENTER);        
        ((VBox) node).getChildren().add(name);
        ((VBox) node).getChildren().add(role);
        ((VBox) node).getChildren().add(playerImg);
        updateNode();
        node.setOnDragDetected(this::onNodeDragDetected);
        initMouseClicked();
        return node;
    }
    
    public void updateNode() 
    {
    	if (isSelected)
        {
            node.setStyle("-fx-background-color: gray");
        }
        else
        {
            node.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        }
    	Player player = getPlayer();
    	playerImg = getPlayerImg(player);
    	name.setText(player.getName());
        role.setText(player.getRole());
        name.setVisible(isDisplayName);
        role.setVisible(isDisplayRole);
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
    
    public void setDisplayName(boolean isDisplayName)
	{
		this.isDisplayName = isDisplayName;
	}

	public void setDisplayRole(boolean isDisplayRole)
	{
		this.isDisplayRole = isDisplayRole;
	}

	protected void initMouseClicked()
    {
        this.node.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent me)
            {
                try
                {
                    RootLayoutController.getInstance().getCreationStackPaneController().setSelectedUUID(uuid);
                    RootLayoutController.getInstance().getCreationStackPaneController().displayStrategy();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }

                Accordion rightMenu = null;
                try
                {
                    rightMenu = RootLayoutController.getInstance().getPlayerPropertiesPaneController().getRootAccordion();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                RootLayoutController.getInstance().setRightPane(rightMenu);

                me.consume();
            }
        });
    }
}
